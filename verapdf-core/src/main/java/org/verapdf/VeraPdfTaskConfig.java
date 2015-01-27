/**
 * 
 */
package org.verapdf;

import java.net.URI;

import org.verapdf.pdfa.spec.PdfaFlavour;

import com.google.common.base.Preconditions;

/**
 * Encapsulation of the VeraPDF processing configuration. This class deals only
 * with the processing and doesn't concern itself with any "non-repeatable", or
 * technical details.
 * 
 * Specifically this means that it cares not about the processing environment
 * (e.g. temp directory, how many threads, etc.). Nor does it care about the
 * particular file or stream to be operated on. It helps to think of the class
 * as capturing all aspects of an invocation that can be reused across all
 * environments and on any file.
 * 
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 */
public final class VeraPdfTaskConfig {
	/**
	 * Default value for the number of errors encountered before validation is
	 * halted. Defaults to zero which means continue validation regardless of
	 * error count.
	 */
	public static final int STOPERRORS_DEFAULT = 0;
	/**
	 * Default value for the verbosity parameter
	 */
	public static final int VERBOSITY_DEFAULT = 3;

	private static final VeraPdfTaskConfig DEFAULT_INSTANCE = new VeraPdfTaskConfig();

	private final PdfaFlavour flavour;
	private final boolean validate;
	private final boolean fixMetadata;
	private final int verbosity;
	private final int stopErrors;

	private VeraPdfTaskConfig() {
		this(PdfaFlavour.PDFA_1_B, true, false, VERBOSITY_DEFAULT,
				STOPERRORS_DEFAULT);
	}

	private VeraPdfTaskConfig(final PdfaFlavour flavour,
			final boolean validate, final boolean fixMetadata,
			final int verbosity, final int stopErrors) {
		this.flavour = flavour;
		this.validate = validate;
		this.fixMetadata = fixMetadata;
		this.verbosity = verbosity;
		this.stopErrors = stopErrors;
	}

	/**
	 * @return the flavour
	 */
	public PdfaFlavour getFlavour() {
		return flavour;
	}

	/**
	 * @return the validate
	 */
	public boolean isValidate() {
		return validate;
	}

	/**
	 * @return the fixMetadata
	 */
	public boolean isFixMetadata() {
		return fixMetadata;
	}

	/**
	 * @return the verbosity
	 */
	public int getVerbosity() {
		return verbosity;
	}

	/**
	 * @return the stopErrors
	 */
	public int getStopErrors() {
		return stopErrors;
	}

	/**
	 * @return
	 */
	public final static VeraPdfTaskConfig defaultInstance() {
		return DEFAULT_INSTANCE;
	}

	/**
	 * @param flavour
	 * @param validate
	 * @param fixMetadata
	 * @param verbosity
	 * @param stopErrors
	 * @return
	 */
	public final static VeraPdfTaskConfig fromValues(
			final PdfaFlavour flavour, final boolean validate,
			final boolean fixMetadata, final int verbosity, final int stopErrors) {
		// Check that flavour is not null
		Preconditions.checkNotNull(flavour, "PdfaFlavour cannot be null");
		// Check that verbosity is between 0 - 9
		Preconditions.checkArgument(((verbosity >= 0) && (verbosity <= 9)),
				"verboity must an integer from 0-9");
		// Check that stopErrors >= 0
		Preconditions.checkArgument((stopErrors >= 0),
				"stopErrors must be >= 0");
		return new VeraPdfTaskConfig(flavour, validate, fixMetadata,
				verbosity, stopErrors);
	}
}
