package com.alwaysofflinesoftware.testify.Util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    // Reads in a CSV file and puts it into an array
    public static List<String> parseFileToList(String filePath){
        List<String> fileContent= new ArrayList<>();
        try {
            String line;
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            while ((line = reader.readLine()) != null){
                fileContent.add(line);
            }
            return fileContent;
        } catch (FileNotFoundException e) {
            SimpleLog.error("Testify could not find the file at " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int countItemInFile(String fileName, String term){
        List<String> parsedFile= parseFileToList(fileName);
        int result= 0;
        int temp= 0;
        if(parsedFile!= null){
            for(String listItem: parsedFile){
                temp= listItem.split(term).length-1;
                result= result+temp;
            }
        }
        return result;
    }

    // Used to write routine files
    public static void createFile(String fileName) {
        try {
            File file = new File(fileName);
            FileWriter fWriter = new FileWriter(file, false);
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            bWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Used to write routine files
    public static void createFile(String fileName, String text) {
        try {
            File file = new File(fileName);
            FileWriter fWriter = new FileWriter(file, false);
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            bWriter.close();
            writeToFile(fileName, text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(String fileName, String text){
        File file= new File(fileName);
        if(file.exists()){
            try {
                FileWriter fWriter = new FileWriter(fileName, true);
                BufferedWriter bWriter = new BufferedWriter(fWriter);
                bWriter.write(text);
                bWriter.newLine();
                bWriter.close();
            }catch(IOException ignored){
            }
        }else {
            createFile(fileName);
            writeToFile(fileName,text);
        }
    }

    public static void writeToFile(String fileName, List<String> text){
        File file= new File(fileName);
        if(file.exists()){
            try {
                FileWriter fWriter = new FileWriter(fileName, true);
                BufferedWriter bWriter = new BufferedWriter(fWriter);
                for(String s: text){
                    bWriter.write(s);
                    bWriter.newLine();
                }
                bWriter.close();
            }catch(IOException ignored){
            }
        }else {
            createFile(fileName);
            writeToFile(fileName,text);
        }
    }

    // Basic file functions

    public static boolean DoesFileExist(String filePath){
        File checkFile= new File(filePath);
        return checkFile.exists();
    }

    public static List<String> read(String filePath) {
        List<String> fileContent = new ArrayList<>();
        try {
            String line;
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            while ((line = reader.readLine()) != null) {
                fileContent.add(line);
            }
            return fileContent;
        } catch (IOException e) {
            Debug.printi(e);
        }
        fileContent.add("Unable to read file:" + filePath);
        return fileContent;
    }

    public static void write(String filePath, String data) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(data);

            writer.close();
        } catch (IOException e) {
            Debug.printi(e);
        }
    }

    public static void append(String filePath, String data) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.append(data);

            writer.close();
        } catch (IOException e) {
            Debug.printi(e);
        }
    }
	
	public static void tempWrite(String filePath, String data) {
        try {
            String fileExt = ".tmp";
            File tmpFile = File.createTempFile(filePath, fileExt);
            write(filePath + fileExt, data);
            tmpFile.deleteOnExit();
        } catch (Exception e) {
            Debug.printi(e);
        }
    }

    public static void tempAppend(String filePath, String data) {
        try {
            String fileExt = ".tmp";
            File tmpFile = File.createTempFile(filePath, fileExt);
            append(filePath + fileExt, data);
            tmpFile.deleteOnExit();
        } catch (Exception e) {
            Debug.printi(e);
        }
    }
}
