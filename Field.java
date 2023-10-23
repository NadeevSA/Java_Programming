package tictactoe;

import java.sql.Ref;
import java.util.Arrays;

/**
 * Игровое поле.
 */
public class Field {
    private final char[][] field;
    private int emptyCellCount;

    public int getBorderX() {
        return this.field[0].length;
    }

    public int getBorderY() {
        return this.field.length;
    }

    public Field(int borderX, int borderY) {
        if (borderX < 3 || borderY < 3)  {
            throw new IllegalArgumentException("Field size must be greater than 2.");
        }

        this.field = new char[borderY][borderX];
        this.emptyCellCount = borderY * borderX;
        initField();
    }

    private void initField() {
        for (char[] chars : this.field) {
            Arrays.fill(chars, CellType.empty.type);
        }
    }

    public void printField() {
        System.out.print("   |");
        for (int i = 0; i < getBorderX(); i++) {
            System.out.printf(" %d |", i + 1);
        }
        System.out.println();

        for (int i = 0; i < getBorderX() + 1; i++) {
            System.out.print("---+");
        }
        System.out.println();

        for (int i = 0; i < getBorderY(); i++) {
            System.out.printf(" %d |", i + 1);
            for (int j = 0; j < getBorderX(); j++) {
                System.out.printf(" %c |", this.field[i][j]);
            }
            System.out.println();

            for (int j = 0; j < getBorderX() + 1; j++) {
                System.out.print("---+");
            }
            System.out.println();
        }
    }

    public boolean checkEmpty(int x, int y) {
        if (x < 0 || x >= getBorderX() || y < 0 || y >= getBorderY()) {
            return false;
        }
        return this.field[y][x] == CellType.empty.type;
    }

    public boolean checkFieldFull() {
        return this.emptyCellCount == 0;
    }

    private boolean checkDiagS(RefInt x, RefInt y, CellType cellType, int length) {
        int border = length - 1;

        int count = 0;
        if (x.getValue() + border >= getBorderX() || y.getValue() - border < 0) {
            if (x.getValue() - border  < 0 || y.getValue() + border >= getBorderY()) {
                return false;
            }
        } else {
            for (int i = 0; i < length; i++) {
                if (this.field[y.getValue() - i][x.getValue() + i] == cellType.type) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == length) {
                    if (count == GameSettings.winCount) {
                        return true;
                    } else if (checkEmpty(x.getValue() + count, y.getValue() - count)) {
                        x.setValue(x.getValue() + count);
                        y.setValue(y.getValue() - count);
                        return true;
                    }
                }
            }
        }

        if (x.getValue() - border  < 0 || y.getValue() + border >= getBorderY()) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (this.field[y.getValue() + i][x.getValue() - i] == cellType.type) {
                count++;
            } else {
                count = 0;
            }
            if (count == length) {
                if (count == GameSettings.winCount) {
                    return true;
                } else if (checkEmpty(x.getValue() - count, y.getValue() + count)) {
                    x.setValue(x.getValue() - count);
                    y.setValue(y.getValue() + count);
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkDiagM(RefInt x, RefInt y, CellType cellType, int length) {
        int border = length - 1;

        int count = 0;
        if (x.getValue() + border >= getBorderX() || y.getValue() + border >= getBorderY()) {
            if (x.getValue() - border < 0 || y.getValue() - border < 0) {
                return false;
            }
        } else {
            for (int i = 0; i < length; i++) {
                if (this.field[y.getValue() + i][x.getValue() + i] == cellType.type) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == length) {
                    if (count == GameSettings.winCount) {
                        return true;
                    } else if (checkEmpty(x.getValue() + count, y.getValue() + count)) {
                        x.setValue(x.getValue() + count);
                        y.setValue(y.getValue() + count);
                        return true;
                    }
                }
            }
        }

        if (x.getValue() - border < 0 || y.getValue() - border < 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (this.field[y.getValue() - i][x.getValue() - i] == cellType.type) {
                count++;
            } else {
                count = 0;
            }
            if (count == length) {
                if (count == GameSettings.winCount) {
                    return true;
                } else if (checkEmpty(x.getValue() - count, y.getValue() - count)) {
                    x.setValue(x.getValue() - count);
                    y.setValue(y.getValue() - count);
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkRow(RefInt x, RefInt y, CellType cellType, int length) {
        int count = 0;
        if (x.getValue() + length >= getBorderX()) {
            if (x.getValue() - (length - 1) < 0) {
                return false;
            }
        } else {
            for (int i = 0; i < length; i++) {
                if (this.field[y.getValue()][x.getValue() + i] == cellType.type) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == length) {
                    if (count == GameSettings.winCount) {
                        return true;
                    } else if (checkEmpty(x.getValue() + count, y.getValue())) {
                        x.setValue(x.getValue() + count);
                        return true;
                    }
                }
            }
        }

        if (x.getValue() - (length - 1) < 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (this.field[y.getValue()][x.getValue() - i] == cellType.type) {
                count++;
            } else {
                count = 0;
            }
            if (count == length) {
                if (count == GameSettings.winCount) {
                    return true;
                } else if (checkEmpty(x.getValue() - count, y.getValue())) {
                    x.setValue(x.getValue() - count);
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkCol(RefInt x, RefInt y, CellType cellType, int length) {
        int count = 0;
        if (y.getValue() + length >= getBorderY()) {
            if (y.getValue() - (length - 1) < 0) {
                return false;
            }
        } else {
            for (int i = 0; i < length; i++) {
                if (this.field[y.getValue() + i][x.getValue()] == cellType.type) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == length) {
                    if (count == GameSettings.winCount) {
                        return true;
                    } else if (checkEmpty(x.getValue(), y.getValue() + count)) {
                        y.setValue(y.getValue() + count);
                        return true;
                    }
                }
            }
        }

        if (y.getValue() - (length - 1) < 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (this.field[y.getValue() - i][x.getValue()] == cellType.type) {
                count++;
            } else {
                count = 0;
            }
            if (count == length) {
                if (count == GameSettings.winCount) {
                    return true;
                } else if (checkEmpty(x.getValue(), y.getValue() - count)) {
                    y.setValue(y.getValue() - count);
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkForLength(CellType cellType, int length, RefInt resX, RefInt resY) {
        for (resY.setValue(0); resY.getValue() < getBorderY(); resY.setValue(resY.getValue() + 1)) {
            for (resX.setValue(0); resX.getValue() < getBorderX(); resX.setValue(resX.getValue() + 1)) {
                if (this.field[resY.getValue()][resX.getValue()] == cellType.type) {
                    if (checkDiagS(resX, resY, cellType, length)) {
                        return true;
                    }
                    if (checkRow(resX, resY, cellType, length)) {
                        return true;
                    }
                    if (checkDiagM(resX, resY, cellType, length)) {
                        return true;
                    }
                    if (checkCol(resX, resY, cellType, length)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void setSym(int x, int y, CellType cellType) {
        this.field[y][x] = cellType.type;
        this.emptyCellCount--;
    }
}
