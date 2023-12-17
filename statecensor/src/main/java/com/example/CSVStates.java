package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.exceptions.*;

public class CSVStates {
    private String filepath;

    public CSVStates(String filepath) {
        this.filepath = filepath;
    }

    public Iterator<Map.Entry<Integer, String>> loadDataFromFile()
            throws FileFormatException, IncorrectHeaderException, FileReaderException, DelimiterException {
        validateFileFormat();

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            validateHeader(reader.readLine());

            HashMap<Integer, String> records = readRecords(reader);

            return records.entrySet().iterator();
        } catch (IOException exception) {
            throw new FileReaderException("Error reading the file: " + exception.getMessage());
        }
    }

    private void validateFileFormat() throws FileFormatException {
        if (!filepath.endsWith(".csv")) {
            throw new FileFormatException("Invalid file format. Please provide a CSV file.");
        }
    }

    private void validateHeader(String header) throws IncorrectHeaderException {
        if (header == null || !header.equals("SrNo,StateName,TIN,StateCode")) {
            throw new IncorrectHeaderException("Invalid CSV header. Expected: SrNo,StateName,TIN,StateCode");
        }
    }

    private HashMap<Integer, String> readRecords(BufferedReader reader) throws DelimiterException, IOException {
        HashMap<Integer, String> records = new HashMap<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] lineData = line.split(",");
            validateRecord(lineData);
            records.put(Integer.parseInt(lineData[2]), lineData[3]);
        }
        return records;
    }

    private void validateRecord(String[] lineData) throws DelimiterException {
        if (lineData.length != 4) {
            throw new DelimiterException("Invalid number of fields in a record. Expected: 4");
        }
    }
}
