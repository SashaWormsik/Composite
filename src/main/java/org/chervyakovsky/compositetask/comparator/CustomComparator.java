package org.chervyakovsky.compositetask.comparator;

import org.chervyakovsky.compositetask.composite.TextComponent;

import java.util.Comparator;

public enum CustomComparator implements Comparator<TextComponent> {
    PARAGRAPH {
        @Override
        public int compare(TextComponent o1, TextComponent o2) {
            return Integer.compare(o1.size(), o2.size());
        }
    }
}
