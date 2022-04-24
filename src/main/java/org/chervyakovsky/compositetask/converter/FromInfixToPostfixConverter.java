package org.chervyakovsky.compositetask.converter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class FromInfixToPostfixConverter {

    private static final String OPERATORS = "+-*/";
    private static final String DELIMITERS = "()";
    private static final String ZERO = "0";
    private static final char RIGHT_BRACKET = ')';
    private static final char LEFT_BRACKET = '(';
    private static final char MINUS = '-';
    private static final char MULTIPLY = '*';
    private static final char DIVIDE = '/';
    private static final char PLUS = '+';

    private FromInfixToPostfixConverter() {
    }

    public static List<String> convert(String infixForm) {
        List<String> postfixForm = new ArrayList<>();
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < infixForm.length(); i++) {
            Character current = infixForm.charAt(i);
            if (isDelimiter(current)) {
                if ((String.valueOf(LEFT_BRACKET)).equals(current.toString())) {
                    stack.push(current);
                } else if ((String.valueOf(RIGHT_BRACKET)).equals(current.toString())) {
                    while (!stack.peek().equals(LEFT_BRACKET)) {
                        postfixForm.add(stack.pop().toString());
                    }
                    stack.pop();
                }
            } else if (isOperator(current)) {
                if (current.equals(MINUS)
                        && (i == 0 || LEFT_BRACKET == infixForm.charAt(i - 1))) {
                    String number = readNumber(infixForm, i + 1);
                    postfixForm.add(ZERO);
                    postfixForm.add(number);
                    postfixForm.add(String.valueOf(MINUS));
                    i += number.length();
                } else {
                    while (!stack.isEmpty()
                            && priority(current) >= priority(stack.peek())
                            && !stack.peek().equals(LEFT_BRACKET)) {
                        postfixForm.add(stack.pop().toString());
                    }

                    stack.push(current);
                }
            } else {
                String number = readNumber(infixForm, i);
                postfixForm.add(number);
                i += number.length() - 1;
            }
        }
        while (!stack.isEmpty()) {
            postfixForm.add(stack.pop().toString());
        }
        return postfixForm;
    }

    private static int priority(Character symbol) {
        int priority = 3;
        if (symbol.equals(LEFT_BRACKET) || symbol.equals(RIGHT_BRACKET)) {
            priority = 0;
        } else if (symbol.equals(MULTIPLY) || symbol.equals(DIVIDE)) {
            priority = 1;
        } else if (symbol.equals(PLUS) || symbol.equals(MINUS)) {
            priority = 2;
        }

        return priority;
    }

    private static String readNumber(String infixForm, int from) {
        String number = "";
        while (infixForm.length() > from
                && !isDelimiter(infixForm.charAt(from))
                && !isOperator(infixForm.charAt(from))) {
            number = number.concat(Character.toString(infixForm.charAt(from)));
            from++;
        }
        return number;
    }

    private static boolean isDelimiter(Character symbol) {
        return DELIMITERS.contains(symbol.toString());
    }

    private static boolean isOperator(Character symbol) {
        return OPERATORS.contains(symbol.toString());
    }
}

