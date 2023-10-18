package Utils;

public class CharArrCopier {
    public static char[][] getCopyOf2DArray(char[][] array) {
        if (array == null) {
            return null;
        }

        char[][] copy = new char[array.length][];

        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i].clone();
        }

        return copy;
    }
}
