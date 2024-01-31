package com.alwaysofflinesoftware.testify.GuiBot;

import com.alwaysofflinesoftware.testify.Util.SimpleLog;

import java.awt.*;
import java.awt.event.KeyEvent;

//GuiBotKeyboard handles all Keyboard Robot events
public class KeyboardController {

    private static Robot robot;

    public KeyboardController() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    // Facilitates keyboard emulation from string commands, Uses KeyboardHandler()
    public void typeCharacters(String input) {
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            keyboardHandler(c);
        }
        SimpleLog.info("Keyboard Typed: " + input);
    }

    // OVERLOAD  keyboard emulation from string commands for special characters (ex: ESC, F5, HOME)
    public void typeCharacters(String input, boolean special) {
        if (special) {
            spKeyboardHandler(input);
            SimpleLog.info("Keyboard Typed: " + input);
        } else {
            typeCharacters(input);
        }
    }

    private static final int defaultPause= 25;
    // Attaches individual character to a emulated keyboard event
    private static void keyboardHandler(char key) {
        robot.setAutoDelay(50);
        switch (key) {
            case 'e':
                keyLogic(KeyEvent.VK_E);
                break;
            case 't':
                keyLogic(KeyEvent.VK_T);
                break;
            case ' ':
                keyLogic(KeyEvent.VK_SPACE);
                break;
            case 'a':
                keyLogic(KeyEvent.VK_A);
                break;
            case 'o':
                keyLogic(KeyEvent.VK_O);
                break;
            case 'i':
                keyLogic(KeyEvent.VK_I);
                break;
            case 'n':
                keyLogic(KeyEvent.VK_N);
                break;
            case 's':
                keyLogic(KeyEvent.VK_S);
                break;
            case 'r':
                keyLogic(KeyEvent.VK_R);
                break;
            case 'h':
                keyLogic(KeyEvent.VK_H);
                break;
            case 'l':
                keyLogic(KeyEvent.VK_L);
                break;
            case 'd':
                keyLogic(KeyEvent.VK_D);
                break;
            case 'c':
                keyLogic(KeyEvent.VK_C);
                break;
            case 'u':
                keyLogic(KeyEvent.VK_U);
                break;
            case 'm':
                keyLogic(KeyEvent.VK_M);
                break;
            case 'f':
                keyLogic(KeyEvent.VK_F);
                break;
            case 'p':
                keyLogic(KeyEvent.VK_P);
                break;
            case 'g':
                keyLogic(KeyEvent.VK_G);
                break;
            case 'w':
                keyLogic(KeyEvent.VK_W);
                break;
            case 'y':
                keyLogic(KeyEvent.VK_Y);
                break;
            case 'b':
                keyLogic(KeyEvent.VK_B);
                break;
            case 'j':
                keyLogic(KeyEvent.VK_J);
                break;
            case 'k':
                keyLogic(KeyEvent.VK_K);
                break;
            case 'v':
                keyLogic(KeyEvent.VK_V);
                break;
            case 'q':
                keyLogic(KeyEvent.VK_Q);
                break;
            case 'A':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_A);
                break;
            case 'B':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_B);
                break;
            case 'C':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_C);
                break;
            case 'D':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_D);
                break;
            case 'E':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_E);
                break;
            case 'F':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_F);
                break;
            case 'G':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_G);
                break;
            case 'H':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_H);
                break;
            case 'I':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_I);
                break;
            case 'J':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_J);
                break;
            case 'K':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_K);
                break;
            case 'L':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_L);
                break;
            case 'M':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_M);
                break;
            case 'N':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_N);
                break;
            case 'O':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_O);
                break;
            case 'P':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_P);
                break;
            case 'Q':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_Q);
                break;
            case 'R':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_R);
                break;
            case 'S':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_S);
                break;
            case 'T':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_T);
                break;
            case 'U':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_U);
                break;
            case 'V':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_V);
                break;
            case 'W':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_W);
                break;
            case 'x':
                keyLogic(KeyEvent.VK_X);
                break;
            case 'X':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_X);
                break;
            case 'Y':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_Y);
                break;
            case 'z':
                keyLogic(KeyEvent.VK_Z);
                break;
            case 'Z':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_Z);
                break;
            case '`':
                keyLogic(KeyEvent.VK_BACK_QUOTE);
                break;
            case '0':
                keyLogic(KeyEvent.VK_0);
                break;
            case '1':
                keyLogic(KeyEvent.VK_1);
                break;
            case '2':
                keyLogic(KeyEvent.VK_2);
                break;
            case '3':
                keyLogic(KeyEvent.VK_3);
                break;
            case '4':
                keyLogic(KeyEvent.VK_4);
                break;
            case '5':
                keyLogic(KeyEvent.VK_5);
                break;
            case '6':
                keyLogic(KeyEvent.VK_6);
                break;
            case '7':
                keyLogic(KeyEvent.VK_7);
                break;
            case '8':
                keyLogic(KeyEvent.VK_8);
                break;
            case '9':
                keyLogic(KeyEvent.VK_9);
                break;
            case '-':
                keyLogic(KeyEvent.VK_MINUS);
                break;
            case '=':
                keyLogic(KeyEvent.VK_EQUALS);
                break;
            case '~':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_BACK_QUOTE);
                break;
            case '!':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_1);
                break;
            case '@':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_2);
                break;
            case '#':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_3);
                break;
            case '$':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_4);
                break;
            case '%':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_5);
                break;
            case '^':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_6);
                break;
            case '&':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_7);
                break;
            case '*':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_8);
                break;
            case '(':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_9);
                break;
            case ')':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_0);
                break;
            case '_':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_MINUS);
                break;
            case '+':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_EQUALS);
                break;
            case '\t':
                keyLogic(KeyEvent.VK_TAB);
                break;
            case '\n':
                keyLogic(KeyEvent.VK_ENTER);
                break;
            case '\r':
                // Do nothing, covered by \n
                break;
            case '[':
                keyLogic(KeyEvent.VK_OPEN_BRACKET);
                break;
            case ']':
                keyLogic(KeyEvent.VK_CLOSE_BRACKET);
                break;
            case '\\':
                keyLogic(KeyEvent.VK_BACK_SLASH);
                break;
            case '{':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_OPEN_BRACKET);
                break;
            case '}':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_CLOSE_BRACKET);
                break;
            case '|':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_BACK_SLASH);
                break;
            case ';':
                keyLogic(KeyEvent.VK_SEMICOLON);
                break;
            case ':':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_SEMICOLON);
                break;
            case '\'':
                keyLogic(KeyEvent.VK_QUOTE);
                break;
            case '"':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_QUOTE);
                break;
            case ',':
                keyLogic(KeyEvent.VK_COMMA);
                break;
            case '<':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_COMMA);
                break;
            case '.':
                keyLogic(KeyEvent.VK_PERIOD);
                break;
            case '>':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_PERIOD);
                break;
            case '/':
                keyLogic(KeyEvent.VK_SLASH);
                break;
            case '?':
                robot.keyPress(KeyEvent.VK_SHIFT);
                keyLogic(KeyEvent.VK_SLASH);
                break;
            default:
                throw new IllegalArgumentException("Cannot type character " + key);
        }
        robot.keyRelease(KeyEvent.VK_SHIFT);
    }

    // NOT COMPLETE
    // Attaches specific strings to "characters" that don't have a visual representation
    // like HOME, F5 or ESCAPE
    private static void spKeyboardHandler(String specialChar) {
        Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_NUM_LOCK, false);
        switch (specialChar.toLowerCase()) {
            case "space":
                keyLogic(KeyEvent.VK_SPACE);
                break;
            case "up":
                keyLogic(KeyEvent.VK_UP);
                break;
            case "down":
                keyLogic(KeyEvent.VK_DOWN);
                break;
            case "left":
                keyLogic(KeyEvent.VK_LEFT);
                break;
            case "right":
                keyLogic(KeyEvent.VK_RIGHT);
                break;
            case "comma":
                keyLogic(KeyEvent.VK_COMMA);
                break;
            case "enter":
                keyLogic(KeyEvent.VK_ENTER, 1350);
                break;
            case "tab":
                keyLogic(KeyEvent.VK_TAB, 1200);
                break;
            case "delete":
                keyLogic(KeyEvent.VK_DELETE);
                break;
            case "backspace":
                keyLogic(KeyEvent.VK_BACK_SPACE);
                break;
            case "windows":
                keyLogic(KeyEvent.VK_WINDOWS);
                break;
            case "f1":
                keyLogic(KeyEvent.VK_F1);
                break;
            case "f2":
                keyLogic(KeyEvent.VK_F2);
                break;
            case "f3":
                keyLogic(KeyEvent.VK_F3);
                break;
            case "f4":
                keyLogic(KeyEvent.VK_F4);
                break;
            case "f5":
                keyLogic(KeyEvent.VK_F5);
                break;
            case "f6":
                keyLogic(KeyEvent.VK_F6);
                break;
            case "f7":
                keyLogic(KeyEvent.VK_F7);
                break;
            case "f8":
                keyLogic(KeyEvent.VK_F8);
                break;
            case "f9":
                keyLogic(KeyEvent.VK_F9);
                break;
            case "f10":
                keyLogic(KeyEvent.VK_F10);
                break;
            case "f11":
                keyLogic(KeyEvent.VK_F11);
                break;
            case "f12":
                keyLogic(KeyEvent.VK_F12);
                break;
            case "end":
                keyLogic(KeyEvent.VK_END);
                break;
            case "pause":
                keyLogic(KeyEvent.VK_PAUSE);
                break;
            case "home":
                keyLogic(KeyEvent.VK_HOME);
                break;
            default:
                Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_NUM_LOCK, false);
                if (specialChar.length() > 1) {
                    SimpleLog.error("ERROR: " + specialChar + " is not a recognized character or special character.");
                } else {
                    keyboardHandler(specialChar.charAt(0));
                }
        }
        Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_NUM_LOCK, false);
    }

    private static void keyLogic(int key) {
        try{
            robot.keyPress(key);
            Thread.sleep(defaultPause);
            robot.keyRelease(key);
        }catch(InterruptedException e){
            SimpleLog.error(e.toString());
        }
    }

    private static void keyLogic(int key, int customPause) {
        try{
            robot.keyPress(key);
            Thread.sleep(customPause);
            robot.keyRelease(key);
        }catch(InterruptedException e){
            SimpleLog.error(e.toString());
        }
    }
}// CLASS END
