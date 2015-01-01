/**
 * 
 */
package org.verapdf.pdfa.metadata;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.activation.DataSource;

import org.apache.pdfbox.preflight.PreflightDocument;
import org.apache.pdfbox.preflight.ValidationResult.ValidationError;
import org.apache.pdfbox.preflight.parser.PreflightParser;
import org.apache.pdfbox.preflight.utils.ByteArrayDataSource;
import org.eclipse.jetty.util.log.Log;
import org.verapdf.pdfa.metadata.CheckResult.Status;
import org.verapdf.pdfa.metadata.ValidationSummary.Counter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
 *
 */
public class ValidationResult {
    /** Default statement for a validation result */
    public final static String DEFAULT_STATEMENT = "unknown"; //$NON-NLS-1$
    private final static ValidationResult DEFAULT_INSTANCE = new ValidationResult();
    private final boolean isCompliant;
    private final String statement;
    private final ValidationSummary summary;
    private final List<CheckAudit> checkAudits;

    private ValidationResult() {
        this(false, DEFAULT_STATEMENT, ValidationSummary.defaultInstance(), new ArrayList<CheckAudit>());
    }
    private ValidationResult(final boolean isCompliant, final String statement,
            final ValidationSummary summary, List<CheckAudit> checkAudits) {
        this.isCompliant = isCompliant;
        this.statement = statement;
        this.summary = summary;
        this.checkAudits = Collections.unmodifiableList(checkAudits);
    }

    /**
     * @return true if the document is compliant with the specification
     */
    @JsonProperty
    public final boolean isCompliant() {
        return this.isCompliant;
    }

    /**
     * @return the statement summarising the validation
     */
    @JsonProperty
    public final String getStatement() {
        return this.statement;
    }

    /**
     * @return the summary of validation
     */
    @JsonProperty
    public final ValidationSummary getSummary() {
        return this.summary;
    }
    
    /**
     * @return a java.util.collections.List of the checkAudits tested during validation
     */
    @JsonProperty
    @JacksonXmlElementWrapper(useWrapping=false)
    public final List<CheckAudit> getCheckAudits() {
        return this.checkAudits;
    }
    
    /**
     * @return the meaningless static default instance, used for testing
     */
    public static final ValidationResult defaultInstance() {
        return DEFAULT_INSTANCE;
    }
    
    /**
     * @param preflightResult A Preflight Validation result to generate our report from
     * @return a VeraPDF ValidationResult created from the supplied document
     */
    public static final ValidationResult fromPreflightValidationResult(org.apache.pdfbox.preflight.ValidationResult preflightResult) {
        boolean isCompliant = preflightResult.isValid();
        Counter summariser = new Counter();
        
        // TODO: This is all wrong, it requires the grouping of checks made under a
        //       single check "clause" id and explanation. See Check ID for related
        //       info. Also the Preflight 1.8.7 doesnt support page numbers for errors. 
        List<CheckAudit> checkAudits = new ArrayList<>();
        for (ValidationError preflightError : preflightResult.getErrorsList()) {
            List<CheckResult> results = new ArrayList<>();
            Check check = Check.fromPreflightError(preflightError);
            CheckResult result = CheckResult.fromValues(Status.FAILED, Location.fromValues(preflightError.getPageNumber().intValue()), preflightError.getDetails());
            if (preflightError.isWarning()) {
                summariser.warning();
            } else {
                summariser.checkFailed();
            }
            results.add(result);
            checkAudits.add(CheckAudit.fromValues(check, results));
        }
        return new ValidationResult(isCompliant, DEFAULT_STATEMENT, summariser.createSummary(), checkAudits);
    }
    
    /**
     * @param pdfStream
     * @return
     * @throws IOException
     */
    public static final ValidationResult fromPdfStream(InputStream pdfStream) throws IOException {
        DataSource source = new ByteArrayDataSource(pdfStream);
        Log.getLog().warn(source.getContentType());
        PreflightParser parser = new PreflightParser(source);
        parser.parse();
        try (PreflightDocument document = parser.getPreflightDocument()) {
            document.validate();
            ValidationResult result =  fromPreflightValidationResult(document.getResult());
            return result;
        }
    }
}
