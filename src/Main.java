import java.util.Arrays;

public class Main {

    static int getSum(String[][] arr) {
        if (arr.length != 4 || Arrays.stream(arr).anyMatch(e -> e.length != 4)) {
            throw new MyArraySizeException("Размерность массива не 4х4!");
        }

        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    result += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException ex) {
                    throw new MyArrayDataException(ex,i,j);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[][] strArr = {
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"}
        };

        String[][] wrongArrSize = {
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3"},
        };

        String[][] wrongData = {
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "wrongData", "4"},
                {"1", "2", "3", "4"}
        };

        try {
            System.out.println("Сумма элементов массива равна: " + getSum(strArr));
            System.out.println("" + getSum(wrongArrSize));
            System.out.println("" + getSum(wrongData));
        } catch (MyArraySizeException ex) {
            System.out.println(ex.getMessage());
        } catch ( MyArrayDataException ex){
            System.out.println(ex.getMessage());
        }
    }
}
