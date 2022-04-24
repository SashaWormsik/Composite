package org.chervyakovsky.compositetask.composite.impl;

public enum TextComponentLevel {

    TEXT("\t", "\n"),
    PARAGRAPH("", ""),
    SENTENCE("", " "),
    LEXEME("", ""),
    MATH_EXPRESSION("", ""),
    WORD("", ""),
    SYMBOL("", "");
    private final String prefix;
    private final String postfix;

    TextComponentLevel(String prefix, String postfix) {
        this.prefix = prefix;
        this.postfix = postfix;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getPostfix() {
        return postfix;
    }
}

