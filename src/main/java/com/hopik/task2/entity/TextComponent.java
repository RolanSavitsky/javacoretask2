package com.hopik.task2.entity;

import java.util.List;

public interface TextComponent {
    void add(TextComponent component);
    List<TextComponent> getChildren();
    TextComponentType getType();
    String build();
}
