package com.hopik.task2.service.impl;

import com.hopik.task2.entity.Letter;
import com.hopik.task2.entity.TextComponent;
import com.hopik.task2.entity.TextComponentType;
import com.hopik.task2.service.TextOperationService;

import java.util.*;

public class TextOperationServiceImpl implements TextOperationService {

    @Override
    public int maxSentencesWithSameWords(TextComponent root) {
        Map<String, Integer> wordsCount = new HashMap<>();

        for (TextComponent paragraph : root.getChildren()) {
            for (TextComponent sentence : paragraph.getChildren()) {
                Set<String> uniqueWords = new HashSet<>();

                for (TextComponent lexeme : sentence.getChildren()) {
                    String word = getWord(lexeme).toLowerCase();

                    if (!word.isEmpty()) {
                        uniqueWords.add(word);
                    }
                }

                for (String word : uniqueWords) {
                    wordsCount.merge(word, 1, Integer::sum);
                }
            }
        }

        return wordsCount.values()
                .stream()
                .max(Integer::compareTo)
                .orElse(0);
    }

    @Override
    public List<TextComponent> sortSentencesByLetter(TextComponent root, char letter) {
        List<TextComponent> sentences = new ArrayList<>();
        for (TextComponent paragraph : root.getChildren()) {
            sentences.addAll(paragraph.getChildren());
        }

        sentences.sort(Comparator.comparingInt(s -> countLettersInSentence(s, letter)));

        return sentences;
    }

    @Override
    public void swapFirstLastLexeme(TextComponent root) {
        for (TextComponent paragraph : root.getChildren()) {
            for (TextComponent sentence : paragraph.getChildren()) {
                List<TextComponent> lexemes = sentence.getChildren();

                if (lexemes.size() < 2) {
                    continue;
                }

                TextComponent firstLexeme = lexemes.getFirst();
                TextComponent lastLexeme = lexemes.getLast();

                lexemes.set(0, lastLexeme);
                lexemes.set(lexemes.size() - 1, firstLexeme);
            }
        }
    }

    private String getWord(TextComponent lexeme) {
        StringBuilder sb = new StringBuilder();

        for (TextComponent wordOrPunctuation : lexeme.getChildren()) {

            if (wordOrPunctuation.getType() == TextComponentType.WORD) {
                for (TextComponent letter : wordOrPunctuation.getChildren()) {
                    Letter l = (Letter)letter;
                    sb.append(l.getValue());
                }
            }
        }

        return sb.toString();
    }

    private int countLettersInSentence(TextComponent sentence, char letter) {
        int count = 0;
        char target = Character.toLowerCase(letter);

        for (TextComponent lexeme : sentence.getChildren()) {
            for (TextComponent wordOrPunctuation : lexeme.getChildren()) {

                if (wordOrPunctuation.getType() == TextComponentType.WORD) {
                    for (TextComponent symbol : wordOrPunctuation.getChildren()) {
                        Letter l = (Letter) symbol;
                        char c = Character.toLowerCase(l.getValue());

                        if (c == target) {
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }
}