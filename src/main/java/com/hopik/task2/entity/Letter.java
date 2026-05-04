package com.hopik.task2.entity;

import java.util.List;

public class Letter implements TextComponent {
    private final char letter;

    public Letter(char letter) {
        this.letter = letter;
    }

    public char getValue(){
        return letter;
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
        return TextComponentType.LETTER;
    }

    @Override
    public String build() {
        return String.valueOf(letter);
    }
}