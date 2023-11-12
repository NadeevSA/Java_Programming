import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int y = 4;
        int x = 4;
        var stringArray = new String[y][x];

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                stringArray[i][j] = (i * 4 + j) + "";
            }
        }

        try {
            System.out.println("Сумма элементов массива: " + getArraySum(stringArray));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int getArraySum(String[][] stringArray) throws MyArrayDataException, MyArraySizeException {
        int y = 4;
        int x = 4;

        if (stringArray.length != y)
            throw new MyArraySizeException("Недопустимый размер массива");

        for (int i = 0; i < y; i++)
            if (stringArray[i].length != x)
                throw new MyArraySizeException("Недопустимый размер массива");

        var result = 0;
        for (int i = 0; i < y; ++i) {
            for (int j = 0; j < x; ++j) {
                try {
                    result += Integer.parseInt(stringArray[i][j]);
                } catch (NumberFormatException ex) {
                    throw new MyArrayDataException("Недопустимый формат данных");
                }
            }
        }

        return result;
    }
}