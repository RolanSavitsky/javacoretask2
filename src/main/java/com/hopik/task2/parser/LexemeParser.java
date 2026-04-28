package com.hopik.task2.parser;

import com.hopik.task2.entity.TextComponent;
import com.hopik.task2.entity.TextComponentType;
import com.hopik.task2.entity.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LexemeParser implements ParserHandler{
    private static final Logger logger = LogManager.getLogger();
    private static final String LEXEME_SPLITTER_REGEX = "\\s+";
    private ParserHandler next;

    @Override
    public void setNext(ParserHandler next) {
        this.next = next;
    }

    @Override
    public void parse(TextComponent component, String data) {
        if (data == null || data.trim().isEmpty()){
            logger.warn("Empty or null data for LexemeParser");
            return;
        }

        String[] lexemes = data.split(LEXEME_SPLITTER_REGEX);

        for (String lexeme : lexemes){
            if (lexeme.trim().isEmpty()){
                continue;
            }

            TextComponent lexemeComponent = new TextComposite(TextComponentType.LEXEME);
            component.add(lexemeComponent);

            if (next != null){
                next.parse(lexemeComponent, lexeme);
            }

            logger.info("LexemeParser: created {} lexemes", lexemes.length);
        }
    }
}
