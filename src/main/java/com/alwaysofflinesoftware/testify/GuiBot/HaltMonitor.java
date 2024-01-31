package com.alwaysofflinesoftware.testify.GuiBot;

import com.alwaysofflinesoftware.testify.Runtime.App;
import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;


public class HaltMonitor implements Runnable{
    GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(true);
    Thread processingThread;

    public HaltMonitor(Thread threadToMonitor){
        processingThread= threadToMonitor;
    }

    @Override
    public void run() {
        waitForHaltKey();
    }

    public void waitForHaltKey() {
        // Listens for keyboard input
        keyboardHook.addKeyListener(new GlobalKeyAdapter() {
            @Override
            public void keyPressed(GlobalKeyEvent event) {
                if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_ESCAPE) {
                    processingThread.stop();
                    keyboardHook.shutdownHook();
                    App.setTempStatus(4);
                }
            }

            @Override
            public void keyReleased(GlobalKeyEvent event) {
                //System.out.println(event);
            }
        });
    }
}
