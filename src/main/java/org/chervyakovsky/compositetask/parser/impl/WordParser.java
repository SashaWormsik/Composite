package org.chervyakovsky.compositetask.parser.impl;

import org.chervyakovsky.compositetask.composite.TextComponent;
import org.chervyakovsky.compositetask.composite.impl.TextComponentLevel;
import org.chervyakovsky.compositetask.composite.impl.TextComposite;
import org.chervyakovsky.compositetask.parser.TextComponentParser;

public class WordParser implements TextComponentParser {

    private final TextComponentParser symbolParser = new SymbolParser();

    @Override
    public TextComponent parser(String stringForParsing) {
        TextComponent word = new TextComposite(TextComponentLevel.WORD);
        char[] wordChars = stringForParsing.toCharArray();
        for (char wordChar : wordChars) {
            TextComponent symbol = symbolParser.parser(Character.toString(wordChar));
            word.add(symbol);
        }
        return word;
    }
}
