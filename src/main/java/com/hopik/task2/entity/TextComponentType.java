package com.hopik.task2.entity;

public enum TextComponentType {
    TEXT(""),
    PARAGRAPH("\n"),
    SENTENCE(" "),
    LEXEME(""),
    WORD(""),
    PUNCTUATION(""),
    LETTER("");

    private final String delimiter;

    TextComponentType(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
