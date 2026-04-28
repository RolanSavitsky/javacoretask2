package com.hopik.task2.parser;

import com.hopik.task2.entity.TextComponent;

public interface ParserHandler {
    void setNext(ParserHandler next);
    void parse(TextComponent component, String data);
}
