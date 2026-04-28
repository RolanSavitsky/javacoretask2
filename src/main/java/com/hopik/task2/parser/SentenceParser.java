package com.hopik.task2.parser;

import com.hopik.task2.entity.TextComponent;
import com.hopik.task2.entity.TextComponentType;
import com.hopik.task2.entity.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SentenceParser implements ParserHandler{
    private static final Logger logger = LogManager.getLogger();
    private static final String SENTENCE_SPLITTER_REGEX = "(?<=[.!?])\\s*";
    private ParserHandler next;

    @Override
    public void setNext(ParserHandler next) {
        this.next = next;
    }

    @Override
    public void parse(TextComponent component, String data) {
        if (data == null || data.trim().isEmpty()) {
            logger.warn("Empty or null data for SentenceParser");
            return;
        }

        String[] sentences = data.split(SENTENCE_SPLITTER_REGEX);

        for (String sentence : sentences){
            if (sentence.trim().isEmpty()){
                continue;
            }

            TextComponent sentenceComponent = new TextComposite(TextComponentType.SENTENCE);
            component.add(sentenceComponent);

            if (next != null){
                next.parse(sentenceComponent, sentence);
            }
        }

        logger.info("SentenceParser: created {} sentences", sentences.length);
    }
}
