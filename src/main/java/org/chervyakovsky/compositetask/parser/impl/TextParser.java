package org.chervyakovsky.compositetask.parser.impl;

import org.chervyakovsky.compositetask.composite.TextComponent;
import org.chervyakovsky.compositetask.composite.impl.TextComponentLevel;
import org.chervyakovsky.compositetask.composite.impl.TextComposite;
import org.chervyakovsky.compositetask.parser.TextComponentParser;

public class TextParser implements TextComponentParser {

    private static final String PARAGRAPH_REGEX = "\\t|\\s{4}";
    private final TextComponentParser paragraphParser = new ParagraphParser();

    @Override
    public TextComponent parser(String stringForParsing) {
        TextComponent text = new TextComposite(TextComponentLevel.TEXT);
        String[] paragraphs = stringForParsing.trim().split(PARAGRAPH_REGEX);
        for (String paragraphString : paragraphs) {
            TextComponent paragraph = paragraphParser.parser(paragraphString.trim());
            text.add(paragraph);
        }
        return text;
    }
}
