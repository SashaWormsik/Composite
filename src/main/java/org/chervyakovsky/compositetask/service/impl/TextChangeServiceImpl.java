package org.chervyakovsky.compositetask.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.compositetask.comparator.CustomComparator;
import org.chervyakovsky.compositetask.composite.TextComponent;
import org.chervyakovsky.compositetask.composite.impl.TextComponentLevel;
import org.chervyakovsky.compositetask.exception.CustomException;
import org.chervyakovsky.compositetask.service.TextChangeService;

import java.util.List;

public class TextChangeServiceImpl implements TextChangeService {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void sortParagraphsBySentencesNumber(TextComponent text) throws CustomException {
        if (text.getLevel() != TextComponentLevel.TEXT) {
            LOGGER.error("TextComponent must be TEXT! " + text);
            throw new CustomException("TextComponent must be TEXT! " + text);
        }
        if (text.size() == 0) {
            LOGGER.info("Text is empty. " + text);
            return;
        }
        text.getComponents().sort(CustomComparator.PARAGRAPH);
    }

    @Override
    public void deleteSentenceWithFewerWords(TextComponent text, int wordAmount) throws CustomException {
        if (text.getLevel() != TextComponentLevel.TEXT) {
            LOGGER.error("TextComponent must be TEXT! " + text);
            throw new CustomException("TextComponent must be TEXT! " + text);
        }
        if (text.size() == 0) {
            LOGGER.info("Text is empty. " + text);
            return;
        }
        List<TextComponent> paragraphs = text.getComponents();
        TextComponent currentParagraph;
        for (int i = 0; i < paragraphs.size(); i++) {
            currentParagraph = paragraphs.get(i);
            removeSentencesInParagraph(currentParagraph, wordAmount);
            if(currentParagraph.size() == 0){
                text.remove(currentParagraph);
                i--;
            }
        }
    }

    private void removeSentencesInParagraph(TextComponent paragraph, int wordAmount) {
        List<TextComponent> sentences = paragraph.getComponents();
        TextComponent currentSentence;
        for(int i = 0; i < sentences.size(); i++){
            currentSentence = sentences.get(i);
            if (wordAmount > countNumberWords(currentSentence)){
                paragraph.remove(currentSentence);
                i--;
            }
        }
    }

    private int countNumberWords(TextComponent sentence) {
        int result = 0;
        List<TextComponent> lexemes = sentence.getComponents();
        for (TextComponent lexeme : lexemes) {
            List<TextComponent> lexemeComponents = lexeme.getComponents();
            for (TextComponent lexemeComponent : lexemeComponents) {
                if (lexemeComponent.getLevel() == TextComponentLevel.WORD) {
                    result++;
                }
            }
        }
        return  result;
    }
}
