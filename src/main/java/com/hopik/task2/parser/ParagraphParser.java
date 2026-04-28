package com.hopik.task2.parser;

import com.hopik.task2.entity.TextComponent;
import com.hopik.task2.entity.TextComponentType;
import com.hopik.task2.entity.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParagraphParser implements ParserHandler {
    private static final Logger logger = LogManager.getLogger();
    private static final String PARAGRAPH_SPLITTER_REGEX = "\\n\\s*\\n";
    private ParserHandler next;

    @Override
    public void setNext(ParserHandler next) {
        this.next = next;
    }

    @Override
    public void parse(TextComponent component, String data) {
        if (data == null || data.trim().isEmpty()) {
            logger.warn("Empty or null data for ParagraphParser");
            return;
        }

        String[] paragraphs = data.split(PARAGRAPH_SPLITTER_REGEX);

        for (String paragraph : paragraphs) {
            if (paragraph.trim().isEmpty()) {
                continue;
            }

            TextComponent paragraphComponent = new TextComposite(TextComponentType.PARAGRAPH);
            component.add(paragraphComponent);

            if (next != null) {
                next.parse(paragraphComponent, paragraph);
            }
        }

        logger.info("ParagraphParser: created {} paragraphs", paragraphs.length);
    }
}
