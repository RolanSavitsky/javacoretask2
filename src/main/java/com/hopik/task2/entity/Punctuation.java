package com.hopik.task2.entity;

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
        throw new UnsupportedOperationException();
    }

    @Override
    public List<TextComponent> getChildren(){
        throw new UnsupportedOperationException();
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