package fxviewer;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;


public class Main {

	private static void showHelp(Options options, String[] args)	{
		HelpFormatter formatter = new HelpFormatter();
		formatter.setWidth(80);;
		System.out.println("");
		System.out.println("###############################################################################");
		System.out.println("\t\t\t   How to use UIAutomator");
		System.out.println("###############################################################################");
		System.out.println("");

		if(args.length > 0) {
			if(args[0].equals("run")) {
				runHelp(options, formatter);
			}
			else if(args[0].equals("rerun")) {
				runHelp(options, formatter);
			}
			else if(args[0].equals("ui")) {
				uiHelp(options, formatter);
			}
			else {
				uiHelp(options, formatter);
				runHelp(options, formatter);
			}
		}
		else {
			uiHelp(options, formatter);
			runHelp(options, formatter);
		}
		System.exit(0);
	}

	private static void uiHelp(Options options, HelpFormatter formatter) {
		Options tmpOptions = new Options();
		tmpOptions.addOption(options.getOption("help"));
		formatter.printHelp("uiautomator.jar ui", "", tmpOptions, "", true);
	}

	private static void runHelp(Options options, HelpFormatter formatter) {
		Options tmpOptions;
		System.out.println("");
		tmpOptions = new Options();
		tmpOptions.addOption(options.getOption("help"));
		tmpOptions.addOption(options.getOption("project"));
		tmpOptions.addOption(options.getOption("report"));
		formatter.printHelp("uiautomator.jar <run/rerun>", "", tmpOptions, "", true);

		System.out.println("");
		tmpOptions = new Options();
		tmpOptions.addOption(options.getOption("help"));
		tmpOptions.addOption(options.getOption("testcase"));
		tmpOptions.addOption(options.getOption("report"));
		formatter.printHelp("uiautomator.jar <run/rerun>", "", tmpOptions, "", true);
	}

	private static void createOptions(Options options)	{
		Option helpOption = Option.builder("h")
				.longOpt( "help" )
				.desc( "Help"  )
				.hasArg(false)
				.build();
		options.addOption( helpOption );

		Option projectOption = Option.builder("p")
				.longOpt( "project" )
				.desc( "project type may be sampleapp or storeapp"  )
				.hasArg(true)
				.argName( "project type" )
				.build();
		options.addOption( projectOption );

		Option testcaseOption = Option.builder("t")
				.longOpt( "testcase" )
				.desc( "testcase (*.tc) file relative or absolute path"  )
				.hasArg(true)
				.argName( "testcase path" )
				.build();
		options.addOption( testcaseOption );

		Option reportPathOption = Option.builder("r")
				.longOpt( "report" )
				.desc( "Report file absolute/relative directory or path to *.xls. "
						+ "If no report option (-r) given then report will be saved to configured directory" )
				.hasArg(true)
				.argName( "report path" )
				.build();
		options.addOption( reportPathOption );
	}

	private static boolean hasOption(Options options, String[] args, String option){
		CommandLineParser parser = new DefaultParser();
		boolean setOption = false;
		try {
			CommandLine line = parser.parse( options, args );

			if( line.hasOption( option )) {
				setOption = true;
			}
		} catch( ParseException exp ) {
			setOption = false;
		}
		return setOption;
	}

	private static String parseOptions(Options options, String[] args, String option) {
		CommandLineParser parser = new DefaultParser();
		String value = "";
		boolean setOption = false;
		try {
			CommandLine line = parser.parse( options, args );

			if( line.hasOption( option )) {
				setOption = true;
				value = line.getOptionValue( option );

				if(value != null) {
					value.trim();
				}
			}
		} catch( ParseException exp ) {
			System.out.println( "Unexpected option: " + exp.getMessage() );
			System.exit(0);
		}

		if(setOption) {
			if(option == "project") {
				if(!(value.equals("sampleapp") || value.equals("storeapp"))) {
					System.out.println("Incorrect parameter: --project " + value);
					System.exit(0);
				}
			}else if(option == "help") {
				return "help";
			}
		}

		return value;
	}

	public static void main(String[] args) {
		System.setProperty("java.net.useSystemProxies", "true");
		Options options;
		options = new Options();
		createOptions(options);

		String reportFilePath = null;
		String mode = null;

		String help = parseOptions(options, args, "help");

		if(help.equals("help")) {
			showHelp(options, args);
			System.exit(0);
		}

		if(args.length <= 0 || args.length > 5) {
			showHelp(options, args);
			System.exit(0);
		}

		if(args.length == 1) {
			if(args[0].equals("ui")) {
				UIAutomator.runUI(args);
			}
			else {
				showHelp(options, args);
			}
		}
		else if(args.length == 3) {
			if(hasOption(options, args, "project")){
				runWithProject(args, options, reportFilePath, mode);
			}else if(hasOption(options, args, "testcase")){
				runWithTestcase(args, options, reportFilePath, mode);
			}else{
				showHelp(options, args);
			}
		}
		else if(args.length == 5) {
			reportFilePath = parseOptions(options, args, "report");

			if(hasOption(options, args, "project")){
				runWithProject(args, options, reportFilePath, mode);
			}else if(hasOption(options, args, "testcase")){
				runWithTestcase(args, options, reportFilePath, mode);
			}else{
				showHelp(options, args);
			}
		}
		else {
			showHelp(options, args);
		}
	}

	private static void runWithProject(String[] args, Options options, String reportFilePath, String mode) {
		if(args[0].equals("run") || args[0].equals("rerun")) {
			mode = args[0];
		}else {
			showHelp(options, args);
		}

		String project = parseOptions(options, args, "project");

		if(project != "" && reportFilePath != ""){
			UIAutomator.runProject(mode, project, reportFilePath);
		}else{
			showHelp(options, args);
		}

	}

	private static void runWithTestcase(String[] args, Options options, String reportFilePath, String mode) {
		if(args[0].equals("run") || args[0].equals("rerun")) {
			mode = args[0];
		}else {
			showHelp(options, args);
		}
		String testCasePath = parseOptions(options, args, "testcase");

		if(testCasePath != "" && testCasePath.endsWith(".tc") && reportFilePath != ""){
			UIAutomator.runTestCase(mode, testCasePath, reportFilePath);
		}else{
			showHelp(options, args);
		}

	}

}
