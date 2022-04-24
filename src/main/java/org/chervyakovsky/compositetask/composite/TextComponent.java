package org.chervyakovsky.compositetask.composite;

import org.chervyakovsky.compositetask.composite.impl.TextComponentLevel;

import java.util.List;

public interface TextComponent {

    boolean add(TextComponent component);

    boolean remove(TextComponent component);

    List<TextComponent> getComponents();

    TextComponent getComponent(int i);

    TextComponentLevel getLevel();

    int size();

    @Override
    String toString();

    @Override
    boolean equals(Object obj);

    @Override
    int hashCode();
}
