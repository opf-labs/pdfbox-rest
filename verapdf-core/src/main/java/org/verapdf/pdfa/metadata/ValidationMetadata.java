/**
 * 
 */
package org.verapdf.pdfa.metadata;

import org.verapdf.pdfa.spec.PdfaFlavour;

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
	 *            PFF/A flavour enumeration indicating the flavour used for
	 *            validation
	 * @param result
	 *            A validation result object, pre-populated by the verification
	 *            process
	 * @return An immutable ValidationMetadata instance created from the
	 *         parameters
	 */
	public final static ValidationMetadata fromValues(PdfaFlavour flavour,
			ValidationResult result) {
		return new ValidationMetadata(flavour, result);
	}
}
