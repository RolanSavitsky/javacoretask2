package com.hopik.task2.reader;

import com.hopik.task2.exception.TextParseException;

public interface TextReader {
    String readFromFile(String filePath) throws TextParseException;
}
