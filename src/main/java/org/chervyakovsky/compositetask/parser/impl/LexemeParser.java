package org.chervyakovsky.compositetask.parser.impl;

import org.chervyakovsky.compositetask.composite.TextComponent;
import org.chervyakovsky.compositetask.composite.impl.TextComponentLevel;
import org.chervyakovsky.compositetask.composite.impl.TextComposite;
import org.chervyakovsky.compositetask.parser.TextComponentParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser implements TextComponentParser {

    private static final String START_PUNCTUATION_REGEX = "^\\p{Punct}+";
    private static final String END_PUNCTUATION_REGEX = "\\p{Punct}+$";
    private static final String WORD_REGEX = "(\\p{Alpha}+[\\D]?\\p{Alpha}+[`']?|\\p{Alpha}{1})";
    private static final String MATH_EXPRESSION_REGEX = "[\\d+\\-*/()]{3,}";
    private static final String DIGIT_REGEX = "\\d+\\.?\\d*";

    private final TextComponentParser symbolParser = new SymbolParser();
    private final TextComponentParser wordParser = new WordParser();
    private final TextComponentParser mathExpressionParser = new MathExpressionParser();

    @Override
    public TextComponent parser(String stringForParsing) {
        TextComponent lexeme = new TextComposite(TextComponentLevel.LEXEME);
        Pattern mathExpressionPattern = Pattern.compile(MATH_EXPRESSION_REGEX);
        Matcher matcherMathExpressionMatcher = mathExpressionPattern.matcher(stringForParsing);
        Pattern digitPattern = Pattern.compile(DIGIT_REGEX);
        Matcher digitMatcher = digitPattern.matcher(stringForParsing);
        if (matcherMathExpressionMatcher.matches()) {
            TextComponent mathExpression = mathExpressionParser.parser(stringForParsing);
            lexeme.add(mathExpression);
        } else if (digitMatcher.matches()) {
            for (Character digit : stringForParsing.toCharArray()) {
                TextComponent digitComponent = symbolParser.parser(digit.toString());
                lexeme.add(digitComponent);
            }
        } else {
            Pattern wordPattern = Pattern.compile(WORD_REGEX);
            Matcher wordMatcher = wordPattern.matcher(stringForParsing);
            Pattern startPunctuationPattern = Pattern.compile(START_PUNCTUATION_REGEX);
            Matcher startPunctuationMatcher = startPunctuationPattern.matcher(stringForParsing);
            Pattern endPunctuationPattern = Pattern.compile(END_PUNCTUATION_REGEX);
            Matcher endPunctuationMatcher = endPunctuationPattern.matcher(stringForParsing);
            int start = 0;
            while (startPunctuationMatcher.find(start)) {
                String punct = startPunctuationMatcher.group();
                for (Character character : punct.toCharArray()) {
                    TextComponent symbol = symbolParser.parser(character.toString());
                    lexeme.add(symbol);
                }
                start = startPunctuationMatcher.end();
            }
            while (wordMatcher.find(start)) {
                TextComponent word = wordParser.parser(wordMatcher.group());
                lexeme.add(word);
                start = wordMatcher.end();
            }
            while (endPunctuationMatcher.find(start)) {
                String punct = endPunctuationMatcher.group();
                for (Character character : punct.toCharArray()) {
                    TextComponent symbol = symbolParser.parser(character.toString());
                    lexeme.add(symbol);
                }
                start = endPunctuationMatcher.end();
            }
        }
        return lexeme;
    }
}
