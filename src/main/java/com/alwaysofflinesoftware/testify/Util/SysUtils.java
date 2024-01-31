package com.alwaysofflinesoftware.testify.Util;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.mouse.GlobalMouseHook;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;

///Utility handles misc screen/file/instruction information processes that don't fit elsewhere
public class SysUtils {

	public static void delay(int milliseconds) {
		long startTime = System.currentTimeMillis();
		//System.out.println(startTime);
		while(true){
			long now = System.currentTimeMillis();
			if ((now - startTime) >= milliseconds){
				//System.out.println(now);
				break;
			}
		}
	}

	// Gets local path of file
	public static String getlocalPath(String file){
		File currentDirFile = new File(file);
		return currentDirFile.getAbsolutePath();
	}
	
	// Retrieves screen resolution.
	public static double[] getScreenResolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		return new double[]{ width, height };
	}

	// When window loses focus, gets focus back
	public static void keepFocus(HWND window){
		if (User32.INSTANCE.GetForegroundWindow() != window) {
			User32.INSTANCE.ShowWindow(window, 9); // Un-minimize if minimized
			User32.INSTANCE.SetForegroundWindow(window); // bring to front
		}
	}
	
	// Outputs input device IDs
	public static void listInputDevices(){
		for(Entry<Long,String> keyboard:GlobalKeyboardHook.listKeyboards().entrySet()){
			System.out.format("%d: %s\n", keyboard.getKey(), keyboard.getValue());
		}
		for(Entry<Long,String> mouse:GlobalMouseHook.listMice().entrySet()){
			System.out.format("%d: %s\n", mouse.getKey(), mouse.getValue());
		}
	}

	// Opens an application from it's exe
	public static void openApp(String fileName) throws InterruptedException {
		try {
			Runtime run = Runtime.getRuntime();
			run.exec(fileName);
			Thread.sleep(2000);
			HWND hwnd = User32.INSTANCE.GetForegroundWindow();
			Thread.sleep(100);
			SimpleLog.info("App" + fileName + "Opened");
		}catch(IOException i) {
			SimpleLog.error("ERROR: Testify could not run the file: " + fileName);
		}
	}

	// Retrieves location of project path
	public static String getFilePath(String extraPath) {
		String absolutePath = new File(".").getAbsolutePath();
		int last = absolutePath.length()-1;
		absolutePath = absolutePath.substring(0, last);
		return absolutePath + extraPath;
	}
}
