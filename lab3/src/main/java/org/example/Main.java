package org.example;

import org.example.exception.MyArrayDataException;
import org.example.exception.MyArraySizeException;
import org.example.handler.ArrayHandler;

/**
 * @author BespoyasovaV
 */
public class Main {
    public static void main(String[] args) {
        String[][] arr = {{"1", "2", "6", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}};
        /** массив не с числами
         * */
        //String [][] arr={{"1","2k","6","4"},{"1","2","3","4"},{"1","2","3","4"},{"1","2","3","4"}};
        /** массив с неверным размером
         * */
        // String [][] arr={{"1","2","6","4"},{"1","2","3","4"},{"1","2","3","4"},{"1","2","3","4"}};

        ArrayHandler handler = new ArrayHandler();
        try {
            Integer[][] newArr = handler.toInteger(arr);
            handler.showArray(newArr);
        } catch (MyArraySizeException e) {
            throw new RuntimeException(e);
        } catch (MyArrayDataException e) {
            throw new RuntimeException(e);
        }

    }
}