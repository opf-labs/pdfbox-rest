/**
 * 
 */
package org.verapdf.pdfa.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
 *
 */
public class ValidationMetadata {
    private final PdfaFlavour flavour;
    private final ValidationResult result;
    
    private ValidationMetadata(PdfaFlavour flavour, ValidationResult result) {
        this.flavour = flavour;
        this.result = result;
    }

    /**
     * @return the flavour
     */
    @JsonProperty
    public final PdfaFlavour getFlavour() {
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
    public final static ValidationMetadata fromValues(PdfaFlavour flavour, ValidationResult result) {
        return new ValidationMetadata(flavour, result);
    }
}
