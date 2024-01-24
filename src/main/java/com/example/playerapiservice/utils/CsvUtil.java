package com.example.playerapiservice.utils;

import com.example.playerapiservice.exceptions.FileProcessingException;
import com.example.playerapiservice.exceptions.InsufficientFilePermissionsException;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.MappingStrategy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
public class CsvUtil<T> {
    /*
    The CSVReader is an implementation of AutoCloseable, so it can be used with try-with-resources to ensure that the file is closed properly, whether an exception occurs or not
     */
    public List<T> readCsv(String csvFilePath, Class<T> targetClass) throws FileNotFoundException {
        validateFileByPath(csvFilePath);

        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            MappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(targetClass);
            CsvToBean<T> csv = new CsvToBean<>();
            csv.setCsvReader(reader);
            csv.setMappingStrategy(strategy);

            return csv.parse();
        } catch (IOException e) {
            throw new FileProcessingException("Error occurred while processing the file");
        }
    }

    private void validateFileByPath(String csvFilePath) throws FileNotFoundException { // we can extract this method to another validator class
        if(StringUtils.isEmpty(csvFilePath)) {
            throw new IllegalArgumentException("file path is null or empty");
        }
        File file = new File(csvFilePath);
        if (!file.exists()) {
            throw new FileNotFoundException("file not found at path: " + csvFilePath);
        }
        if (!file.canRead()) {
            throw new InsufficientFilePermissionsException("Insufficient permissions to read the file");
        }
    }
}
