package org.chervyakovsky.compositetask.main;

import org.chervyakovsky.compositetask.composite.TextComponent;
import org.chervyakovsky.compositetask.exception.CustomException;
import org.chervyakovsky.compositetask.parser.TextComponentParser;
import org.chervyakovsky.compositetask.parser.impl.TextParser;
import org.chervyakovsky.compositetask.reader.CustomReader;
import org.chervyakovsky.compositetask.reader.impl.CustomReaderImpl;
import org.chervyakovsky.compositetask.service.TextFindService;
import org.chervyakovsky.compositetask.service.impl.TextFindServiceImpl;

public class Main {
    public static void main(String[] args) throws CustomException {

        CustomReader reader = new CustomReaderImpl();
        String text = reader.readAllFile("text.txt");
        TextComponentParser parser = new TextParser();
        TextComponent component = parser.parser(text);
        System.out.println(component.toString());
        TextFindService findService = new TextFindServiceImpl();
        System.out.println(findService.findSentenceWithLongestWord(component));
        System.out.println(findService.findAllRepeatingWords(component));
        System.out.println(findService.countNumberOfVowelsAndConsonants(component.getComponent(3).getComponent(0)));
    }
}

