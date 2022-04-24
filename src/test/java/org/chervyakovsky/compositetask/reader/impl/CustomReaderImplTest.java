package org.chervyakovsky.compositetask.reader.impl;

import org.chervyakovsky.compositetask.exception.CustomException;
import org.chervyakovsky.compositetask.reader.CustomReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CustomReaderImplTest {

    public static final String FILE_NAME = "test_text.txt";
    private CustomReader reader;

    @BeforeClass
    public void setReader() {
        reader = new CustomReaderImpl();
    }

    @Test
    public void testReadAllFile() {
        String stringFromFile = null;
        try {
            stringFromFile = reader.readAllFile(FILE_NAME);
        } catch (CustomException e) {
            Assert.fail("Could not read the file!" + e.getMessage());
        }
        String expected = "    Three words here." +
                "    This is the second sentence and the second paragraph. There are two sentences, this is the third sentence." +
                "    The sentence with the longest word is amphoheterogony and it is the fourth sentence." +
                "    There are 15 vowels and 23 consonants in a sentence.";
        Assert.assertEquals(stringFromFile, expected);
    }
}