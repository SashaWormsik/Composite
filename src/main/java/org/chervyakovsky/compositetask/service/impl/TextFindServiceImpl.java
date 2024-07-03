package org.chervyakovsky.compositetask.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.compositetask.composite.TextComponent;
import org.chervyakovsky.compositetask.composite.impl.TextComponentLevel;
import org.chervyakovsky.compositetask.exception.CustomException;
import org.chervyakovsky.compositetask.service.TextFindService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextFindServiceImpl implements TextFindService {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String IS_VOWELS_REGEX = "[aeiou]";

    @Override
    public TextComponent findSentenceWithLongestWord(TextComponent text) throws CustomException {
        if (text.getLevel() != TextComponentLevel.TEXT) {
            LOGGER.error("TextComponent must be TEXT! " + text);
            throw new CustomException("TextComponent must be TEXT! " + text);
        }
        if (text.size() == 0) {
            LOGGER.info("Text is empty. " + text);
            throw new CustomException("TEXT is empty " + text);
        }
        TextComponent resultSentence = null;
        int max = 0;
        int temp;
        for (TextComponent paragraph : text.getComponents()) {
            for (TextComponent sentence : paragraph.getComponents()) {
                temp = determineLengthLongestWord(sentence);
                if (temp > max) {
                    resultSentence = sentence;
                    max = temp;
                }
            }
        }
        return resultSentence;
    }

    @Override
    public Map<String, Integer> findAllRepeatingWords(TextComponent text) throws CustomException {
        Map<String, Integer> result = new HashMap<>();
        if (text.getLevel() != TextComponentLevel.TEXT) {
            LOGGER.error("TextComponent must be TEXT! " + text);
            throw new CustomException("TextComponent must be TEXT! " + text);
        }
        if (text.size() == 0) {
            LOGGER.info("TEXT is empty. " + text);
            throw new CustomException("TEXT is empty " + text);
        }
        for (TextComponent paragraph : text.getComponents()) {
            for (TextComponent sentence : paragraph.getComponents()) {
                for (TextComponent lexeme : sentence.getComponents()) {
                    for (TextComponent lexemeComponent : lexeme.getComponents()) {
                        if (lexemeComponent.getLevel() == TextComponentLevel.WORD) {
                            String word = lexemeComponent.toString().toLowerCase();
                            result.computeIfPresent(word, (key, value) -> ++value);
                            result.putIfAbsent(word, 1);
                        }
                    }
                }
            }
        }
        result = result.entrySet().stream()
                .filter(x -> x.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return result;
    }

    @Override
    public Map<String, Integer> countNumberOfVowelsAndConsonants(TextComponent sentence) throws CustomException {
        Map<String, Integer> result = new HashMap<>();
        result.put("VOWELS", 0);
        result.put("CONSONANTS", 0);
        if (sentence.getLevel() != TextComponentLevel.SENTENCE) {
            LOGGER.error("TextComponent must be SENTENCE! " + sentence);
            throw new CustomException("TextComponent must be SENTENCE! " + sentence);
        }
        if (sentence.size() == 0) {
            LOGGER.info("SENTENCE is empty. " + sentence);
            throw new CustomException("SENTENCE is empty " + sentence);
        }
        for (TextComponent lexeme : sentence.getComponents()) {
            for (TextComponent lexemeComponent : lexeme.getComponents()) {
                if (lexemeComponent.getLevel() == TextComponentLevel.WORD) {
                    for (TextComponent symbol : lexemeComponent.getComponents()) {
                        if (isVowels(symbol.toString())) {
                            result.computeIfPresent("VOWELS", (key, value) -> ++value);
                        } else {
                            result.computeIfPresent("CONSONANTS", (key, value) -> ++value);
                        }
                    }
                }
            }
        }
        return result;
    }

    private int determineLengthLongestWord(TextComponent sentence) {
        int max = 0;
        int temp;
        List<TextComponent> lexemes = sentence.getComponents();
        for (TextComponent lexeme : lexemes) {
            List<TextComponent> lexemeComponents = lexeme.getComponents();
            for (TextComponent lexemeComponent : lexemeComponents) {
                if (lexemeComponent.getLevel() == TextComponentLevel.WORD) {
                    temp = lexemeComponent.size();
                    if (temp > max) {
                        max = temp;
                    }
                }
            }
        }
        return max;
    }

    private boolean isVowels(String symbol) {
        Pattern isVowelSymbol = Pattern.compile(IS_VOWELS_REGEX);
        Matcher matcher = isVowelSymbol.matcher(symbol);
        return matcher.matches();
    }
}
