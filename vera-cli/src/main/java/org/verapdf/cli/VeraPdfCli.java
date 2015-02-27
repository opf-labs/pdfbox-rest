/**
 *
 */
package org.verapdf.cli;

import com.beust.jcommander.JCommander;
import org.verapdf.VeraPdfTaskConfig;
import org.verapdf.cli.commands.CommandVeraPDF;

/**
 * @author cfw
 *
 */
public class VeraPdfCli {

    /**
     * The application name, used to invoke the CLI application
     */
    public static final String APP_NAME = "veraPdf"; //$NON-NLS-1$

    private static final CommandVeraPDF commandVeraPDF;

    static {
        commandVeraPDF = new CommandVeraPDF();
    }

	/**
	 * Main CLI entry point, process the command line arguments
	 *
	 * @param args
	 *            Java.lang.String array of command line args, to be processed
	 *            using Apache commons CLI.
	 */
	public static void main(String[] args) {
        JCommander jCommander = new JCommander();
        jCommander.addCommand(commandVeraPDF);
        VeraPdfTaskConfig taskConfig = VeraPdfTaskConfig.defaultInstance();

        jCommander.parse(args);
        taskConfig = createConfigFromCliOptions(commandVeraPDF);
    }

    //TODO: throw correct exception
    private final static VeraPdfTaskConfig createConfigFromCliOptions(final CommandVeraPDF commandVeraPDF) {
        //TODO: if no logic will be placed there than I prefer not to use intermediate variables
        boolean validate = commandVeraPDF.isValidate();
        String inputPath = commandVeraPDF.getInputPath();
        boolean inputPathURL = commandVeraPDF.isInputPathURL();
        String flavour = commandVeraPDF.getFlavour();
        String profile = commandVeraPDF.getProfile();
        boolean fixMetadata = commandVeraPDF.isFixMetadata();
        Integer verbosity = commandVeraPDF.getVerbosity();
        boolean progress = commandVeraPDF.isProgress();
        boolean progressToStdout = commandVeraPDF.isProgressToStdout();
        String progressPath = commandVeraPDF.getProgressPath();
        boolean progressPathURL = commandVeraPDF.isProgressPathURL();
        Integer stopErrors = commandVeraPDF.getStopErrors();
        String tempDir = commandVeraPDF.getTempDir();
        boolean output = commandVeraPDF.isOutput();
        boolean outputToInput = commandVeraPDF.isOutputToInput();
        String outputPath = commandVeraPDF.getOutputPath();
        boolean outputPathURL = commandVeraPDF.isOutputPathURL();
        String report = commandVeraPDF.getReport();
        return VeraPdfTaskConfig.fromValues(validate, inputPath, inputPathURL, flavour, profile,
                                     fixMetadata, verbosity, progress, progressToStdout, progressPath,
                                     progressPathURL, stopErrors, tempDir, output, outputToInput,
                                     outputPath, outputPathURL, report);
    }
}