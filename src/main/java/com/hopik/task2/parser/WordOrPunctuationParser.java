package com.hopik.task2.parser;

import com.hopik.task2.entity.Punctuation;
import com.hopik.task2.entity.TextComponent;
import com.hopik.task2.entity.TextComponentType;
import com.hopik.task2.entity.TextComposite;
import com.hopik.task2.exception.TextParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WordOrPunctuationParser extends AbstractParser {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void parse(TextComponent component, String lexeme) {
        if (lexeme == null) {
            throw new TextParseException("Lexeme is null");
        }

        String[] parts = lexeme.split(WORD_OR_PUNCTUATION_SPLITTER_REGEX);

        int wordCount = 0;
        int punctuationCount = 0;

        for (String part : parts){
            if (part.matches(WORD_REGEX)){
                TextComponent wordComponent = new TextComposite(TextComponentType.WORD);
                component.add(wordComponent);
                wordCount++;

                if (next != null){
                    next.parse(wordComponent, part.strip());
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