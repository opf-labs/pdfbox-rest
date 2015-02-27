package org.verapdf.cli.commands;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

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

    public boolean isValidate() {
        return validate;
    }

    public String getInputPath() {
        return inputPath;
    }

    public boolean isInputPathURL() {
        return inputPathURL;
    }

    public String getFlavour() {
        return flavour;
    }

    public String getProfile() {
        return profile;
    }

    public boolean isFixMetadata() {
        return fixMetadata;
    }

    public Integer getVerbosity() {
        return verbosity;
    }

    public boolean isProgress() {
        return progress;
    }

    public boolean isProgressToStdout() {
        return progressToStdout;
    }

    public String getProgressPath() {
        return progressPath;
    }

    public boolean isProgressPathURL() {
        return progressPathURL;
    }

    public Integer getStopErrors() {
        return stopErrors;
    }

    public String getTempDir() {
        return tempDir;
    }

    public boolean isOutput() {
        return output;
    }

    public boolean isOutputToInput() {
        return outputToInput;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public boolean isOutputPathURL() {
        return outputPathURL;
    }

    public String getReport() {
        return report;
    }
}