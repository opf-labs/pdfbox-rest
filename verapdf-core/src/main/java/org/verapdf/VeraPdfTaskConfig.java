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
	/** Default value for the verbosity parameter */
	public static final int VERBOSITY_DEFAULT = 3;
	/** Minimum value of the verbosity parameter */
	public static final int VERBOSITY_MIN = 0;
	/** Maximum value of the verbosity parameter */
	public static final int VERBOSITY_MAX = 9;

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
		return this.flavour;
	}

	/**
	 * @return the validate
	 */
	public boolean isValidate() {
		return this.validate;
	}

	/**
	 * @return the fixMetadata
	 */
	public boolean isFixMetadata() {
		return this.fixMetadata;
	}

	/**
	 * @return the verbosity
	 */
	public int getVerbosity() {
		return this.verbosity;
	}

	/**
	 * @return the stopErrors
	 */
	public int getStopErrors() {
		return this.stopErrors;
	}

	/**
	 * @return the default VeraPdfTaskConfig instance
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
		Preconditions.checkNotNull(flavour, "Expected PdfaFlavour to be non-null");
		// Check that verbosity is between 0 - 9
		Preconditions.checkArgument(((verbosity >= VERBOSITY_MIN) && (verbosity <= VERBOSITY_MAX)),
				"Expected verbosity >= %s and <= %s, but %s is outside range.", Integer.valueOf(VERBOSITY_MIN), Integer.valueOf(VERBOSITY_MAX), Integer.valueOf(verbosity));
		// Check that stopErrors >= 0
		Preconditions.checkArgument((stopErrors >= 0),
				"Expected stopErrors to be >= %s but is %s", Integer.valueOf(STOPERRORS_DEFAULT), Integer.valueOf(stopErrors));
		return new VeraPdfTaskConfig(flavour, validate, fixMetadata,
				verbosity, stopErrors);
	}
}
