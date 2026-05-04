package com.hopik.task2.entity;

import com.hopik.task2.exception.TextParseException;

import java.util.List;

public class Punctuation implements TextComponent{
    private final char punctuation;

    public Punctuation(char punctuation){
        this.punctuation = punctuation;
    }

    public char getValue(){
        return punctuation;
    }

    @Override
    public void add(TextComponent component) {
        throw new TextParseException("Punctuation cant have children");
    }

    @Override
    public List<TextComponent> getChildren(){
        throw new TextParseException("Punctuation cant have children");
    }

    @Override
    public TextComponentType getType() {
        return TextComponentType.PUNCTUATION;
    }

    @Override
    public String build() {
        return String.valueOf(punctuation);
    }
}