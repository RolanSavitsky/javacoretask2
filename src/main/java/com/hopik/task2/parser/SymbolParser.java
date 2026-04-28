package com.hopik.task2.parser;

import com.hopik.task2.entity.Letter;
import com.hopik.task2.entity.TextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SymbolParser implements ParserHandler {
    private static final Logger logger = LogManager.getLogger();
    private ParserHandler next;

    @Override
    public void setNext(ParserHandler next) {
        this.next = next;
    }

    @Override
    public void parse(TextComponent component, String data) {
        if (data == null || data.isEmpty()) {
            logger.warn("Empty or null data for SymbolParser");
            return;
        }

        for (char symbol : data.toCharArray()) {
            component.add(new Letter(symbol));
        }

        logger.trace("SymbolParser: created {} letters", data.length());
    }
}