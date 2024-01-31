package com.alwaysofflinesoftware.testify.GuiBot;

import com.alwaysofflinesoftware.testify.Runtime.App;
import com.alwaysofflinesoftware.testify.Util.FileUtils;
import com.alwaysofflinesoftware.testify.Util.SimpleLog;
import com.alwaysofflinesoftware.testify.Util.SysUtils;

import java.util.List;

// GuiFactory handles using the tools of testifyUI to perform automated routines
public final class CommandProcessor implements Runnable{
	private final String commandFile;
	private String smartFile;
	private MouseController guim;
	private KeyboardController guik;
	private SmartController guism;
	private static double commandProgress= 0;
	private static double commandCount= 0;


	public CommandProcessor(String jobFile){
		commandFile= jobFile;
	}

	public CommandProcessor(String jobFile, String dataFile){
		commandFile= jobFile;
		smartFile= dataFile;
	}

	@Override
	public void run() {
		perform();
	}

	private void perform() {
		SimpleLog.info("Testify Interpreter Started");
		guim= new MouseController();
		guik= new KeyboardController();
		guism= new SmartController();
		List<String> instructSet = FileUtils.parseFileToList(commandFile);
		String[] command;
		if (instructSet != null) {
			commandCount= instructSet.size();
			for (String s : instructSet) {
				command = s.split(" ");
				try {
					executeCommand(command);
					commandProgress+= 1;
					App.updateProgressBar((commandProgress/commandCount));
				} catch (Exception e) {
					SimpleLog.error(String.valueOf(e));
					SimpleLog.error("An unrecognized value for action word '" + command[0] + "' was used in the routine file");
					System.exit(0);
				}
			}
		} else {
			SimpleLog.warning("Testify Interpreter found instructions but they were empty");
			App.setTempStatus(-1);
		}
		SimpleLog.info("Testify Interpreter finished");
		App.setTempStatus(5);
		commandCount= 0;
		commandProgress= 0;
		App.updateProgressBar(0.0);
	}

	private void executeCommand(String[] commands) {
		try{
			switch(commands[0]){
				case "click":
				case "move":
					guim.clickProcessing(commands);
					break;
				case "smartKeys":  //Temporary till I figure out a better algorithm
					guism.setSmartDataPath(smartFile);
					guism.setSmartFieldCount(FileUtils.countItemInFile(commandFile,"smart"));
					guism.smartTyper(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]), guism.concatCommands(commands));
					break;
				case "keyPress":
					guik.typeCharacters(commands[1], false);
					break;
				case "specialKeyPress":
					guik.typeCharacters(commands[1], true);
					break;
				case "scroll":
					guim.scroll(Integer.parseInt(commands[1]));
					break;
				case "open":
					SysUtils.openApp(commands[1]);
					break;
				case "loop":
					//TODO
					break;
				case "run":
					SimpleLog.info("Running sub-script");
					CommandProcessor subBot;
					if(commands.length==3){
						subBot = new CommandProcessor(commands[1], commands[2]);
					}else{
						subBot = new CommandProcessor(commands[1]);
					}
					subBot.perform();
					SimpleLog.info("Sub-script completed");
					break;
				case "#":
					break;
			}
		} catch (InterruptedException | NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
