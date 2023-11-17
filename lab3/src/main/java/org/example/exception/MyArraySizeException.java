package org.example.exception;

/**
 * @author BespoyasovaV
 * Класс исключение с неверным размером массива
 */
public class MyArraySizeException extends Exception {
    public MyArraySizeException(String message) {
        super(message);
    }
}
