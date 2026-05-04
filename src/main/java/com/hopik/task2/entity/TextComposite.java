package com.hopik.task2.entity;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent{
    private final TextComponentType type;
    private final List<TextComponent> children = new ArrayList<>();

    public TextComposite(TextComponentType type){
        this.type = type;
    }

    @Override
    public void add(TextComponent component) {
        children.add(component);
    }

    @Override
    public List<TextComponent> getChildren() {
        return children;
    }

    @Override
    public TextComponentType getType(){
        return type;
    }

    @Override
    public String build() {
        StringBuilder sb = new StringBuilder();

        for(TextComponent child : children){
            sb.append(child.build());
            sb.append(type.getDelimiter());
        }

        return sb.toString().strip();
    }
}