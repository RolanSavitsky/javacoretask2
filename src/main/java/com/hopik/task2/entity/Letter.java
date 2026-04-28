package com.hopik.task2.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Letter implements TextComponent{
    private final static Logger logger = LogManager.getLogger();
    private char letter;

    public Letter(char letter){
        this.letter = letter;
    }

    @Override
    public void add(TextComponent component) {
        logger.warn("Skipped attempt to add component to leaf element");
    }

    @Override
    public void remove(TextComponent component) {
        logger.warn("Skipped attempt to remove component from leaf element");
    }
}
