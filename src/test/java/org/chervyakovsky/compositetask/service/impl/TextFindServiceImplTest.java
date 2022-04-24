package org.chervyakovsky.compositetask.service.impl;

import org.chervyakovsky.compositetask.composite.TextComponent;
import org.chervyakovsky.compositetask.exception.CustomException;
import org.chervyakovsky.compositetask.parser.TextComponentParser;
import org.chervyakovsky.compositetask.parser.impl.TextParser;
import org.chervyakovsky.compositetask.reader.CustomReader;
import org.chervyakovsky.compositetask.reader.impl.CustomReaderImpl;
import org.chervyakovsky.compositetask.service.TextFindService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TextFindServiceImplTest {

    private static final String FILE_NAME = "test_text.txt";
    private CustomReader reader = new CustomReaderImpl();
    private TextComponent component;
    private TextFindService findService = new TextFindServiceImpl();

    @BeforeClass
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
    public void testFindSentenceWithLongestWord() throws CustomException {
        TextComponent actualComponent = findService.findSentenceWithLongestWord(component);
        String actual = actualComponent.toString();
        String expected = "The sentence with the longest word is amphoheterogony and it is the fourth sentence. ";
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindAllRepeatingWords() throws CustomException {
        Map<String, Integer> actual = findService.findAllRepeatingWords(component);
        int actualValueSentence = actual.get("sentence");
        Assert.assertEquals(actualValueSentence, 5);
    }

    @Test
    public void testCountNumberOfVowelsAndConsonants() throws CustomException {
        TextComponent sentence = component.getComponent(3).getComponent(0);
        Map<String, Integer> actual = findService.countNumberOfVowelsAndConsonants(sentence);
        Map<String, Integer> expected = new HashMap<>();
        expected.put("VOWELS", 15);
        expected.put("CONSONANTS", 23);
        Assert.assertEquals(actual, expected);
    }
}