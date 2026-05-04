package com.hopik.task2.parser;

import com.hopik.task2.entity.TextComponent;
import com.hopik.task2.entity.TextComponentType;
import com.hopik.task2.entity.TextComposite;
import com.hopik.task2.exception.TextParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParagraphParser extends AbstractParser {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void parse(TextComponent parent, String text) {
        if (text == null) {
            throw new TextParseException("Text is null");
        }

        String[] paragraphs = text.split(PARAGRAPH_SPLITTER_REGEX);

        for (String paragraph : paragraphs) {

            TextComponent paragraphComponent = new TextComposite(TextComponentType.PARAGRAPH);
            parent.add(paragraphComponent);

            if (next != null) {
                next.parse(paragraphComponent, paragraph.strip());
            }
        }

        logger.info("ParagraphParser: created {} paragraphs", paragraphs.length);
    }
}
