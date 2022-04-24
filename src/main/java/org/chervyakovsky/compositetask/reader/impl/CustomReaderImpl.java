package org.chervyakovsky.compositetask.reader.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.compositetask.exception.CustomException;
import org.chervyakovsky.compositetask.reader.CustomReader;
import org.chervyakovsky.compositetask.util.ResourcePathUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CustomReaderImpl implements CustomReader {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String readAllFile(String fileName) throws CustomException {
        fileName = ResourcePathUtil.getResourcePath(fileName);
        checkFile(fileName);
        StringBuilder resultString = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
            while (reader.ready()) {
                resultString.append(reader.readLine());
            }
        } catch (IOException e) {
            LOGGER.error("The problem with reading the file", e);
            throw new CustomException("The problem with reading the file", e);
        }
        return resultString.toString();
    }

    private void checkFile(String fileName) throws CustomException {
        LOGGER.info("Checking file");
        if (fileName == null || fileName.trim().isEmpty()) {
            LOGGER.error("FileName is null or empty string");
            throw new CustomException("FileName is null or empty string");
        }
    }
}
