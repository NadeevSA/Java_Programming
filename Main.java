public class Main {
    public static void main(String[] args) {
        String[][] array = {
            {"1", "2", "3", "4"},
            {"5", "6", "7", "8"},
            {"9", "1", "2", "3"},
            {"4", "5", "6", "7"},
        };

        try {
            var arraySum = arraySum(array);

            System.out.println(arraySum);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e);
        }
    }

    public static int arraySum(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length == 4) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].length != 4) {
                    throw new MyArraySizeException(i, array[i].length);
                }
            }
        } else {
            throw new MyArraySizeException(array.length);
        }

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                }
                catch (NumberFormatException e) {
                    throw new MyArrayDataException(j, i);
                }
            }
        }
        return sum;
    }
}
