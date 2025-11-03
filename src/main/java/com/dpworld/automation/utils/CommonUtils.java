package com.dpworld.automation.utils;


import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.util.Strings;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class CommonUtils {

    public static boolean ifStringNotNullOrEmpty(String s) {
        return !Strings.isNullOrEmpty(s);
    }


    public static boolean verifyDateFormat(String dateFormatPattern, String date) {
        boolean result = true;
        DateFormat dateFormatter = new SimpleDateFormat(dateFormatPattern);
        try {
            dateFormatter.parse(date.replaceAll(",", ""));
        } catch (Exception e) {
            result = false;
        }
        LogUtils.info("Expected Date Format: " + dateFormatPattern);
        LogUtils.info("Actual Date: " + date);
        return result;
    }


    public static String getRandomAlphanumeric(int size) {
        return RandomStringUtils.randomAlphanumeric(size);
    }

    public static String getRandomNumeric(int size) {
        return RandomStringUtils.randomNumeric(size);
    }

    @SneakyThrows
    public static LinkedHashMap<String, LinkedHashMap<String, String>> csvRead(String path) {
        CsvMapper mapper = new CsvMapper();
        mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
        File csvFile = new File(path);
        MappingIterator<String[]> it = mapper.readerFor(String[].class).readValues(csvFile);
        LinkedHashMap<String, LinkedHashMap<String, String>> tableMap = new LinkedHashMap<>();
        String[] header = it.next();
        while (it.hasNext()) {
            String[] row = it.next();
            LinkedHashMap<String, String> innerMap = new LinkedHashMap<>();
            for (int i = 1; i < row.length; i++) {
                innerMap.put(header[i].toUpperCase(), row[i]);
            }

            tableMap.put(row[0], innerMap);
        }
        LogUtils.info("Capture csv data for FRS - Monthly Incident by Region/Division from download:- " + tableMap);
        return tableMap;
    }

    @SneakyThrows
    public static LinkedList<LinkedHashMap<String, String>> csvReadList(String path) {
        CsvMapper mapper = new CsvMapper();
        mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
        File csvFile = new File(path);
        MappingIterator<String[]> it = mapper.readerFor(String[].class).readValues(csvFile);
        LinkedList<LinkedHashMap<String, String>> lst = new LinkedList<>();
        String[] header = it.next();
        while (it.hasNext()) {
            String[] row = it.next();
            for (int i = 0; i < row.length; i++) {
                LinkedHashMap<String, String> innerMap = new LinkedHashMap<>();
                innerMap.put(header[i].toUpperCase(), row[i]);
                lst.add(innerMap);
            }
        }
        LogUtils.info("Capture csv data for FRS - Monthly Incident by Region/Division from download:- " + lst);
        return lst;
    }

    public static String ValidateFileContainsDownloadFiles(String path,String containsFileName){
        File file=new File(path);
        String fileName="";
        if(file.isDirectory()){
            File[] files = file.listFiles();
            try {
                for (File f:files){
                    fileName = f.getAbsolutePath();
                    if(fileName.contains(containsFileName))
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }return fileName;
    }

    public static void deleteAlreadyExistingFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

}
