/**
 * 
 */
package org.verapdf;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.fixMetadata ? 1231 : 1237);
		result = prime * result
				+ ((this.flavour == null) ? 0 : this.flavour.hashCode());
		result = prime * result + this.stopErrors;
		result = prime * result + (this.validate ? 1231 : 1237);
		result = prime * result + this.verbosity;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof VeraPdfTaskConfig)) {
			return false;
		}
		VeraPdfTaskConfig other = (VeraPdfTaskConfig) obj;
		if (this.fixMetadata != other.fixMetadata) {
			return false;
		}
		if (this.flavour != other.flavour) {
			return false;
		}
		if (this.stopErrors != other.stopErrors) {
			return false;
		}
		if (this.validate != other.validate) {
			return false;
		}
		if (this.verbosity != other.verbosity) {
			return false;
		}
		return true;
	}

	/**
	 * @return the default VeraPdfTaskConfig instance
	 */
	public final static VeraPdfTaskConfig defaultInstance() {
		return DEFAULT_INSTANCE;
	}

	/**
	 * Factory method used to create VeraPdfTaskConfig instances from values.
	 * This method will check the arguments against illegal values and throw the
	 * appropriate runtime errors.
	 * 
	 * @param flavour
	 *            enumeration indicating the PDF/A flavour to validate against
	 * @param validate
	 *            boolean set true to request PDF/A validation, false disable.
	 * @param fixMetadata
	 *            boolean set true to request metadata fixes, false to disable.
	 * @param verbosity
	 *            integer for requesting verbosity level, should be in range 0 -
	 *            9
	 * @param stopErrors
	 *            requests that validation be stopped after encountering this
	 *            number of errors, 0 indicates don't stop and show all errors
	 * @return an immutable VeraPdfTaskConfig object populated from the values
	 * @throws NullPointerException
	 *             if the PdfaFlavour flavour argument is null.
	 * @throws IllegalArgumentException
	 *             if the int verbosity is outside the range 0-9
	 * @throws IllegalArgumentException
	 *             if the int stopErrors is negative.
	 */
	public final static VeraPdfTaskConfig fromValues(final PdfaFlavour flavour,
			final boolean validate, final boolean fixMetadata,
			final int verbosity, final int stopErrors) {
		// Check that flavour is not null
		Preconditions.checkNotNull(flavour,
				"Expected PdfaFlavour to be non-null");
		// Check that verbosity is between 0 - 9
		Preconditions.checkArgument(
				((verbosity >= VERBOSITY_MIN) && (verbosity <= VERBOSITY_MAX)),
				"Expected verbosity >= %s and <= %s, but %s is outside range.",
				Integer.valueOf(VERBOSITY_MIN), Integer.valueOf(VERBOSITY_MAX),
				Integer.valueOf(verbosity));
		// Check that stopErrors >= 0
		Preconditions.checkArgument((stopErrors >= 0),
				"Expected stopErrors to be >= %s but is %s",
				Integer.valueOf(STOPERRORS_DEFAULT),
				Integer.valueOf(stopErrors));
		return new VeraPdfTaskConfig(flavour, validate, fixMetadata, verbosity,
				stopErrors);
	}
}
