package org.chervyakovsky.compositetask.parser.impl;

import org.chervyakovsky.compositetask.composite.TextComponent;
import org.chervyakovsky.compositetask.composite.impl.TextComponentLevel;
import org.chervyakovsky.compositetask.composite.impl.TextComposite;
import org.chervyakovsky.compositetask.converter.FromInfixToPostfixConverter;
import org.chervyakovsky.compositetask.interpreter.Context;
import org.chervyakovsky.compositetask.interpreter.MathExpression;
import org.chervyakovsky.compositetask.interpreter.PolishNotationOperator;
import org.chervyakovsky.compositetask.parser.TextComponentParser;

import java.util.List;

public class MathExpressionParser implements TextComponentParser {

    private final TextComponentParser symbolParser = new SymbolParser();

    @Override
    public TextComponent parser(String dataString) {
        TextComponent mathExpression = new TextComposite(TextComponentLevel.MATH_EXPRESSION);
        List<String> polishFormTokens = FromInfixToPostfixConverter.convert(dataString);
        List<MathExpression> expressions = PolishNotationOperator.defineSequence(polishFormTokens);
        Context context = new Context();
        expressions.forEach(expression -> expression.interpret(context));
        dataString = context.pop().toString();
        char[] characters = dataString.toCharArray();
        for (Character character : characters) {
            TextComponent symbol = symbolParser.parser(character.toString());
            mathExpression.add(symbol);
        }
        return mathExpression;
    }
}
