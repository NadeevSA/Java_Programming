package org.example.exception;

/**
 * @author BespoyasovaV
 */
public class MyArrayDataException extends Exception {
    public MyArrayDataException(String message, int i, int j) {
        super(message + " в строке:" + i + " и столбце:" + j);
    }
}
