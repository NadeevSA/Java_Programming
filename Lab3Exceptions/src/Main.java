public class Main {

    public static void main(String[] args) {
        String[][] array = {
                {"1","2", "3","4"} ,
                {"5","6", "7","8"} ,
                {"9","0", "1","2"} ,
                {"3","4", "5","6"} ,
        };

        try {
            System.out.println(sumArray(array));
        }
        catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e);
        }
    }

    public static int sumArray(String[][] stringArray) throws MyArrayDataException, MyArraySizeException {
        if (stringArray.length == 4) {
            for (int i = 0; i < stringArray.length; i++) {
                if (stringArray[i].length != 4)
                    throw new MyArraySizeException("Размерность массива не соблюдена. " +
                            "В строке " + i + " длина массива равна " + stringArray[i].length);
            }
        }
        else {
            throw new MyArraySizeException("Размерность массива не соблюдена. " +
                    "Массив имеет " + stringArray.length + " строк");
        }

        int sum = 0;
        for (int i = 0; i < stringArray.length; i++) {
            for (int j = 0; j < stringArray[i].length; j++) {
                try {
                    sum += Integer.parseInt(stringArray[i][j]);
                }
                catch (NumberFormatException e) {
                    throw new MyArrayDataException("В ячейке [" + i + "][" + j + "] лежат неверные данные");
                }
            }
        }
        return sum;
    }
}
