package com.hopik.task2.parser;

import com.hopik.task2.entity.TextComponent;
import com.hopik.task2.entity.TextComponentType;
import com.hopik.task2.entity.TextComposite;
import com.hopik.task2.exception.TextParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SentenceParser extends AbstractParser {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void parse(TextComponent component, String paragraph) {
        if (paragraph == null) {
            throw new TextParseException("Paragraph is null");
        }

        String[] sentences = paragraph.split(SENTENCE_SPLITTER_REGEX);

        for (String sentence : sentences){
            TextComponent sentenceComponent = new TextComposite(TextComponentType.SENTENCE);
            component.add(sentenceComponent);

            if (next != null){
                next.parse(sentenceComponent, sentence.strip());
            }
        }

        logger.info("SentenceParser: created {} sentences", sentences.length);
    }
}