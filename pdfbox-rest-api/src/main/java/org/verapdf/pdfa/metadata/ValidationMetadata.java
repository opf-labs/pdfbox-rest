/**
 * 
 */
package org.verapdf.pdfa.metadata;

import org.verapdf.pdfa.spec.PdfaSpecification;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
 *
 */
public class ValidationMetadata {
    private final PdfaSpecification flavour;
    private final ValidationResult result;
    
    private ValidationMetadata(PdfaSpecification flavour, ValidationResult result) {
        this.flavour = flavour;
        this.result = result;
    }

    /**
     * @return the flavour
     */
    @JsonProperty
    public final PdfaSpecification getFlavour() {
        return this.flavour;
    }

    /**
     * @return the result
     */
    @JsonProperty
    public final ValidationResult getResult() {
        return this.result;
    }
    
    /**
     * @param flavour
     * @param result
     * @return
     */
    public final static ValidationMetadata fromValues(PdfaSpecification flavour, ValidationResult result) {
        return new ValidationMetadata(flavour, result);
    }
}
