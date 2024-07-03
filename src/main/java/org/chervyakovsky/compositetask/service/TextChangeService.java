package org.chervyakovsky.compositetask.service;

import org.chervyakovsky.compositetask.composite.TextComponent;
import org.chervyakovsky.compositetask.exception.CustomException;

public interface TextChangeService {
    void sortParagraphsBySentencesNumber(TextComponent text) throws CustomException;

    void deleteSentenceWithFewerWords(TextComponent text, int wordAmount) throws CustomException;
}
