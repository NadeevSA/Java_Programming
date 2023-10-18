package Utils;

public class LineDecorator {
    public static void drawLine(int size) {
        for (int i = 0; i < size * 5; i++)
            System.out.print("=");
    }
}
