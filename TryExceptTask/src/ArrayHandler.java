import Exceptions.MyArraySizeException;
import Exceptions.MyArrayDataException;

public class ArrayHandler {
    int widthMax = 4;
    int lengthMax = 4;

    public int getSum(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length > lengthMax || array[0].length > widthMax)
            throw new MyArraySizeException(String.format("Массив должен быть размера %dx%d", widthMax, lengthMax));

        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    int intConverted = Integer.parseInt(array[i][j]);
                    sum += intConverted;
                }
                catch (NumberFormatException nfe) {
                    throw new MyArrayDataException(j, i);
                }
            }
        }

        return sum;
    }
}
