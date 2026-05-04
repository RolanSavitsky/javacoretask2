package com.hopik.task2.parser;

import com.hopik.task2.entity.TextComponent;
import com.hopik.task2.entity.TextComponentType;
import com.hopik.task2.entity.TextComposite;
import com.hopik.task2.exception.TextParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LexemeParser extends AbstractParser {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void parse(TextComponent component, String sentence) {
        if (sentence == null) {
            throw new TextParseException("Sentence is null");
        }

        String[] lexemes = sentence.split(LEXEME_SPLITTER_REGEX);

        for (String lexeme : lexemes){
            TextComponent lexemeComponent = new TextComposite(TextComponentType.LEXEME);
            component.add(lexemeComponent);

            if (next != null){
                next.parse(lexemeComponent, lexeme.strip());
            }
        }

        logger.info("LexemeParser: created {} lexemes", lexemes.length);
    }
}