package com.example;

import java.util.Iterator;
import java.util.Map;

public class StateCensusAnalyzer {
    private CSVStateCensus csvStateCensus;
    private CSVStates csvStates;

    public StateCensusAnalyzer(String filePath) {
        this.csvStateCensus = new CSVStateCensus(filePath);
        this.csvStates = new CSVStates(filePath);
    }

    public int countRecordsInStateCensusData() {
        try {
            Iterator<String[]> iterator = csvStateCensus.loadDataFromFile();
            int count = 0;
            while (iterator.hasNext()) {
                count++;
                iterator.next();
            }
            return count;
        } catch (Exception exception) {
            System.err.println("Error while counting state census records: " + exception.getMessage());
            return -1;
        }
    }

    public int countDistinctStateCodes() {
        try {
            Iterator<Map.Entry<Integer, String>> iterator = csvStates.loadDataFromFile();
            int count = 0;
            while (iterator.hasNext()) {
                count++;
                iterator.next();
            }
            return count;
        } catch (Exception exception) {
            System.err.println("Error while counting distinct state codes: " + exception.getMessage());
            return -1;
        }
    }

    public String getStateCodeByTIN(int TIN) {
        try {
            Iterator<Map.Entry<Integer, String>> iterator = csvStates.loadDataFromFile();
            String stateCode = null;
            while (iterator.hasNext()) {
                Map.Entry<Integer, String> entry = iterator.next();
                if (entry.getKey() == TIN) {
                    stateCode = entry.getValue();
                }
            }
            return stateCode == null ? "STATECODE_NOT_FOUND" : stateCode;
        } catch (Exception exception) {
            System.err.println("Error while retrieving state code by TIN: " + exception.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the State Census Analyzer.");

        String stateCensusFilePath = "src/main/resources/StateCensus.csv";

        StateCensusAnalyzer stateCensusAnalyzer = new StateCensusAnalyzer(stateCensusFilePath);
        System.out.println("Number of records in State Census CSV: " + stateCensusAnalyzer.countRecordsInStateCensusData());
        System.out.println("Number of distinct state codes: " + stateCensusAnalyzer.countDistinctStateCodes());
        System.out.println("State Code for TIN 10 is: " + stateCensusAnalyzer.getStateCodeByTIN(10));
    }
}
