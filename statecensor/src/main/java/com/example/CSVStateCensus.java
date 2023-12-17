package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import com.exceptions.*;

public class CSVStateCensus {
    private String filepath;

    public CSVStateCensus(String filePath) {
        this.filepath = filePath;
    }

    public Iterator<String[]> loadDataFromFile()
            throws FileFormatException, IncorrectHeaderException, FileReaderException, DelimiterException {
        validateFileFormat();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            validateHeader(reader.readLine());

            ArrayList<String[]> records = readRecords(reader);

            return records.iterator();
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

    private ArrayList<String[]> readRecords(BufferedReader reader) throws DelimiterException, IOException {
        ArrayList<String[]> records = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] lineData = line.split(",");
            validateRecord(lineData);
            records.add(lineData);
        }
        return records;
    }

    private void validateRecord(String[] lineData) throws DelimiterException {
        if (lineData.length != 4) {
            throw new DelimiterException("Invalid number of fields in a record. Expected: 4");
        }
    }
}
