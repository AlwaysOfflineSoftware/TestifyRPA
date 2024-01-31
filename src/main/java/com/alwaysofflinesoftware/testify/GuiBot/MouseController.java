package com.alwaysofflinesoftware.testify.GuiBot;

import com.alwaysofflinesoftware.testify.Util.SimpleLog;
import com.alwaysofflinesoftware.testify.Util.SysUtils;
import com.alwaysofflinesoftware.testify.Util.Winfo;

import java.awt.*;
import java.awt.event.InputEvent;

//GuiBotMouse handles all Mouse Robot events
public class MouseController {
    private static double speed = 5;
    Robot mouse;

    public MouseController() {
        try {
            mouse = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    // Mouse track speed, 100= instant
    public static void setMouseSpeed(int set) {
        if (set > 100) {
            speed = 100;
        } else {
            speed = Math.max(set, 1);
        }
    }

    public void clickProcessing(String[] command) {
        double[] pts;
        try {
            if (command.length > 3) {
                pts = Winfo.getWindowDemensions(Winfo.getWinRect(command[4].replace("_", " ")));
                double[] oldPts = {Double.parseDouble(command[5]), Double.parseDouble(command[6]),
                        Double.parseDouble(command[7]), Double.parseDouble(command[8])};
                switch (command[3]) {
                    case "[left]":
                        MouseFunctions.smartMouseClick("l",Integer.parseInt(command[1]),
                                Integer.parseInt(command[2]), command[4].replace("_", " "), oldPts, pts);
                        break;
                    case "[right]":
                        MouseFunctions.smartMouseClick("r",Integer.parseInt(command[1]),
                                Integer.parseInt(command[2]), command[4].replace("_", " "), oldPts, pts);
                        break;
                    case "[middle]":
                        MouseFunctions.smartMouseClick("m",Integer.parseInt(command[1]),
                                Integer.parseInt(command[2]), command[4].replace("_", " "), oldPts, pts);
                        break;
                    default:
                        accuMouse(Integer.parseInt(command[1]), Integer.parseInt(command[2]));
                        System.out.println(command[3]);
                        break;
                }
            } else {
                accuMouse(Integer.parseInt(command[1]), Integer.parseInt(command[2]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Moves mouse by pixel then can choose to click left
    public void accuMouse_L(double destX, double destY) {
        try {
            double[] screen = SysUtils.getScreenResolution();
            if (destX > screen[0] || destY > screen[1]) {
                SimpleLog.error("One or more destinations exceed screen resolution");
            } else {
                dynaMouse(destX, destY);
                SimpleLog.info("Mouse moved to X:" + destX + " Y:" + destY);
                leftClick(destX, destY);
            }
        } catch (Exception evt) {
            SimpleLog.error(evt.getMessage());
        }
    }

    // Moves mouse by pixel then can choose to click middle
    public void accuMouse_M(double destX, double destY) {
        try {
            double[] screen = SysUtils.getScreenResolution();
            if (destX > screen[0] || destY > screen[1]) {
                SimpleLog.error("One or more destinations exceed screen resolution");
            } else {
                dynaMouse(destX, destY);
                SimpleLog.info("Mouse moved to X:" + destX + " Y:" + destY);
                middleClick(destX, destY);
            }
        } catch (Exception evt) {
            SimpleLog.error(evt.getMessage());
        }
    }

    // Moves mouse by pixel then can choose to click right
    public void accuMouse_R(double destX, double destY) {
        try {
            double[] screen = SysUtils.getScreenResolution();
            if (destX > screen[0] || destY > screen[1]) {
                SimpleLog.error("One or more destinations exceed screen resolution");
            } else {
                dynaMouse(destX, destY);
                SimpleLog.info("Mouse moved to X:" + destX + " Y:" + destY);
                rightClick(destX, destY);
            }
        } catch (Exception evt) {
            SimpleLog.error(evt.getMessage());
        }
    }

    // Moves mouse by pixel no clicking
    public void accuMouse(double destX, double destY) {
        try {
            double[] screen = SysUtils.getScreenResolution();
            if (destX > screen[0] || destY > screen[1]) {
                System.out.println("ERROR: One or more destinations exceed screen resolution");
            } else {
                dynaMouse(destX, destY);
                SimpleLog.info("Mouse moved to X:" + destX + " Y:" + destY);
            }
        } catch (Exception evt) {
            System.err.println(evt.getMessage());
        }
    }

    // Scroll Wheel Basic Control
    public void scroll(int amount) {
        int i = 0;

        if (amount < 0) {
            while (i > amount) {
                mouse.mouseWheel(1);
                i--;
            }
        } else if (amount > 0) {
            while (i < amount) {
                mouse.mouseWheel(-1);
                i++;
            }
        }
        SimpleLog.info("Mouse Scrolled " + amount + " units");
    }

    // Mouse drag area selection
    public void mouseDrag(double destX, double destY) throws InterruptedException {
        int i, j;
        double[] screen = SysUtils.getScreenResolution();

        if (destX > 100 || destY > 100)
            SimpleLog.error("Value over 100!");
        else {
            destX = Math.floor((destX / 100) * screen[0]);
            destY = Math.floor((destY / 100) * screen[1]);
            Point p = MouseInfo.getPointerInfo().getLocation();
            i = p.x;
            j = p.y;

            mouse.mousePress(InputEvent.getMaskForButton(1));
            while (i != destX) {
                mouse.mouseMove(i, j);
                if (i < destX)
                    i++;
                else
                    i--;
                Thread.sleep(1);
            }
            while (j != destY) {
                mouse.mouseMove(i, j);
                if (j < destY)
                    j++;
                else
                    j--;
                Thread.sleep(1);
            }
            SimpleLog.info("Mouse dragged to X:" + destX + " Y:" + destY);
            mouse.mouseRelease(InputEvent.getMaskForButton(1));
        }
    }

    // Performs percentage movement increments for the mouse
    // (increased performance, less calls to mouse but still smooth looking)
    private void dynaMouse(double endX, double endY) {
        Point p = MouseInfo.getPointerInfo().getLocation();
        double x = p.x;
        double y = p.y;
        double distX = Math.abs(x - endX);
        double distY = Math.abs(y - endY);
        double inc = (speed / 1000);

        while (x != endX) {
            if (x < endX) {
                mouse.mouseMove((int) (x + (distX * inc) + 1), (int) y);
                p = MouseInfo.getPointerInfo().getLocation();
                x = p.x;
                if (x > endX) {
                    y = p.y;
                    mouse.mouseMove((int) endX, (int) y);
                    break;
                }
            }
            if (x > endX) {
                mouse.mouseMove((int) (x - ((distX * inc) + 1)), (int) y);
                p = MouseInfo.getPointerInfo().getLocation();
                x = p.x;
                if (x < endX) {
                    y = p.y;
                    mouse.mouseMove((int) endX, (int) y);
                    break;
                }
            }
        }
        while (y != endY) {
            if (y < endY) {
                mouse.mouseMove((int) x, (int) (y + (distY * inc) + 1));
                p = MouseInfo.getPointerInfo().getLocation();
                y = p.y;
                if (y > endY) {
                    x = p.x;
                    mouse.mouseMove((int) x, (int) endY);
                    break;
                }
            }
            if (y > endY) {
                mouse.mouseMove((int) x, (int) (y - ((distY * inc) + 1)));
                p = MouseInfo.getPointerInfo().getLocation();
                y = p.y;
                if (y < endY) {
                    x = p.x;
                    mouse.mouseMove((int) x, (int) endY);
                    break;
                }
            }
        }
    }

    private void leftClick(double destX, double destY) {
        mouse.mousePress(InputEvent.getMaskForButton(1));
        mouse.mouseRelease(InputEvent.getMaskForButton(1));
        SimpleLog.info("Left Clicked at X:" + destX + " Y:" + destY);
    }

    private void middleClick(double destX, double destY) {
        mouse.mousePress(InputEvent.getMaskForButton(2));
        mouse.mouseRelease(InputEvent.getMaskForButton(2));
        SimpleLog.info("Middle Clicked at X:" + destX + " Y:" + destY);
    }

    private void rightClick(double destX, double destY) {
        mouse.mousePress(InputEvent.getMaskForButton(3));
        mouse.mouseRelease(InputEvent.getMaskForButton(3));
        SimpleLog.info("Right Clicked at X:" + destX + " Y:" + destY);
    }
}
