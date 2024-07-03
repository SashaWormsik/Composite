package org.chervyakovsky.compositetask.parser;

import org.chervyakovsky.compositetask.composite.TextComponent;

public interface TextComponentParser {
    TextComponent parser(String stringForParsing);
}
