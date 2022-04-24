package org.chervyakovsky.compositetask.parser.impl;

import org.chervyakovsky.compositetask.composite.TextComponent;
import org.chervyakovsky.compositetask.composite.impl.TextComponentLevel;
import org.chervyakovsky.compositetask.composite.impl.TextComposite;
import org.chervyakovsky.compositetask.parser.TextComponentParser;

import java.text.BreakIterator;

public class ParagraphParser implements TextComponentParser {

    //private static final String SENTENCE_REGEX = "\\b[\\w\\p{Space}“”’\\p{Punct}&&[^.?!]]+[.?!]";
    private final TextComponentParser sentenceParser = new SentenceParser();

    @Override
    public TextComponent parser(String stringForParsing) {
        TextComponent paragraph = new TextComposite(TextComponentLevel.PARAGRAPH);
        BreakIterator sentenceIterator = BreakIterator.getSentenceInstance();
        sentenceIterator.setText(stringForParsing);
        int start = sentenceIterator.first();
        for (int end = sentenceIterator.next(); end != BreakIterator.DONE; start = end, end = sentenceIterator.next()) {
            TextComponent sentence = sentenceParser.parser(stringForParsing.substring(start, end).trim());
            paragraph.add(sentence);
        }
        return paragraph;
    }
}
