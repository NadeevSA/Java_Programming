package tictactoe;

import java.util.Scanner;

public class User {
    private final CellType cellType;

    private final Scanner scanner = new Scanner(System.in);

    public User(CellType cellType) {
        this.cellType = cellType;
    }

    public void move(Field field) {
        int x, y;

        do {
            System.out.println();
            System.out.print("Type position (X Y): ");
            x = scanner.nextInt();
            y = scanner.nextInt();
        } while (x < 1 || x > field.getBorderX()
                || y < 1 || y > field.getBorderY()
                || !field.checkEmpty(x - 1, y - 1));

        field.setSym(x - 1, y - 1, cellType);
        field.printField();
    }
}
