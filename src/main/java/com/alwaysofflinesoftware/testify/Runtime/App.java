package com.alwaysofflinesoftware.testify.Runtime;

import com.alwaysofflinesoftware.testify.GuiBot.CommandProcessor;
import com.alwaysofflinesoftware.testify.GuiBot.Recorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static java.awt.GridBagConstraints.HORIZONTAL;
import static java.awt.GridBagConstraints.WEST;
import java.util.Timer;
import java.util.TimerTask;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class App {
    private static JFrame frame;
    private static JTextField txt_RoutineFile;
    private static JTextField txt_SmartFile;
    private static JTextField txf_ConsoleOut;
    private static JTextField txt_Status;
    private static JProgressBar progressBar;
    
    
    /////////////////////////////
    // Launch the application. //
    /////////////////////////////
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                App window = new App();
                window.frame.setVisible(true);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
        });
    }

    // Create the application.
    public App() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel"); //So good to know!!
        }catch (UnsupportedLookAndFeelException e) {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }

        initialize();
    }
    
    ///////////////////////
    // GUI Functionality //
    ///////////////////////
    
    public static void runButtonAction(){
        if(!txt_RoutineFile.getText().equals("")){
            String routineFile=txt_RoutineFile.getText();
            CommandProcessor commander= new CommandProcessor(routineFile);
            commander.run();
        }
    }

    public static void recordButtonAction(){
        Recorder recorder= new Recorder("routine.txt",false,false);
        recorder.run();
    }

    public static void setStatus(String newStatus, Color backgroundColor){
        txt_Status.setText(newStatus);
        txt_Status.setBackground(backgroundColor);
    }

    public static void setStatus(int setStatus){
        statusCodeInterpreter(setStatus);
    }

    public static void setTempStatus(String newStatus, Color backgroundColor){
        long delay = 1000L;
        Timer timer = new Timer(true);
        TimerTask resetTimerTask= new TimerTask() {
            public void run() {
                statusCodeInterpreter(0);
            } 
        };
        
        txt_Status.setText(newStatus);
        txt_Status.setBackground(backgroundColor);
        timer.schedule(resetTimerTask, delay);
    }

    public static void setTempStatus(int setStatus){
        long delay = 1000L;
        Timer timer = new Timer(true);
        TimerTask resetTimerTask= new TimerTask() {
            public void run() {
                statusCodeInterpreter(0);
            } 
        };
        
        statusCodeInterpreter(setStatus);
        timer.schedule(resetTimerTask, delay);
    }
    
    private static void statusCodeInterpreter(int setStatus) {
        switch (setStatus){
            case 0:
                txt_Status.setText("IDLE");
                txt_Status.setBackground(Color.GRAY);
                break;
            case 1:
                txt_Status.setText("RUNNING");
                txt_Status.setBackground(Color.YELLOW);
                break;
            case 2:
                txt_Status.setText("RECORDING");
                txt_Status.setBackground(Color.PINK);
                break;
            case 3:
                txt_Status.setText("WAITING");
                txt_Status.setBackground(Color.YELLOW);
                break;
            case 4:
                txt_Status.setText("STOPPED");
                txt_Status.setBackground(Color.RED);
                break;
            case 5:
                txt_Status.setText("COMPLETE");
                txt_Status.setBackground(Color.GREEN);
                break;
            case -1:
                txt_Status.setText("ERROR");
                txt_Status.setBackground(Color.RED);
                break;
            default:
                break;
        }
    }

    public static void updateProgressBar(double progress) {
        progressBar.setValue((int) progress);
    }

    public static void writeToGUIconsole(String entry) {
        txf_ConsoleOut.setText(txf_ConsoleOut.getText()+"\n"+entry);
    }
    
    
    ////////////////////
    // GUI Formation //
    ///////////////////

    // Initialize the contents of the frame.
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 325);
        frame.setResizable(false);
        frame.setTitle("Testify RPA");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]      {50,100,75,22,0,0,0,0,0,0,0,0,0,0,20};
        gridBagLayout.columnWeights = new double[]  {0.0,1.0,0.0,1.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,Double.MIN_VALUE};
        gridBagLayout.rowHeights = new int[]        {0,0,0,0,0,0,0,0,0};
        gridBagLayout.rowWeights = new double[]     {0.0,0.0,0.0,0.0,0.0,0.0,0.0,Double.MIN_VALUE};
        frame.getContentPane().setLayout(gridBagLayout);

        JLabel lbl_RoutineFile = new JLabel("Routine File:");
        GridBagConstraints gbc_lblRoutineFile = new GridBagConstraints();
        gbc_lblRoutineFile.anchor = WEST;
        gbc_lblRoutineFile.insets = new Insets(0, 0, 5, 5);
        gbc_lblRoutineFile.gridx = 1;
        gbc_lblRoutineFile.gridy = 1;
        frame.getContentPane().add(lbl_RoutineFile, gbc_lblRoutineFile);

        txt_RoutineFile = new JTextField(); // Routine File Field
        GridBagConstraints gbc_txtRoutineFile = new GridBagConstraints();
        gbc_txtRoutineFile.gridwidth = 10;
        gbc_txtRoutineFile.insets = new Insets(0, 0, 5, 5);
        gbc_txtRoutineFile.fill = HORIZONTAL;
        gbc_txtRoutineFile.gridx = 2;
        gbc_txtRoutineFile.gridy = 1;
        frame.getContentPane().add(txt_RoutineFile, gbc_txtRoutineFile);
        txt_RoutineFile.setColumns(10);
        
        JButton btn_Run = new JButton("Run");
        GridBagConstraints gbc_btnRun = new GridBagConstraints();
        gbc_btnRun.gridwidth = 2;
        gbc_btnRun.fill = HORIZONTAL;
        gbc_btnRun.insets = new Insets(0, 0, 5, 5);
        gbc_btnRun.gridx = 12;
        gbc_btnRun.gridy = 1;
        frame.getContentPane().add(btn_Run, gbc_btnRun);

        JLabel lbl_SmartFile = new JLabel("Smart File:");
        GridBagConstraints gbc_lblSmartFile = new GridBagConstraints();
        gbc_lblSmartFile.anchor = WEST;
        gbc_lblSmartFile.insets = new Insets(0, 0, 5, 5);
        gbc_lblSmartFile.gridx = 1;
        gbc_lblSmartFile.gridy = 2;
        frame.getContentPane().add(lbl_SmartFile, gbc_lblSmartFile);

        txt_SmartFile = new JTextField(); // Smart File Field
        GridBagConstraints gbc_txtSmartFile = new GridBagConstraints();
        gbc_txtSmartFile.gridwidth = 10;
        gbc_txtSmartFile.insets = new Insets(0, 0, 5, 5);
        gbc_txtSmartFile.fill = HORIZONTAL;
        gbc_txtSmartFile.gridx = 2;
        gbc_txtSmartFile.gridy = 2;
        frame.getContentPane().add(txt_SmartFile, gbc_txtSmartFile);
        txt_SmartFile.setColumns(10);

        JButton btn_Record = new JButton("Record");
        GridBagConstraints gbc_btnRecord = new GridBagConstraints();
        gbc_btnRecord.gridwidth = 2;
        gbc_btnRecord.fill = HORIZONTAL;
        gbc_btnRecord.insets = new Insets(0, 0, 5, 5);
        gbc_btnRecord.gridx = 12;
        gbc_btnRecord.gridy = 2;
        frame.getContentPane().add(btn_Record, gbc_btnRecord);

        JLabel lblTerminalOutput = new JLabel("Terminal Output:");
        GridBagConstraints gbc_lblTerminalOutput = new GridBagConstraints();
        gbc_lblTerminalOutput.anchor = WEST;
        gbc_lblTerminalOutput.gridwidth = 4;
        gbc_lblTerminalOutput.insets = new Insets(0, 0, 5, 5);
        gbc_lblTerminalOutput.gridx = 1;
        gbc_lblTerminalOutput.gridy = 3;
        frame.getContentPane().add(lblTerminalOutput, gbc_lblTerminalOutput);

        txf_ConsoleOut = new JTextField();
        GridBagConstraints gbc_txfConsoleOut = new GridBagConstraints();
        gbc_txfConsoleOut.gridwidth = 11;
        gbc_txfConsoleOut.gridheight = 4;
        gbc_txfConsoleOut.insets = new Insets(0, 0, 5, 5);
        gbc_txfConsoleOut.fill = GridBagConstraints.BOTH;
        gbc_txfConsoleOut.gridx = 1;
        gbc_txfConsoleOut.gridy = 4;
        frame.getContentPane().add(txf_ConsoleOut, gbc_txfConsoleOut);
        txf_ConsoleOut.setEditable(false);
        txf_ConsoleOut.setColumns(10);

        JCheckBox chk_UseSmartInput = new JCheckBox("Use Smart Input");
        GridBagConstraints gbc_chkUseSmartInput = new GridBagConstraints();
        gbc_chkUseSmartInput.insets = new Insets(0, 0, 5, 5);
        gbc_chkUseSmartInput.gridx = 12;
        gbc_chkUseSmartInput.gridy = 4;
        frame.getContentPane().add(chk_UseSmartInput, gbc_chkUseSmartInput);

        JCheckBox chk_TrackMouse = new JCheckBox("Track Mouse");
        GridBagConstraints gbc_chkTrackMouse= new GridBagConstraints();
        gbc_chkTrackMouse.anchor = WEST;
        gbc_chkTrackMouse.insets = new Insets(0, 0, 5, 5);
        gbc_chkTrackMouse.gridx = 12;
        gbc_chkTrackMouse.gridy = 5;
        frame.getContentPane().add(chk_TrackMouse, gbc_chkTrackMouse);

        txt_Status = new JTextField();
        txt_Status.setText("IDLE");
        GridBagConstraints gbc_txtIdle = new GridBagConstraints();
        gbc_txtIdle.fill = HORIZONTAL;
        gbc_txtIdle.insets = new Insets(0, 0, 5, 5);
        gbc_txtIdle.gridx = 1;
        gbc_txtIdle.gridy = 8;
        frame.getContentPane().add(txt_Status, gbc_txtIdle);
        txt_Status.setEditable(false);
        txt_Status.setColumns(10);

        progressBar = new JProgressBar();
        GridBagConstraints gbc_progressBar = new GridBagConstraints();
        gbc_progressBar.fill = HORIZONTAL;
        gbc_progressBar.gridwidth = 10;
        gbc_progressBar.insets = new Insets(0, 0, 5, 5);
        gbc_progressBar.gridx = 2;
        gbc_progressBar.gridy = 8;
        frame.getContentPane().add(progressBar, gbc_progressBar);

        JButton btn_Cancel = new JButton("Cancel");
        GridBagConstraints gbc_btnCancel = new GridBagConstraints();
        gbc_btnCancel.gridwidth = 2;
        gbc_btnCancel.fill = HORIZONTAL;
        gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
        gbc_btnCancel.gridx = 12;
        gbc_btnCancel.gridy = 8;
        frame.getContentPane().add(btn_Cancel, gbc_btnCancel);
        
        

        btn_Run.addActionListener(arg0 -> {
            runButtonAction();
        });

        btn_Record.addActionListener(arg0 -> {
            recordButtonAction();
        });
        
        btn_Cancel.addActionListener((ActionEvent arg0) -> {
            System.exit(0);
        });
    }
}