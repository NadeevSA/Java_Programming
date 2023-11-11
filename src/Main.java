import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        var source = new String[4][4];
        for (String[] strings : source) {
            Arrays.fill(strings, "10");
        }

        try {
            var result = ParseArray(source);
            System.out.println(result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e);
        }
    }

    public static int ParseArray(String[][] source) throws MyArraySizeException, MyArrayDataException {
        if (source.length != 4) {
            throw new MyArraySizeException("Wrong array size.");
        }
        for (String[] strings : source) {
            if (strings.length != 4) {
                throw new MyArraySizeException("Wrong array size.");
            }
        }

        var result = 0;
        for (int i = 0; i < source.length; ++i) {
            for (int j = 0; j < source[i].length; ++j) {
                try{
                    var currentNumber = Integer.parseInt(source[i][j]);
                    result += currentNumber;
                }
                catch (NumberFormatException ex){
                    throw new MyArrayDataException("Parsing error: cells (" + i + ", " + j + ").");
                }
            }
        }
        return result;
    }
}