package com.alwaysofflinesoftware.testify.GuiBot;

import com.alwaysofflinesoftware.testify.Util.SimpleLog;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class KeyboardFunctions {
    // Traversal of grid based screen elements using arrow keys
    public void arrowTo(int x, int y) throws InterruptedException {
        try {
            Robot arrows = new Robot();
            arrows.setAutoDelay(60);
            int i = 0, j = 0;
            while (i != x) {
                if (i > x) {
                    arrows.keyPress(KeyEvent.VK_LEFT);
                    i++;
                    Thread.sleep(15);
                } else {
                    arrows.keyPress(KeyEvent.VK_RIGHT);
                    i--;
                    Thread.sleep(15);
                }
            }
            while (j != y) {
                if (j > y) {
                    arrows.keyPress(KeyEvent.VK_DOWN);
                    j++;
                    Thread.sleep(15);
                } else {
                    arrows.keyPress(KeyEvent.VK_UP);
                    j--;
                    Thread.sleep(15);
                }
            }
            SimpleLog.info("Keys arrowed " + x + " Horizontally and " + y + " Vertically");
        } catch (AWTException e) {
            SimpleLog.error("Robot could not press key or could not be initialized");
            e.printStackTrace();
        }
    }

    // Selects text through arrow keys, done by using SHIFT + DIRECTION
    public void arrowDrag(int x, int y) throws InterruptedException {
        try {
            Robot arrows = new Robot();
            arrows.setAutoDelay(30);
            int i = 0, j = 0;
            Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_NUM_LOCK, false);
            while (i != x) {
                if (i > x) {
                    arrows.keyPress(KeyEvent.VK_SHIFT);
                    arrows.keyPress(KeyEvent.VK_LEFT);
                    arrows.keyRelease(KeyEvent.VK_LEFT);
                    arrows.keyRelease(KeyEvent.VK_SHIFT);
                    i++;
                    Thread.sleep(15);
                } else {
                    arrows.keyPress(KeyEvent.VK_SHIFT);
                    arrows.keyPress(KeyEvent.VK_RIGHT);
                    arrows.keyRelease(KeyEvent.VK_RIGHT);
                    arrows.keyRelease(KeyEvent.VK_SHIFT);
                    i++;
                    Thread.sleep(15);
                }
            }
            while (j != y) {
                if (j > y) {
                    arrows.keyPress(KeyEvent.VK_SHIFT);
                    arrows.keyPress(KeyEvent.VK_DOWN);
                    arrows.keyRelease(KeyEvent.VK_DOWN);
                    arrows.keyRelease(KeyEvent.VK_SHIFT);
                    j++;
                    Thread.sleep(15);
                } else {
                    arrows.keyPress(KeyEvent.VK_SHIFT);
                    arrows.keyPress(KeyEvent.VK_UP);
                    arrows.keyRelease(KeyEvent.VK_UP);
                    arrows.keyRelease(KeyEvent.VK_SHIFT);
                    j++;
                    Thread.sleep(15);
                }
            }
            Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_NUM_LOCK, true);
            SimpleLog.info("Keys arrowed " + x + " Horizontally and " + y + " Vertically");
        } catch (AWTException e) {
            SimpleLog.error("Robot could not press key or could not be initialized");
            e.printStackTrace();
        }
    }

    //Emulates CTRL + C or the copy/duplicate keyboard shortcut
    //Has no visual effect unless paste() is used
    public void copyKS() {
        try {
            Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_NUM_LOCK, false);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_NUM_LOCK, true);
        } catch (AWTException e) {
            SimpleLog.error("Robot could not press key or could not be initialized");
            //e.printStackTrace();
        }
    }

    //Emulates CTRL + X or the cut/move keyboard shortcut
    //Has no visual effect unless paste() is used
    public void cutKS() {
        try {
            Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_NUM_LOCK, false);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_X);
            robot.keyRelease(KeyEvent.VK_X);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_NUM_LOCK, true);
        } catch (AWTException e) {
            SimpleLog.error("Robot could not press key or could not be initialized");
            //e.printStackTrace();
        }
    }

    //Emulates CTRL + V or the paste keyboard shortcut
    public void pasteKS() {
        try {
            Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_NUM_LOCK, false);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_NUM_LOCK, true);
        } catch (AWTException e) {
            SimpleLog.error("Robot could not press key or could not be initialized");
            //e.printStackTrace();
        }
    }

    //Emulates CTRL + A or the select all keyboard shortcut
    public static void selectAllKS() {
        try {
            Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_NUM_LOCK, false);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_NUM_LOCK, true);
        } catch (AWTException e) {
            SimpleLog.error("Robot could not press key or could not be initialized");
            //e.printStackTrace();
        }
    }
}
