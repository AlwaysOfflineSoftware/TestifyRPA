package com.alwaysofflinesoftware.testify.Util;


import javax.swing.JOptionPane;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.io.File;
import java.util.Date;
import com.alwaysofflinesoftware.testify.Runtime.App;

public final class SimpleLog {

	// Creates Dynamic text And log files
	private static final String logForm= "%-43s  %s";
	private static final String PrimaryLog = "Testify_log.txt";
	private static Boolean debug= false;

	public static void toggleDebug(boolean debugMode){
		debug= debugMode;
	}

	public static void CreateLog() {
		File f = new File(PrimaryLog);
		if(!f.exists()){
			FileUtils.createFile(PrimaryLog,String.format(logForm, "LOG INFO ", "Date(m-d-y)_Time(h-m-s)"));
		}
	}

	public static void info(String entry){
		if(!debug){
			Date date= new Date();
			FileUtils.writeToFile(PrimaryLog,String.format(logForm, ("info: " + entry + " @ "), date));
		}else{
			System.out.println(entry);
		}
		App.writeToGUIconsole(entry);
	}

	public static void warning(String entry){
		if(!debug){
			Date date= new Date();
			FileUtils.writeToFile(PrimaryLog,String.format(logForm, ("Warning: " + entry + " @ "), date));
		}else{
			System.out.println(entry);
		}
		App.writeToGUIconsole("WARNING: " + entry);
	}

	public static void error(String entry){
		if(!debug){
			Date date= new Date();
			FileUtils.writeToFile(PrimaryLog,String.format(logForm, ("ERROR: " + entry + " @ "), date));
		}else{
			System.err.println(entry);
		}
		App.writeToGUIconsole("ERROR: " + entry);
	}

	public static void CreateLog(String logFile) {
		File f = new File(logFile);
		if(!f.exists()){
			FileUtils.createFile(logFile,String.format(logForm, "LOG INFO ", "Date(m-d-y)_Time(h-m-s)"));
		}
	}

	public static void info(String logFile, String entry){
		if(!debug){
			Date date= new Date();
			FileUtils.writeToFile(logFile,String.format(logForm, ("info: " + entry + " @ "), date));
		}else{
			System.out.println(entry);
		}
		App.writeToGUIconsole(entry);
	}

	public static void warning(String logFile, String entry){
		if(!debug){
			Date date= new Date();
			FileUtils.writeToFile(logFile,String.format(logForm, ("Warning: " + entry + " @ "), date));
		}else{
			System.out.println(entry);
		}
		App.writeToGUIconsole("WARNING: " + entry);
	}

	public static void error(String logFile, String entry){
		if(!debug){
			Date date= new Date();
			FileUtils.writeToFile(logFile,String.format(logForm, ("ERROR: " + entry + " @ "), date));
		}else{
			System.err.println(entry);
		}
		App.writeToGUIconsole("ERROR: " + entry);
	}

	// Creates a dialog box for warnings or errors
	public static void dialog(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	// Gets memory usage and compares to total allotted
	public static String getMemUsage() {
		String memStats;
		long heapSize = Runtime.getRuntime().totalMemory();
		// Get maximum size of heap in bytes. The heap cannot grow beyond this size.
		long heapMaxSize = Runtime.getRuntime().maxMemory();
		// Get amount of free memory within the heap in bytes. This size will increase
		long heapFreeSize = Runtime.getRuntime().freeMemory();

		memStats= "heap size " + formatMemOut(heapSize);
		memStats+= "  heapMaxSize " + formatMemOut(heapMaxSize);
		memStats+= "  heapFreeSize " + formatMemOut(heapFreeSize);
		return memStats;
	}

	// Formats memory use info to be more human-readable
	private static String formatMemOut(long v) {
		if (v < 1024)
			return v + " B";
		int z = (63 - Long.numberOfLeadingZeros(v)) / 10;
		return String.format("%.1f %sB", (double) v / (1L << (z * 10)), " KMGTPE".charAt(z));
	}

	// Used for taking screenshots, this is for the entire screen
	public static void screenshot(String picName){
		try {
			Robot camera= new Robot();
			double[] dem= SysUtils.getScreenResolution();
			Rectangle screen = new Rectangle((int) dem[0],(int) dem[1]);
			camera.createScreenCapture(screen);
		} catch (AWTException e) {
			System.err.println("ERROR: Testify could not capture screen or could not be initialized");
			e.printStackTrace();
		}
	}
}
