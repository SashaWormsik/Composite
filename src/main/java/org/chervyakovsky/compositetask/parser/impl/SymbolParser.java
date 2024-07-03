package org.chervyakovsky.compositetask.parser.impl;

import org.chervyakovsky.compositetask.composite.impl.Symbol;
import org.chervyakovsky.compositetask.composite.impl.SymbolType;
import org.chervyakovsky.compositetask.composite.TextComponent;
import org.chervyakovsky.compositetask.parser.TextComponentParser;

public class SymbolParser implements TextComponentParser {
    @Override
    public TextComponent parser(String stringForParsing) {
        char currentSymbol = stringForParsing.charAt(0);
        Symbol symbol;
        if (Character.isLetter(currentSymbol)) {
            symbol = new Symbol(currentSymbol, SymbolType.LETTER);
        } else if (Character.isDigit(currentSymbol)) {
            symbol = new Symbol(currentSymbol, SymbolType.DIGIT);
        } else {
            symbol = new Symbol(currentSymbol, SymbolType.PUNCTUATION);
        }
        return symbol;
    }
}
