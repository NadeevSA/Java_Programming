public class MyArrayDataException extends Exception {
    public MyArrayDataException(int x, int y) {
        super("Invalid array value on position: x=" + x + ", y=" + y);
    }
}
