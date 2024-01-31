package com.alwaysofflinesoftware.testify.GuiBot;

import lc.kra.system.mouse.GlobalMouseHook;
import lc.kra.system.mouse.event.GlobalMouseAdapter;
import lc.kra.system.mouse.event.GlobalMouseEvent;
import com.alwaysofflinesoftware.testify.Util.SimpleLog;
import com.alwaysofflinesoftware.testify.Util.Winfo;
import com.alwaysofflinesoftware.testify.Runtime.App;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class WindowID implements Runnable{
    // might throw a UnsatisfiedLinkError if the native library fails to load or
    // a RuntimeException if hooking fails
    GlobalMouseHook mouseHook = new GlobalMouseHook();
    private static final Map<String, String> keyMap = new HashMap<>();
    private static boolean run = true;
    String curWinTitle;

    public void run() {
        runInputHooker();
    }

    // Tracks mouse click and retrieves clicked window title
    public void runInputHooker() {
        run= true;
        SimpleLog.info("Global mouse hook successfully started");
        mouseHook.addMouseListener(new GlobalMouseAdapter() {
            @Override
            public void mousePressed(GlobalMouseEvent event) {
                curWinTitle = Winfo.getActiveWindowTitle();
                if(Objects.equals(curWinTitle, "")){
                    curWinTitle="Unable_to_ID";
                }
                run= false;
            }
        });

        try {
            while (run) {
                Thread.sleep(128);
            }
            App.setTempStatus(4);
            mouseHook.shutdownHook();
            Thread.currentThread().interrupt();
            Thread.currentThread().stop();
        } catch (InterruptedException ignored) {
        }finally {
            SimpleLog.CreateLog();
        }
    }
}
