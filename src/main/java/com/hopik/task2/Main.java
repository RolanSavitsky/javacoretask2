package com.hopik.task2;

import com.hopik.task2.entity.TextComponent;
import com.hopik.task2.entity.TextComponentType;
import com.hopik.task2.entity.TextComposite;
import com.hopik.task2.exception.TextParseException;
import com.hopik.task2.parser.*;
import com.hopik.task2.reader.TextReader;
import com.hopik.task2.reader.impl.TextReaderImpl;
import com.hopik.task2.service.TextCountService;
import com.hopik.task2.service.TextOperationService;
import com.hopik.task2.service.impl.TextCountServiceImpl;
import com.hopik.task2.service.impl.TextOperationServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    static void main() {
        try {
            TextReader reader = new TextReaderImpl();
            String text = reader.readFromFile("data/input.txt");
            TextComponent root = new TextComposite(TextComponentType.TEXT);

            Parser paragraphParser = new ParagraphParser();
            Parser sentenceParser = new SentenceParser();
            Parser lexemeParser = new LexemeParser();
            Parser wordOrPunctuationParser = new WordOrPunctuationParser();
            Parser symbolParser = new SymbolParser();

            paragraphParser.setNext(sentenceParser);
            sentenceParser.setNext(lexemeParser);
            lexemeParser.setNext(wordOrPunctuationParser);
            wordOrPunctuationParser.setNext(symbolParser);

            paragraphParser.parse(root, text);

            System.out.println("Restored text");
            System.out.println(root.build());

            TextCountService countService = new TextCountServiceImpl();
            TextOperationService operationService = new TextOperationServiceImpl();

            int letters = countService.countLetters(root);
            int symbols = countService.countSymbols(root);

            System.out.println("Letter = " + letters);
            System.out.println("Symbols = " + symbols);

            System.out.println("Task 1");
            int max = operationService.maxSentencesWithSameWords(root);
            System.out.println("Max sentences with same words: " + max);

            System.out.println("Task 2 (sorted by 'o')");

            List<TextComponent> sortedSentences = operationService.sortSentencesByLetter(root, 'o');
            for (TextComponent sentence : sortedSentences){
                System.out.println(sentence.build());
            }

            System.out.println("Task 3");
            operationService.swapFirstLastLexeme(root);
            System.out.println(root.build());

        } catch (TextParseException e) {
            logger.error("Application error", e);
        }
    }
}