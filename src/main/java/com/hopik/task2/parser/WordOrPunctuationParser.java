package com.hopik.task2.parser;

import com.hopik.task2.entity.Punctuation;
import com.hopik.task2.entity.TextComponent;
import com.hopik.task2.entity.TextComponentType;
import com.hopik.task2.entity.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WordOrPunctuationParser implements ParserHandler{
    private static final Logger logger = LogManager.getLogger();
    private static final String WORD_OR_PUNCTUATION_SPLITTER_REGEX = "(?=[^\\p{L}\\p{N}])|(?<=[^\\p{L}\\p{N}])";
    private static final String WORD_REGEX = "\\p{L}+";
    private ParserHandler next;

    @Override
    public void setNext(ParserHandler next) {
        this.next = next;
    }

    @Override
    public void parse(TextComponent component, String data) {
        if (data == null || data.isEmpty()){
            logger.warn("Empty or null data for WordOrPunctuationParser");
            return;
        }

        String[] parts = data.split(WORD_OR_PUNCTUATION_SPLITTER_REGEX);

        int wordCount = 0;
        int punctuationCount = 0;

        for (String part : parts){
            if (part.trim().isEmpty()){
                continue;
            }

            if (part.matches(WORD_REGEX)){
                TextComponent wordComponent = new TextComposite(TextComponentType.WORD);
                component.add(wordComponent);
                wordCount++;

                if (next != null){
                    next.parse(wordComponent, part);
                }
            } else {
                TextComponent punctuationComponent = new Punctuation(part.charAt(0));
                component.add(punctuationComponent);
                punctuationCount++;
            }
        }

        logger.info("WordOrPunctuationParser: created {} words, {} punctuations", wordCount, punctuationCount);
    }
}
