/**
 * 
 */
package org.verapdf;

import com.google.common.base.Preconditions;
import org.verapdf.pdfa.spec.PdfaFlavour;

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

    private final boolean validate;
    private final Input input;
	private final PdfaFlavour flavour;
    private final String profile;
	private final boolean fixMetadata;
	private final int verbosity;
    private final Progress progress;
	private final int stopErrors;
    private final String tempDir;
    private final Output output;
    private final String report;

	private VeraPdfTaskConfig() {
		this(false, null, PdfaFlavour.PDFA_1_B, null, false, VERBOSITY_DEFAULT, null,
				STOPERRORS_DEFAULT, null, null, null);
	}

    public VeraPdfTaskConfig(boolean validate, Input input, PdfaFlavour flavour,
                             String profile, boolean fixMetadata, int verbosity,
                             Progress progress, int stopErrors, String tempdir,
                             Output output, String report) {
        this.validate = validate;
        this.input = input;
        this.flavour = flavour;
        this.profile = profile;
        this.fixMetadata = fixMetadata;
        this.verbosity = verbosity;
        this.progress = progress;
        this.stopErrors = stopErrors;
        this.tempDir = tempdir;
        this.output = output;
        this.report = report;
    }

    public VeraPdfTaskConfig(boolean validate, String inputPath, boolean inputPathURL,
                             String flavour, String profile, boolean fixMetadata,
                             Integer verbosity, boolean progress, boolean progressToStdout,
                             String progressPath, boolean progressPathURL, Integer stopErrors,
                             String tempDir, boolean output, boolean outputToInput,
                             String outputPath, boolean outputPathURL, String report) {
        this.validate = validate;
        this.input = new Input(inputPath, inputPathURL);
        //TODO: probably move this logic tto PdfaFlavour class?
        switch (flavour) {
            case "1a" : this.flavour = PdfaFlavour.PDFA_1_A;
                break;
            case "1b" : this.flavour = PdfaFlavour.PDFA_1_B;
                break;
            case "2a" : this.flavour = PdfaFlavour.PDFA_2_A;
                break;
            case "2b" : this.flavour = PdfaFlavour.PDFA_2_B;
                break;
            case "3a" : this.flavour = PdfaFlavour.PDFA_3_A;
                break;
            case "3b" : this.flavour = PdfaFlavour.PDFA_3_B;
                break;
            case "3u" : this.flavour = PdfaFlavour.PDFA_3_U;
                break;
            case "none" : this.flavour = PdfaFlavour.NONE;
                break;
            default : this.flavour = PdfaFlavour.NONE;
                break;
        }
        this.profile = profile;
        this.fixMetadata = fixMetadata;
        this.verbosity = verbosity;
        if (progress) {
            this.progress = new Progress(progressToStdout, progressPath, progressPathURL);
        } else this.progress = null;
        this.stopErrors = stopErrors;
        this.tempDir = tempDir;
        if (output) {
            this.output = new Output(outputToInput, outputPath, outputPathURL);
        } else this.output = null;
        this.report = report;
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
     * @return the input
     */
    public Input getInput() {
        return input;
    }

    /**
     * @return the profile
     */
    public String getProfile() {
        return profile;
    }

    /**
     * @return the progress
     */
    public Progress getProgress() {
        return progress;
    }

    /**
     * @return the tempDir
     */
    public String getTempDir() {
        return tempDir;
    }

    /**
     * @return the output
     */
    public Output getOutput() {
        return output;
    }

    /**
     * @return the report
     */
    public String getReport() {
        return report;
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
	public final static VeraPdfTaskConfig fromValues(final boolean validate,
            final String inputPath, final boolean inputPathURL, final String flavour,
			final String profile, final boolean fixMetadata, final Integer verbosity,
			final boolean progress, final boolean progressToStdout, final String progressPath,
            final boolean progressPathURL, final Integer stopErrors, final String tempDir,
            final boolean output, final boolean outputToInput, final String outputPath,
            final boolean outputPathURL, final String report) {
        //Check that input path is defined
        Preconditions.checkNotNull(inputPath);
        //if validate is true we need to check validation profile
        if (validate) {
            // Check that flavour is not null if profile is null
            if (profile.isEmpty()) {
                Preconditions.checkNotNull(flavour,
                        "Expected PdfaFlavour to be non-null if profile is not defined");
            }
            // Check that profile is not null if flavour is null
            if (flavour == null) {
                Preconditions.checkNotNull(profile,
                        "Expected profile to be non-null if PdfaFlavout is not defined");
            }
        }
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
        // Check that output is not null if fixMetadata is true
        if (fixMetadata) {
            Preconditions.checkState(output);
            Preconditions.checkNotNull(outputPath,
                    "Expected output to be non-null if fixmetadata is true");
        }
        // Check that report is not null
        Preconditions.checkNotNull(report,
                "Expected report to be non-null");
		return new VeraPdfTaskConfig(validate, inputPath, inputPathURL, flavour, profile, fixMetadata, verbosity,
				progress, progressToStdout, progressPath, progressPathURL, stopErrors, tempDir, output, outputToInput,
                outputPath, outputPathURL, report);
	}
}
