package org.example.handler;

import org.example.exception.MyArrayDataException;
import org.example.exception.MyArraySizeException;

/**
 * @author BespoyasovaV
 * Класс преобразует мдвумерный массив строк в двумерный массив Int
 */
public class ArrayHandler {
    private void checkSize(String[][] arr) throws MyArraySizeException {
        if (arr.length != 4) {
            throw new MyArraySizeException("Неверный размер массива");
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != 4) {
                throw new MyArraySizeException("Неверный размер массива");
            }
        }
    }

    public Integer[][] toInteger(String[][] arr) throws MyArraySizeException,MyArrayDataException {
        checkSize(arr);
        Integer[][] newArr = new Integer[4][4];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (!arr[i][j].matches("\\d+")) {
                    throw new MyArrayDataException("Неверные данные", i, j);
                }
                newArr[i][j] = Integer.parseInt(arr[i][j]);
            }
        }
        return newArr;
    }

    public void showArray(Integer[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println();
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
        }

    }
}
