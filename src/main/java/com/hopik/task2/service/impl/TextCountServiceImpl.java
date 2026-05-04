package com.hopik.task2.service.impl;

import com.hopik.task2.entity.TextComponent;
import com.hopik.task2.entity.TextComponentType;
import com.hopik.task2.service.TextCountService;

public class TextCountServiceImpl implements TextCountService {

    @Override
    public int countLetters(TextComponent component) {
        int count = 0;

        if (component.getType() == TextComponentType.LETTER) {
            return 1;
        } else if (component.getType() == TextComponentType.PUNCTUATION) {
            return 0;
        }

        for (TextComponent child : component.getChildren()) {
            count += countLetters(child);
        }

        return count;
    }

    @Override
    public int countSymbols(TextComponent component) {
        int count = 0;

        if (component.getType() == TextComponentType.LETTER || component.getType() == TextComponentType.PUNCTUATION) {
            return 1;
        }

        for (TextComponent child : component.getChildren()) {
            count += countSymbols(child);
        }

        return count;
    }
}