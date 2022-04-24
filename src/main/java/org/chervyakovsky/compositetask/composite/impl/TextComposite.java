package org.chervyakovsky.compositetask.composite.impl;

import org.chervyakovsky.compositetask.composite.TextComponent;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {

    private final List<TextComponent> components;
    private final TextComponentLevel level;

    public TextComposite(TextComponentLevel level) {
        this.level = level;
        components = new ArrayList<>();
    }

    @Override
    public boolean add(TextComponent component) {
        return this.components.add(component);
    }

    @Override
    public boolean remove(TextComponent component) {
        return this.components.remove(component);
    }

    @Override
    public TextComponent getComponent(int i) {
        return components.get(i);
    }

    @Override
    public List<TextComponent> getComponents() {
        return components;
    }

    @Override
    public TextComponentLevel getLevel() {
        return level;
    }

    @Override
    public int size() {
        return components.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        TextComposite that = (TextComposite) o;
        return level == that.level &&
                components.equals(that.components);
    }

    @Override
    public int hashCode() {
        int result = level.hashCode();
        result = result + components.hashCode();
        return result;
    }

    @Override
    public String toString() {
        String prefix = level.getPrefix();
        String postfix = level.getPostfix();
        StringBuilder result = new StringBuilder();
        for (TextComponent component : this.components) {
            result.append(prefix).append(component.toString()).append(postfix);
        }
        return result.toString();
    }
}
