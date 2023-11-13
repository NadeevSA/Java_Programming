package Exceptions;

public class MyArrayDataException extends Exception {

    public MyArrayDataException(int xCoord, int yCoord) {
        super(String.format("Невозможно преобразовать строку в int в ячейке с координатами [%d;%d]", yCoord, xCoord));
    }
}
