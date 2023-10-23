package tictactoe;

import java.util.Random;

/**
 * Компьютер.
 */
public class Computer {
    private final CellType cellType;
    private final CellType opponentCellType;
    private final Random rand = new Random();

    public Computer(CellType cellType, CellType opponentCellType) {
        this.cellType = cellType;
        this.opponentCellType = opponentCellType;
    }

    public void move(Field field) {
        RefInt x = new RefInt();
        RefInt y = new RefInt();

        do {
            calculateMove(field, x, y);
        } while (!field.checkEmpty(x.getValue(), y.getValue()));
        field.setSym(x.getValue(), y.getValue(), cellType);

        System.out.println();
        System.out.println("Computer (" + cellType + ") move (X Y): " + (x.getValue() + 1) + " " + (y.getValue() + 1));
        field.printField();
    }

    private void calculateMove(Field field, RefInt coord_x, RefInt coord_y) {
        if (!field.checkForLength(cellType, GameSettings.winCount - 1, coord_x, coord_y)) {
            if (!field.checkForLength(opponentCellType,GameSettings.winCount - 1, coord_x, coord_y)) {
                if (!field.checkForLength(opponentCellType, GameSettings.winCount - 2, coord_x, coord_y)) {
                    if (!field.checkForLength(cellType, GameSettings.winCount - 2, coord_x, coord_y)) {
                        do {
                            coord_x.setValue(rand.nextInt(field.getBorderX()));
                            coord_y.setValue(rand.nextInt(field.getBorderY()));
                        } while (!field.checkEmpty(coord_x.getValue(), coord_y.getValue()));
                    }
                }
            }
        }
    }
}
