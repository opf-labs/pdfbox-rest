/**
 * 
 */
package org.verapdf.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.verapdf.VeraPdfTaskConfig;
import org.verapdf.pdfa.spec.PdfaFlavour;
import org.verapdf.pdfa.spec.PdfaSpecifications;

/**
 * @author cfw
 *
 */
public class VeraPdfCli {
	/**
	 * The application name, used to invoke the CLI application
	 */
	public static final String APP_NAME = "veraPdf"; //$NON-NLS-1$
	
	// String constants for CLI Options
	private static final String VALIDATE_OPT = "validate"; //$NON-NLS-1$
	private static final String VALIDATE_OPT_DESC = "Request PDF/A validation";
	private static final String FILE_OPT = "file"; //$NON-NLS-1$
	private static final String FILE_OPT_ARG = "filepath";
	private static final String FILE_OPT_DESC = "Absolute path of file to validate.";
	private static final String FIXMETADATA_OPT = "fixmetadata"; //$NON-NLS-1$
	private static final String FIXMETADATA_OPT_DESC = "Request automatic fix of metadata.";
	private static final String URL_OPT = "url"; //$NON-NLS-1$
	private static final String URL_OPT_ARG = "URL";
	private static final String URL_OPT_DESC = "URI encoded URL of file to validate.";
	private static final String PDFA_OPT = "pdfa"; //$NON-NLS-1$
	private static final String PDFA_OPT_ARG = "PDF/A flavour";
	private static final String PDFA_OPT_DESC = "PDF/A flavour to use for validation, can be (none|1a|1b|2a|2b|3a|3b|3u).";
	private static final String STOPERRORS_OPT = "stoperrors"; //$NON-NLS-1$
	private static final String STOPERRORS_OPT_ARG = "number";
	private static final String STOPERRORS_OPT_DESC = "The number of errors after which validation is interupted, must be an integer greater than 0. Default is to not stop on any error.";
	private static final String VERBOSITY_OPT = "verbosity"; //$NON-NLS-1$
	private static final String VERBOSITY_OPT_ARG = "number";
	private static final String VERBOSITY_OPT_DESC = "Control reporting verbosity, a number between 0 and 9 (inclusive), defaults to 3.";
	private static final String HELP_OPT = "help"; //$NON-NLS-1$
	private static final String HELP_OPT_DESC = "Print this message.";

	// Create the options object
	private static final Options OPTIONS = new Options();
	static {
		Option help = new Option(HELP_OPT, HELP_OPT_DESC);
		Option validate = new Option(VALIDATE_OPT, VALIDATE_OPT_DESC);
		Option fixMetadata = new Option(FIXMETADATA_OPT, FIXMETADATA_OPT_DESC);
		@SuppressWarnings("static-access")
		Option file = OptionBuilder.withArgName(FILE_OPT_ARG).hasArg()
				.withDescription(FILE_OPT_DESC).create(FILE_OPT);
		@SuppressWarnings("static-access")
		Option url = OptionBuilder.withArgName(URL_OPT_ARG).hasArg()
				.withDescription(URL_OPT_DESC).create(URL_OPT);
		@SuppressWarnings("static-access")
		Option pdfa = OptionBuilder.withArgName(PDFA_OPT_ARG).hasArg()
				.withDescription(PDFA_OPT_DESC).create(PDFA_OPT);
		@SuppressWarnings("static-access")
		Option stopErrors = OptionBuilder.withArgName(STOPERRORS_OPT_ARG)
				.hasArg().withDescription(STOPERRORS_OPT_DESC)
				.create(STOPERRORS_OPT);
		@SuppressWarnings("static-access")
		Option verbosity = OptionBuilder.withArgName(VERBOSITY_OPT_ARG)
				.hasArg().withDescription(VERBOSITY_OPT_DESC)
				.create(VERBOSITY_OPT);

		OPTIONS.addOption(help);
		OPTIONS.addOption(validate);
		OPTIONS.addOption(fixMetadata);
		OPTIONS.addOption(file);
		OPTIONS.addOption(url);
		OPTIONS.addOption(pdfa);
		OPTIONS.addOption(verbosity);
		OPTIONS.addOption(stopErrors);
	}

	/**
	 * Main CLI entry point, process the command line arguments
	 * 
	 * @param args
	 *            Java.lang.String array of command line args, to be processed
	 *            using Apache commons CLI.
	 */
	public static void main(String[] args) {
		CommandLineParser gnuParser = new GnuParser();
		VeraPdfTaskConfig taskConfig = VeraPdfTaskConfig.defaultInstance();
		try {
			CommandLine cmdLine = gnuParser.parse(OPTIONS, args);
			
			// If help requested then output help message and terminate
			if (cmdLine.hasOption(HELP_OPT) || args.length == 0) {
				outputHelpAndTerminate(0);
			}
			
			taskConfig = createConfigFromCliOptions(cmdLine);
		} catch (ParseException excep) {
			System.err.println("Command line parsing failed, exception message: " + excep.getLocalizedMessage());
			excep.printStackTrace();
			outputHelpAndTerminate(1);
		}
		
	}

	private final static void outputHelpAndTerminate(final int exitCode) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp(APP_NAME, OPTIONS);
		System.exit(exitCode);
	}
	
	private final static VeraPdfTaskConfig createConfigFromCliOptions(final CommandLine cmdLine) {
		boolean validate = cmdLine.hasOption(VALIDATE_OPT);
		boolean fixMetadata = cmdLine.hasOption(FIXMETADATA_OPT);
		PdfaFlavour flavour = PdfaFlavour.NONE;
		if (cmdLine.hasOption(PDFA_OPT)) {
			String flavourString = cmdLine.getOptionValue(PDFA_OPT);
			flavour = PdfaSpecifications.byFlavourString(flavourString);
		}
		int verbosity = VeraPdfTaskConfig.VERBOSITY_DEFAULT;
		if (cmdLine.hasOption(VERBOSITY_OPT)) {
			verbosity = Integer.valueOf(cmdLine.getOptionValue(VERBOSITY_OPT)).intValue();
		}
		int stopErrors = VeraPdfTaskConfig.STOPERRORS_DEFAULT;
		if (cmdLine.hasOption(STOPERRORS_OPT)) {
			stopErrors = Integer.valueOf(cmdLine.getOptionValue(STOPERRORS_OPT)).intValue();
		}
		return VeraPdfTaskConfig.fromValues(flavour, validate, fixMetadata, verbosity, stopErrors);
	}
}