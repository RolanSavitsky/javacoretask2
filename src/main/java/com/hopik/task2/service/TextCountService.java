package com.hopik.task2.service;

import com.hopik.task2.entity.TextComponent;

public interface TextCountService {
    int countLetters(TextComponent component);
    int countSymbols(TextComponent component);
}
