package org.chervyakovsky.compositetask.composite.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.compositetask.composite.TextComponent;

import java.util.List;

public class Symbol implements TextComponent {

    private static final Logger LOGGER = LogManager.getLogger();

    private SymbolType type;
    private char aChar;

    public Symbol(char aChar, SymbolType type){
        this.aChar = aChar;
        this.type = type;
    }

    @Override
    public boolean add(TextComponent component) {
        LOGGER.error("Unavailable operation. It is impossible to perform an action on the smallest indivisible element!");
        throw new UnsupportedOperationException("Unavailable operation. It is impossible to perform an action on the smallest indivisible element");
    }

    @Override
    public boolean remove(TextComponent component) {
        LOGGER.error("Unavailable operation. It is impossible to perform an action on the smallest indivisible element!");
        throw new UnsupportedOperationException("Unavailable operation. It is impossible to perform an action on the smallest indivisible element");
    }

    @Override
    public List<TextComponent> getComponents() {
        LOGGER.error("Unavailable operation. It is impossible to perform an action on the smallest indivisible element!");
        throw new UnsupportedOperationException("Unavailable operation. It is impossible to perform an action on the smallest indivisible element");
    }

    @Override
    public TextComponent getComponent(int i) {
        LOGGER.error("Unavailable operation. It is impossible to perform an action on the smallest indivisible element!");
        throw new UnsupportedOperationException("Unavailable operation. It is impossible to perform an action on the smallest indivisible element");
    }

    @Override
    public TextComponentLevel getLevel() {
        return TextComponentLevel.SYMBOL;
    }

    @Override
    public int size() {
        return 1;
    }

    public SymbolType getSymbolType(){
        return this.type;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Symbol symbol = (Symbol) o;
        return  this.aChar == symbol.aChar && type.equals(symbol.type);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + (int) aChar;
        return result;
    }

    @Override
    public String toString(){
        return Character.toString(aChar);
    }
}
