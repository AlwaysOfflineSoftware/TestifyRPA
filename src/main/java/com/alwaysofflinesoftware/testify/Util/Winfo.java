package com.alwaysofflinesoftware.testify.Util;

import com.sun.jna.Native;
import com.sun.jna.platform.WindowUtils;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.win32.StdCallLibrary;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

// Winfo handles special win32 functions for getting direct info from Windows OS on an application
public class Winfo {
	// Required for class methods, modifies standard user32
	public interface User32 extends StdCallLibrary {
	      @SuppressWarnings("deprecation")
		User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class);
	      HWND FindWindow(String lpClassName, String lpWindowName);
	      int GetWindowRect(HWND handle, int[] rect);
	   }

	// Retrieves window top left corner X & Y along with window bottom right corner X & Y
   public static Rectangle getWinRect(String windowName){
	  try {
	      HWND hwnd = User32.INSTANCE.FindWindow(null, windowName);
	      if (hwnd == null) {
	         throw new WindowNotFoundException("", windowName);
	      }else{
	    	  int[] sides = {0, 0, 0, 0};
	          int result = User32.INSTANCE.GetWindowRect(hwnd, sides);
	          if (result == 0) {
	             throw new GetWindowRectException(windowName);
	          }else{
				  return new Rectangle(sides[0],sides[1],sides[2]-100,sides[3]-100);
	          }
	      }
      }catch(WindowNotFoundException wnf){
		System.err.println("ERROR: Testify could not find the window recorded in the routine.");
		//wnf.printStackTrace();
		System.exit(0);
      }catch(GetWindowRectException gwr){
  		System.err.println("ERROR: Testify could not retrieve the window demensions recorded in the routine.");
  		//gwr.printStackTrace();
  		System.exit(0);
      }
	return null;
   }

	// Converts rectangles from window identification into dimension data array
	public static double[] getWindowDemensions(Rectangle rect) {
		double x= rect.getX();
		double y= rect.getY();
		double h= rect.getHeight();
		double w= rect.getWidth();
		return new double[]{x,y,w,h};
	}

	// Gets active windows title
	public static String getActiveWindowTitle() {
		HWND hwnd = com.sun.jna.platform.win32.User32.INSTANCE.GetForegroundWindow();
		return WindowUtils.getWindowTitle(hwnd);
	}
   
   public static HWND getWindowHWND(String windowName) {
	   return User32.INSTANCE.FindWindow(null, windowName);
   }
   
   // Takes screenshot of specific window and not the entire screen
   public static File captureWinApp(String windowName) throws Exception{
	   Robot r = new Robot();
	   HWND hwnd = User32.INSTANCE.FindWindow(null, windowName);
	   Rectangle rect;
	   SysUtils.keepFocus(hwnd);
	  Thread.sleep(500);
	  rect = getWinRect(windowName);
	  BufferedImage i = r.createScreenCapture(rect);
	  File file = new File(windowName + ".png");
	  ImageIO.write(i, "png", file);
	  return file; 
   }

   // Used in exception handling
   @SuppressWarnings("serial")
   public static class WindowNotFoundException extends Exception {
      public WindowNotFoundException(String className, String windowName) {
         super(String.format("Window null for className: %s; windowName: %s", 
                  className, windowName));
      }
   }

   // Used in exception handling
   @SuppressWarnings("serial")
   public static class GetWindowRectException extends Exception {
      public GetWindowRectException(String windowName) {
         super("Window Rect not found for " + windowName);
      }
   }

	public void adjustWindow(String windowName, int fixedX, int fixedY, int curX, int curY) {
		HWND hwnd = com.sun.jna.platform.win32.User32.INSTANCE.FindWindow(null, windowName);
		com.sun.jna.platform.win32.User32.INSTANCE.MoveWindow(hwnd, fixedX, fixedY, curX, curY, false);
	}

	// BROKEN
	// Makes bot wait up to 10 seconds for windows to load
	@SuppressWarnings("unused")
	private static void windowWaitCheck(String window) throws Exception {
		boolean windowFound= false;
		if(!window.equals("Start menu")) {
			while(System.currentTimeMillis()%10000!= 0) {
				if(getActiveWindowTitle().equals(window)) {
					System.out.println("2");
					windowFound= true;
					break;
				}
			}
			if(windowFound) {
				//HWND hwndWindow= Winfo.getWindowHWND(window);
				//Tasker.keepFocus(hwndWindow);
			}else {
				System.err.println("ERROR: Bot timed out waiting for window or could not find the window");
				System.exit(0);
			}
		}
	}
}
