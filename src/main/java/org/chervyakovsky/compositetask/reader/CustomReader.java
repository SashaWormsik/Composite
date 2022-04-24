package org.chervyakovsky.compositetask.reader;

import org.chervyakovsky.compositetask.exception.CustomException;

import java.util.List;

public interface CustomReader {
    String readAllFile(String fileName) throws CustomException;
}
