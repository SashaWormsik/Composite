package org.chervyakovsky.compositetask.interpreter;

@FunctionalInterface
public interface MathExpression {
    void interpret(Context context);
}
