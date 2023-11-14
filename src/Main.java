import java.util.Random;

public class Main {
    public static int rows = 4;
    public static int cols = 4;
    public static void main(String[] args) {
        String[][] array = RandArray();
        //String[][] array = {{"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}};
        try {
            int result = sumArrayElements(array);
            System.out.println("Сумма = " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        } finally {
            PrintArr(array);
        }
    }
    public static int sumArrayElements(String[][] array) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;
        if (array.length != 4 || array[0].length != 4) {
            throw new MyArraySizeException("Размер массива должен быть 4x4");
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) {
                throw new MyArraySizeException("Размер массива должен быть 4x4");
            }
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Неверные данные в ячейке: [" + i + ", " + j + "]");
                }
            }
        }
        return sum;
    }
    public static String[][] RandArray(){
        String[][] arr = new String[rows][cols];

        Random rand = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int item = rand.nextInt(100);
                if(item == 0){
                    arr[i][j] = "ноль";
                }
                else {
                    arr[i][j] = Integer.toString(item);
                }
            }
        }
        return arr;
    }
    public static void PrintArr(String[][] arr){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}