package com.hopik.task2.parser;

import com.hopik.task2.entity.Letter;
import com.hopik.task2.entity.TextComponent;
import com.hopik.task2.exception.TextParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SymbolParser extends AbstractParser {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void parse(TextComponent component, String word) {
        if (word == null) {
            throw new TextParseException("Word is null");
        }

        for (char symbol : word.toCharArray()) {
            component.add(new Letter(symbol));
        }

        logger.info("SymbolParser: created {} letters", word.length());
    }
}