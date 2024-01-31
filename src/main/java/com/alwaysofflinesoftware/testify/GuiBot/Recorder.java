package com.alwaysofflinesoftware.testify.GuiBot;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.mouse.GlobalMouseHook;
import lc.kra.system.mouse.event.GlobalMouseAdapter;
import lc.kra.system.mouse.event.GlobalMouseEvent;
import com.alwaysofflinesoftware.testify.Runtime.App;
import com.alwaysofflinesoftware.testify.Util.FileUtils;
import com.alwaysofflinesoftware.testify.Util.SimpleLog;
import com.alwaysofflinesoftware.testify.Util.Winfo;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// GuiRecord handles key and mouse logging for the creation of automatic routines
public class Recorder implements Runnable{
    // might throw a UnsatisfiedLinkError if the native library fails to load or
    // a RuntimeException if hooking fails
    GlobalMouseHook mouseHook = new GlobalMouseHook();
    // add true to the constructor, to switch to raw input mode
    GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(true);
    private static final Map<String, String> keyMap = new HashMap<>();
    private static boolean run = true;
    private static boolean trackMoves= false;
    private static boolean useSmart= false;
    private static int smartCounter= 1;
    private static List<String> uiRecord;
    String curWinTitle;
    Rectangle winRect;
    String fileForRecording;

    // Runs keymap initializer on object creation
    public Recorder(String recordFile, boolean mtrack, boolean useSm) {
        fileForRecording= recordFile;
        trackMoves= mtrack;
        useSmart= useSm;
        initKeyMap();
    }

    @Override
    public void run() {
        runInputHooker();
    }

    // Tracks inputs for all keyboard and mice events
    public void runInputHooker() {
        run= true;
        App.setStatus(2);
        uiRecord = new ArrayList<>();
        SimpleLog.info("Global mouse hook successfully started");
        mouseHook.addMouseListener(new GlobalMouseAdapter() {
            @Override
            public void mousePressed(GlobalMouseEvent event) {
                curWinTitle = Winfo.getActiveWindowTitle();
                winRect = Winfo.getWinRect(curWinTitle);
                if (!curWinTitle.equals("")) {
                    uiRecord.add("click " + (event.toString()).replace(",", " ") + " " +
                            curWinTitle.replace(" ", "_") + " " + winRect.x + " " + winRect.y +
                            " " + winRect.width + " " + winRect.height);
                }
            }

            @Override
            public void mouseReleased(GlobalMouseEvent event) {
                //uiRecord+= "release " + (event.toString()).replace(",", " ")+ ",";
            }

            @Override
            public void mouseMoved(GlobalMouseEvent event) {
                if(trackMoves){
                    if(System.currentTimeMillis()%120== 0) {
                        uiRecord.add("move " + (event.toString()).replace(",", " "));
                    }
                }
            }

            @Override
            public void mouseWheel(GlobalMouseEvent event) {
                if (event.getDelta() > 0) {
                    uiRecord.add("scroll 1");
                } else {
                    uiRecord.add("scroll -1");
                }
            }
        });

        // Listens for keyboard input
        SimpleLog.info("Global keyboard hook successfully started");
        keyboardHook.addKeyListener(new GlobalKeyAdapter() {
            @Override
            public void keyPressed(GlobalKeyEvent event) {
                if(useSmart){
                    if (!processInput(event.toString()).equals("")) {
                        if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_RETURN) {
                            uiRecord.add("specialKeyPress " + processInput(event.toString()));
                        }else{
                            uiRecord.add("smartKeys " + "1 " + smartCounter + " Fallback value");
                            smartCounter++;
                        }
                    }
                }else{
                    if ((processInput(event.toString())).length() == 1) {
                        uiRecord.add("keyPress " + processInput(event.toString()));
                    } else {
                        if (!processInput(event.toString()).equals("")) {
                            uiRecord.add("specialKeyPress " + processInput(event.toString()));
                        }
                    }
                }
                if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_ESCAPE) {
                    run = false;
                    smartCounter=1;
                    mouseHook.shutdownHook();
                    keyboardHook.shutdownHook();
                    SimpleLog.info("unhooked and exiting");
                }
            }

            @Override
            public void keyReleased(GlobalKeyEvent event) {
                //SimpleLog.info(event);
            }
        });

        try {
            FileUtils.createFile(fileForRecording);
            while (run) {
                Thread.sleep(128);
            }
            FileUtils.writeToFile(fileForRecording, uiRecord);
            App.setTempStatus(4);
        } catch (InterruptedException ignored) {
        }finally {
            SimpleLog.CreateLog();
        }
    }

    // Converts key IDs to characters
    private static String processInput(String inEvent) {
        if (keyMap.get(inEvent) != null) {
            return keyMap.get(inEvent);
        } else {
            return "NullKey";
        }
    }

    // Initializes KeyMap map for converting status messages to key chars/Strings
    private static void initKeyMap() {
        keyMap.put("32 [down]", "space");
        keyMap.put("8 [down]", "backspace");
        keyMap.put("46 [down]", "delete");
        keyMap.put("27 [down]", "");
        keyMap.put("48 [down]", "0");
        keyMap.put("49 [down]", "1");
        keyMap.put("50 [down]", "2");
        keyMap.put("51 [down]", "3");
        keyMap.put("52 [down]", "4");
        keyMap.put("53 [down]", "5");
        keyMap.put("54 [down]", "6");
        keyMap.put("55 [down]", "7");
        keyMap.put("56 [down]", "8");
        keyMap.put("57 [down]", "9");
        keyMap.put("96 [down]", "0");
        keyMap.put("97 [down]", "1");
        keyMap.put("98 [down]", "2");
        keyMap.put("99 [down]", "3");
        keyMap.put("100 [down]", "4");
        keyMap.put("101 [down]", "5");
        keyMap.put("102 [down]", "6");
        keyMap.put("103 [down]", "7");
        keyMap.put("104 [down]", "8");
        keyMap.put("105 [down]", "9");
        keyMap.put("65 [down]", "a");
        keyMap.put("66 [down]", "b");
        keyMap.put("67 [down]", "c");
        keyMap.put("68 [down]", "d");
        keyMap.put("69 [down]", "e");
        keyMap.put("70 [down]", "f");
        keyMap.put("71 [down]", "g");
        keyMap.put("72 [down]", "h");
        keyMap.put("73 [down]", "i");
        keyMap.put("74 [down]", "j");
        keyMap.put("75 [down]", "k");
        keyMap.put("76 [down]", "l");
        keyMap.put("77 [down]", "m");
        keyMap.put("78 [down]", "n");
        keyMap.put("79 [down]", "o");
        keyMap.put("80 [down]", "p");
        keyMap.put("81 [down]", "q");
        keyMap.put("82 [down]", "r");
        keyMap.put("83 [down]", "s");
        keyMap.put("84 [down]", "t");
        keyMap.put("85 [down]", "u");
        keyMap.put("86 [down]", "v");
        keyMap.put("87 [down]", "w");
        keyMap.put("88 [down]", "x");
        keyMap.put("89 [down]", "y");
        keyMap.put("90 [down]", "z");
        keyMap.put("189 [down]", "-");
        keyMap.put("187 [down]", "=");
        keyMap.put("219 [down]", "[");
        keyMap.put("221 [down]", "]");
        keyMap.put("220 [down]", "\\");
        keyMap.put("191 [down]", "/");
        keyMap.put("190 [down]", ".");
        keyMap.put("111 [down]", "/");
        keyMap.put("106 [down]", "*");
        keyMap.put("109 [down]", "-");
        keyMap.put("107 [down]", "+");
        keyMap.put("110 [down]", ".");
        keyMap.put("188 [down]", "comma");
        keyMap.put("9 [down]", "tab");
        keyMap.put("13 [down]", "enter");
        keyMap.put("112 [down]", "f1");
        keyMap.put("113 [down]", "f2");
        keyMap.put("114 [down]", "f3");
        keyMap.put("115 [down]", "f4");
        keyMap.put("116 [down]", "f5");
        keyMap.put("117 [down]", "f6");
        keyMap.put("118 [down]", "f7");
        keyMap.put("119 [down]", "f8");
        keyMap.put("120 [down]", "f9");
        keyMap.put("121 [down]", "f10");
        keyMap.put("122 [down]", "f11");
        keyMap.put("123 [down]", "f12");
        keyMap.put("91 [down]", "windows");
        keyMap.put("37 [down]", "left");
        keyMap.put("38 [down]", "up");
        keyMap.put("39 [down]", "right");
        keyMap.put("40 [down]", "down");
        //SHIFT
        keyMap.put("16 [down,shift]", "");
        keyMap.put("65 [down,shift]", "A");
        keyMap.put("66 [down,shift]", "B");
        keyMap.put("67 [down,shift]", "C");
        keyMap.put("68 [down,shift]", "D");
        keyMap.put("69 [down,shift]", "E");
        keyMap.put("70 [down,shift]", "F");
        keyMap.put("71 [down,shift]", "G");
        keyMap.put("72 [down,shift]", "H");
        keyMap.put("73 [down,shift]", "I");
        keyMap.put("74 [down,shift]", "J");
        keyMap.put("75 [down,shift]", "K");
        keyMap.put("76 [down,shift]", "L");
        keyMap.put("77 [down,shift]", "M");
        keyMap.put("78 [down,shift]", "N");
        keyMap.put("79 [down,shift]", "O");
        keyMap.put("80 [down,shift]", "P");
        keyMap.put("81 [down,shift]", "Q");
        keyMap.put("82 [down,shift]", "R");
        keyMap.put("83 [down,shift]", "S");
        keyMap.put("84 [down,shift]", "T");
        keyMap.put("85 [down,shift]", "U");
        keyMap.put("86 [down,shift]", "V");
        keyMap.put("87 [down,shift]", "W");
        keyMap.put("88 [down,shift]", "X");
        keyMap.put("89 [down,shift]", "Y");
        keyMap.put("90 [down,shift]", "Z");
        keyMap.put("48 [down,shift]", ")");
        keyMap.put("49 [down,shift]", "!");
        keyMap.put("50 [down,shift]", "@");
        keyMap.put("51 [down,shift]", "#");
        keyMap.put("52 [down,shift]", "$");
        keyMap.put("53 [down,shift]", "%");
        keyMap.put("54 [down,shift]", "^");
        keyMap.put("55 [down,shift]", "&");
        keyMap.put("56 [down,shift]", "*");
        keyMap.put("57 [down,shift]", "(");
        keyMap.put("219 [down,shift]", "{");
        keyMap.put("221 [down,shift]", "}");
        keyMap.put("220 [down,shift]", "|");
        keyMap.put("191 [down,shift]", "?");
        keyMap.put("190 [down,shift]", ">");
        keyMap.put("188 [down,shift]", "<");
        // CONTROL
        keyMap.put("17 [down,control]", "");
        keyMap.put("65 [down,control]", "selectall");
        // ALT
        keyMap.put("18 [down,menu]", "");
    }
}
