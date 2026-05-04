package com.hopik.task2.service;

import com.hopik.task2.entity.TextComponent;

import java.util.List;

public interface TextOperationService {
    int maxSentencesWithSameWords(TextComponent root);
    List<TextComponent> sortSentencesByLetter(TextComponent root, char letter);
    void swapFirstLastLexeme(TextComponent root);
}