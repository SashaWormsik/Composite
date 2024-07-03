package org.chervyakovsky.compositetask.service.impl;

import org.chervyakovsky.compositetask.composite.TextComponent;
import org.chervyakovsky.compositetask.exception.CustomException;
import org.chervyakovsky.compositetask.parser.TextComponentParser;
import org.chervyakovsky.compositetask.parser.impl.TextParser;
import org.chervyakovsky.compositetask.reader.CustomReader;
import org.chervyakovsky.compositetask.reader.impl.CustomReaderImpl;
import org.chervyakovsky.compositetask.service.TextChangeService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TextChangeServiceImplTest {

    private static final String FILE_NAME = "test_text.txt";
    private CustomReader reader = new CustomReaderImpl();
    private TextComponent component;
    private TextChangeService changeService = new TextChangeServiceImpl();

    @BeforeMethod
    public void setComponent() {
        String text = null;
        try {
            text = reader.readAllFile(FILE_NAME);
        } catch (CustomException e) {
            Assert.fail("Could not read the file!" + e.getMessage());
        }
        TextComponentParser parser = new TextParser();
        component = parser.parser(text);
    }

    @Test
    public void testSortParagraphsBySentencesNumber() throws CustomException {
        String expected = "\tThree words here. \n" +
                "\tThe sentence with the longest word is amphoheterogony and it is the fourth sentence. \n" +
                "\tThere are 15 vowels and 23 consonants in a sentence. \n" +
                "\tThis is the second sentence and the second paragraph. There are two sentences, this is the third sentence. \n";
        changeService.sortParagraphsBySentencesNumber(component);
        Assert.assertEquals(component.toString(), expected);
    }

    @Test
    public void testDeleteSentenceWithFewerWords() throws CustomException {
        String expected = "\tThis is the second sentence and the second paragraph. There are two sentences, this is the third sentence. \n" +
                "\tThe sentence with the longest word is amphoheterogony and it is the fourth sentence. \n" +
                "\tThere are 15 vowels and 23 consonants in a sentence. \n";
        changeService.deleteSentenceWithFewerWords(component, 4);
        Assert.assertEquals(component.toString(), expected);
    }
}