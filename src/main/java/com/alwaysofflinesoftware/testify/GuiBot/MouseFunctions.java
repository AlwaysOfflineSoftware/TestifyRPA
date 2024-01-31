package com.alwaysofflinesoftware.testify.GuiBot;

import com.sun.jna.platform.win32.WinDef;
import com.alwaysofflinesoftware.testify.Util.SysUtils;
import com.alwaysofflinesoftware.testify.Util.Winfo;

public class MouseFunctions {
    // Uses mouse data and dimension data arrays to adjust recorded mouse movements to new window locations
    public static void smartMouseClick(String mouseMode, double mouseX, double mouseY, String window, double[] oldWinDem, double[] newWinDem) {
        MouseController guim = new MouseController();
        double xdif, ydif, trueMouseX, trueMouseY; //, wdif, hdif;

        xdif = newWinDem[0] - oldWinDem[0]; //top left window point x
        ydif = newWinDem[1] - oldWinDem[1]; //top left window point y
        //wdif= (oldWinDem[2]- oldWinDem[0]) + (newWinDem[0]- newWinDem[2]); //bottom right window point x
        //hdif= (oldWinDem[3]- oldWinDem[1]) + (newWinDem[1]- newWinDem[3]); //bottom right window point y

        // current mouse + top left point difference + bottom right point difference
        trueMouseX = mouseX + xdif;
        trueMouseY = mouseY + ydif;
        //trueMouseX+= hdif;
        //trueMouseY+= wdif;

        WinDef.HWND hwndWindow = Winfo.getWindowHWND(window);
        SysUtils.keepFocus(hwndWindow);
        //windowWaitCheck(window);
        switch (mouseMode) {
            case "l":
                guim.accuMouse_L(trueMouseX, trueMouseY);
                break;
            case "r":
                guim.accuMouse_R(trueMouseX, trueMouseY);
                break;
            case "m":
                guim.accuMouse_M(trueMouseX, trueMouseY);
                break;
        }
    }
}