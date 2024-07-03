package org.chervyakovsky.compositetask.service;

import org.chervyakovsky.compositetask.composite.TextComponent;
import org.chervyakovsky.compositetask.exception.CustomException;

import java.util.Map;

public interface TextFindService {
    TextComponent findSentenceWithLongestWord(TextComponent textComponent) throws CustomException;

    Map<String, Integer> findAllRepeatingWords(TextComponent text) throws CustomException;

    Map<String, Integer>  countNumberOfVowelsAndConsonants(TextComponent sentence) throws CustomException;

}
