/**
 * 
 */
package org.verapdf.pdfa.metadata;

import java.io.IOException;
import java.io.InputStream;

import javax.activation.DataSource;

import org.apache.pdfbox.preflight.PreflightDocument;
import org.apache.pdfbox.preflight.parser.PreflightParser;
import org.apache.pdfbox.preflight.utils.ByteArrayDataSource;
import org.eclipse.jetty.util.log.Log;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
 *
 */
public class ValidationReport {
    private final DocumentMetadata docInfo;
    private final ValidationMetadata validationData;
    
    private ValidationReport(final DocumentMetadata docInfo,
    final ValidationMetadata validationData) {
        this.docInfo = docInfo;
        this.validationData = validationData;
    }

    /**
     * @return the docInfo
     */
    @JsonProperty
    public final DocumentMetadata getDocInfo() {
        return this.docInfo;
    }

    /**
     * @return the validationData
     */
    @JsonProperty
    public final ValidationMetadata getValidationData() {
        return this.validationData;
    }
    
    public final static ValidationReport fromPdfStream(InputStream pdfStream) throws IOException {
        DataSource source = new ByteArrayDataSource(pdfStream);
        PreflightParser parser = new PreflightParser(source);
        parser.parse();
        DocumentMetadata docMd = Metadata.fromPdfBoxDocInfo(parser.getPDDocument());
        PreflightDocument document = parser.getPreflightDocument();
        document.validate();
        ValidationResult result =  ValidationResult.fromPreflightValidationResult(document.getResult());
        document.close();
        return new ValidationReport(docMd, ValidationMetadata.fromValues(PdfaFlavour.PDFA_1_B, result));
    }
}
