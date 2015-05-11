package org.verapdf.cli.commands;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

/**
 * This class holds all command line options used by VeraPDF application.
 * @author Timur Kamalov
 */
@Parameters(commandNames = "verapdf")
public class CommandVeraPDF extends Command {

    @Parameter(names = "-validate", required = true)
    private boolean validate;

    @Parameter(names = "-input", required = true)
    private String inputPath;

    @Parameter(names = "-urlinput")
    private boolean inputPathURL;


    @Parameter(names = "-pdfa")
    private String flavour;

    @Parameter(names = "-profile")
    private String profile;

    @Parameter(names = "-fixmetadata")
    private boolean fixMetadata;

    @Parameter(names = "-verbosity")
    private Integer verbosity = 3;


    @Parameter(names = "-progress")
    private boolean progress;

    @Parameter(names = "-stdout")
    private boolean progressToStdout;

    @Parameter(names = "-progresspath")
    private String progressPath;

    @Parameter(names = "-urlprogress")
    private boolean progressPathURL;


    @Parameter(names = "-stoperrors")
    private Integer stopErrors;

    @Parameter(names = "-tempdir")
    private String tempDir;


    @Parameter(names = "-output")
    private boolean output;

    @Parameter(names = "-inputpath")
    private boolean outputToInput;

    @Parameter(names = "-outputpath")
    private String outputPath;

    @Parameter(names = "-urloutput")
    private boolean outputPathURL;


    @Parameter(names = "-report", required = true)
    private String report;

    /**
     * @return the validate
     */
    public boolean isValidate() {
        return validate;
    }

    /**
     * @return the inputPath
     */
    public String getInputPath() {
        return inputPath;
    }

    /**
     * @return the inputPathURL
     */
    public boolean isInputPathURL() {
        return inputPathURL;
    }

    /**
     * @return the flavour
     */
    public String getFlavour() {
        return flavour;
    }

    /**
     * @return the profile
     */
    public String getProfile() {
        return profile;
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
    public Integer getVerbosity() {
        return verbosity;
    }

    /**
     * @return the progress
     */
    public boolean isProgress() {
        return progress;
    }

    /**
     * @return the progressToStdout
     */
    public boolean isProgressToStdout() {
        return progressToStdout;
    }

    /**
     * @return the progressPath
     */
    public String getProgressPath() {
        return progressPath;
    }

    /**
     * @return the progressPathURL
     */
    public boolean isProgressPathURL() {
        return progressPathURL;
    }

    /**
     * @return the stopErrors
     */
    public Integer getStopErrors() {
        return stopErrors;
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
    public boolean isOutput() {
        return output;
    }

    /**
     * @return the outputToInput
     */
    public boolean isOutputToInput() {
        return outputToInput;
    }

    /**
     * @return the outputPath
     */
    public String getOutputPath() {
        return outputPath;
    }

    /**
     * @return the outputPathURL
     */
    public boolean isOutputPathURL() {
        return outputPathURL;
    }

    /**
     * @return the report
     */
    public String getReport() {
        return report;
    }
}