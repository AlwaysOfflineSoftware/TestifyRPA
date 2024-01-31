package com.alwaysofflinesoftware.testify.GuiBot;

import com.alwaysofflinesoftware.testify.Util.FileUtils;

import java.util.ArrayList;
import java.util.List;


public class SmartController extends KeyboardController {

    private static String smartDataPath;
    private static int smartFieldCount = 0;
    private static int currentCounter= 0;

    public void setSmartDataPath(String path) {
        smartDataPath = path;
    }

    public void setSmartFieldCount(int smartCount) {
        smartFieldCount = smartCount;
    }

    public String getSmartDataPath(String path) {
        return smartDataPath;
    }

    public void smartTyper(int smartRow, int smartIndex, String fallback) {
        List<String> rawList = FileUtils.parseFileToList(smartDataPath);
        if (rawList != null) {
            List<String> cleanList = cleanRawList(rawList);
            if (cleanList.size() >= smartRow - 1) {
                String[] smartItem = cleanList.get(smartRow - 1).split(",");
                if (smartItem.length >= smartIndex) {
                    typeCharacters(smartItem[smartIndex - 1]);
                } else {
                    typeCharacters(fallback);
                }
            } else {
                typeCharacters(fallback);
            }
        } else {
            typeCharacters(fallback);
        }
    }

    public String concatCommands(String[] raw) {
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < raw.length; i++) {
            if (i > 2) {
                resultList.add(raw[i]);
            }
        }
        if (resultList.isEmpty()) {
            resultList.add("");
        }
        return String.join(" ", resultList);
    }

    private List<String> cleanRawList(List<String> raw) {
        List<String> clean = new ArrayList<>();

        for (String item : raw) {
            if (!item.isBlank()) {
                clean.add(item);
            }
        }
        return clean;
    }
}