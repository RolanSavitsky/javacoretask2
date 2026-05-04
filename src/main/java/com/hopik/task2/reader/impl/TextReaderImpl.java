package com.hopik.task2.reader.impl;

import com.hopik.task2.exception.TextParseException;
import com.hopik.task2.reader.TextReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextReaderImpl implements TextReader {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String readFromFile(String filePath) throws TextParseException {
        Path path = Paths.get(filePath);

        try {
            return Files.readString(path);
        } catch (IOException e){
            logger.error("File reading error: {}", filePath, e);
            throw new TextParseException("File reading error: ", e);
        }
    }
}
