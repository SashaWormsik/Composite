package org.chervyakovsky.compositetask.interpreter;

import java.util.ArrayList;
import java.util.List;

public class PolishNotationOperator {
    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String MULTIPLY = "*";
    private static final String DIVIDE = "/";


    private PolishNotationOperator() {
    }

    public static List<MathExpression> defineSequence(List<String> polishNotationTokens) {
        List<MathExpression> expressions = new ArrayList<>();
        polishNotationTokens.forEach(token -> {
            switch (token) {
                case PLUS:
                    expressions.add(context -> context.push(context.pop() + context.pop()));
                    break;
                case MINUS:
                    expressions.add(context -> {
                        Double subtrahend = context.pop();
                        Double minuend = context.pop();
                        context.push(minuend - subtrahend);
                    });
                    break;
                case MULTIPLY:
                    expressions.add(context -> context.push(context.pop() * context.pop()));
                    break;
                case DIVIDE:
                    expressions.add(context -> {
                        Double divisor = context.pop();
                        Double dividend = context.pop();
                        context.push(dividend / divisor);
                    });
                    break;
                default:
                    expressions.add(context -> context.push(Double.parseDouble(token)));
                    break;
            }
        });
        return expressions;
    }
}
