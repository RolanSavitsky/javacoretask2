package com.hopik.task2.entity;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent{
    private TextComponentType type;
    private List<TextComponent> components = new ArrayList<TextComponent>();

    public TextComposite(TextComponentType type){
        this.type = type;
    }

    public TextComponentType getType(){
        return type;
    }

    @Override
    public void add(TextComponent component) {
        components.add(component);
    }

    @Override
    public void remove(TextComponent component) {
        component.remove(component);
    }
}