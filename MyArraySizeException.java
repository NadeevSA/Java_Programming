public class MyArraySizeException extends Exception {
    public MyArraySizeException(int row, int cols) {
        super("Invalid array size! Expected 4x4 array, but got 4x" + cols + " at row " + row);
    }
    
    public MyArraySizeException(int rows) {
        super("Invalid array size! Expected 4 rows, but got " + rows);
    }
}
