package com.hopik.task2.parser;

import com.hopik.task2.entity.TextComponent;
import com.hopik.task2.exception.TextParseException;

public interface Parser {
    String PARAGRAPH_SPLITTER_REGEX = "\\n\\s*\\n";
    String SENTENCE_SPLITTER_REGEX = "(?<=[.!?])\\s*";
    String LEXEME_SPLITTER_REGEX = "\\s+";
    String WORD_OR_PUNCTUATION_SPLITTER_REGEX = "(?=[^\\p{L}\\p{N}])|(?<=[^\\p{L}\\p{N}])";
    String WORD_REGEX = "\\p{L}+";

    void setNext(Parser next);
    void parse(TextComponent component, String data)  throws TextParseException;
}
