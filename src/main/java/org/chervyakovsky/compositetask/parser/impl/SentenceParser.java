package org.chervyakovsky.compositetask.parser.impl;

import org.chervyakovsky.compositetask.composite.TextComponent;
import org.chervyakovsky.compositetask.composite.impl.TextComponentLevel;
import org.chervyakovsky.compositetask.composite.impl.TextComposite;
import org.chervyakovsky.compositetask.parser.TextComponentParser;

public class SentenceParser implements TextComponentParser {
    private static final String LEXEME_DELIMITER_REGEX = "\\s+";
    private final TextComponentParser lexemeParser = new LexemeParser();

    @Override
    public TextComponent parser(String stringForParsing) {
        TextComponent sentence = new TextComposite(TextComponentLevel.SENTENCE);
        String[] lexemes = stringForParsing.split(LEXEME_DELIMITER_REGEX);
        for (String lexemeString : lexemes) {
            TextComponent lexeme = lexemeParser.parser(lexemeString);
            sentence.add(lexeme);
        }
        return sentence;
    }
}
