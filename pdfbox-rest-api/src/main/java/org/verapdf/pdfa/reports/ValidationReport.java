/**
 * 
 */
package org.verapdf.pdfa.reports;

import java.io.IOException;
import java.io.InputStream;

import javax.activation.DataSource;

import org.apache.pdfbox.preflight.PreflightDocument;
import org.apache.pdfbox.preflight.parser.PreflightParser;
import org.apache.pdfbox.preflight.utils.ByteArrayDataSource;
import org.verapdf.pdfa.metadata.DocumentMetadata;
import org.verapdf.pdfa.metadata.Metadata;
import org.verapdf.pdfa.metadata.ValidationMetadata;
import org.verapdf.pdfa.metadata.ValidationResult;
import org.verapdf.pdfa.spec.PdfaFlavour;

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

    /**
     * Creates a ValidationReport instance from a PDF format InputStream.
     * 
     * @param pdfStream
     *            InputStream of PDF format to validate
     * @return a ValidationReport containing details of the PDF/A Validation
     *         process
     * @throws IOException
     *             when there's a problem reading the PDF input stream
     */
    public final static ValidationReport fromPdfStream(InputStream pdfStream)
            throws IOException {
        DataSource source = new ByteArrayDataSource(pdfStream);
        PreflightParser parser = new PreflightParser(source);
        parser.parse();
        DocumentMetadata docMd = Metadata.fromPdfBoxDocInfo(parser
                .getPDDocument());
        try (PreflightDocument document = parser.getPreflightDocument()) {
            document.validate();
            ValidationResult result = ValidationResult
                    .fromPreflightValidationResult(document.getResult());
            return new ValidationReport(docMd, ValidationMetadata.fromValues(
                    PdfaFlavour.PDFA_1_B, result));
        }
    }
}
