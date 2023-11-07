package org.example;

import java.util.*;

public class Main {
    static final int Y = 0;
    static final int X = 1;
    static final int DY = 2;
    static final int DX = 3;
    static final int[] VERTICAL_VECTOR = new int[]{1, 0};
    static final int[] HORIZONTAL_VECTOR = new int[]{0, 1};
    static final int[] MAIN_DIAGONAL_VECTOR = new int[]{1, 1};
    static final int[] SECOND_DIAGONAL_VECTOR = new int[]{1, -1};

    static final char PLAYER_DOT = 'X';
    static final char AI_DOT = '0';
    static final char EMPTY_DOT = '.';

    static int COLUMNS = 4;
    static int ROWS = 4;
    static int WINLINELENGTH = 3;

    static char[][] field;
    static ArrayList<int[]> playerMoves = new ArrayList<int[]>();
    static ArrayList<int[]> aiMoves = new ArrayList<int[]>();

    static Scanner scanner = new Scanner(System.in);
    static final Random rand = new Random();

    private static void inputGameParams() {
        System.out.println("Введите размер поля: X Y");
        COLUMNS = scanner.nextInt();
        ROWS = scanner.nextInt();
        do {
            System.out.println("Введите длину победной серии");
            WINLINELENGTH = scanner.nextInt();
        } while (WINLINELENGTH > ROWS || WINLINELENGTH > COLUMNS);
    }

    private static void initField() {
        field = new char[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }

    private static void printField() {
        System.out.println("-------");
        for (int i = 0; i < ROWS; i++) {
            System.out.print("|");
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(field[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println("-------");
    }

    private static void setSym(int y, int x, char sym) {
        field[y][x] = sym;
    }

    private static void playerStep() {
        int x;
        int y;
        do {
            System.out.println("Введите координаты: X Y");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isEmptyCell(y, x));
        setSym(y, x, PLAYER_DOT);
        playerMoves.add(new int[]{y, x});
    }

    private static boolean isEqualList(ArrayList<int[]> list1, ArrayList<int[]> list2) {
        if (list1.size() != list2.size())
            return false;
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i)[Y] != list2.get(i)[Y] || list1.get(i)[X] != list2.get(i)[X])
                return false;
        }

        return true;
    }

    private static int getMaxLengthByDirection(int[] move, int[] vector, char sym) {
        int dy = vector[Y];
        int dx = vector[X];
        int length = 1;
        for (int j = 1; ; j++) {
            boolean isGoodCell1 = isGoodCell(move[Y] + j * dy, move[X] + j * dx, sym);
            boolean isGoodCell2 = isGoodCell(move[Y] - j * dy, move[X] - j * dx, sym);
            if (!isGoodCell2 && !isGoodCell1)
                break;
            if (isGoodCell1)
                length++;
            if (isGoodCell2)
                length++;
        }

        return length;
    }

    private static boolean checkWinPossibility(int[] move, int[] vector, char sym) {
        return getMaxLengthByDirection(move, vector, sym) >= WINLINELENGTH;
    }

    private static ArrayList<ArrayList<int[]>> getMaxLinesByDirection(ArrayList<int[]> moves, int[] vector) {

        if (moves.isEmpty())
            return null;
        char sym = field[moves.getFirst()[Y]][moves.getFirst()[X]];
        int dx = vector[X];
        int dy = vector[Y];
        if (!isDeltaValid(dx, dy))
            return null;

        var sortedMoves = sortMoves(moves, false);
        var maxLines = new ArrayList<ArrayList<int[]>>();

        for (int i = 0; i < sortedMoves.size(); i++) {
            var line = new ArrayList<int[]>();
            var move = sortedMoves.get(i);

            if (checkWinPossibility(move, vector, sym)) {
                var firstCell = move;
                int absDx = Math.abs(dx);
                int absDy = Math.abs(dy);
                //треш
                for (int j = 1, x = firstCell[X] - absDx, y = firstCell[Y] - absDy;
                     j < WINLINELENGTH && isCellValid(y, x) && field[y][x] == sym;
                     j++, x -= absDx, y -= absDy) {
                    firstCell = new int[]{firstCell[Y] - absDy, firstCell[X] - absDx};
                }

                line.add(new int[]{firstCell[Y], firstCell[X], dy, dx});
                for (int j = 1; isCellValid(move[Y] + j * dy, move[X] + j * dx); j++) {
                    if (!containMove(sortedMoves, move[Y] + j * dy, move[X] + j * dx)) {
                        break;
                    }
                    line.add(new int[]{move[Y] + j * dy, move[X] + j * dx, dy, dx});
                }
                maxLines.add(line);
            }
        }

        return maxLines;
    }

    private static int[] getIntermediateMoveByDirection(ArrayList<int[]> sortedMoves, int[] vector) {
        int dy = vector[Y];
        int dx = vector[X];

        for (int i = 0; i < sortedMoves.size(); i++) {
            //флаг, можем ли пропустить одну пустую ячейку, чтобы проверить следующие ячейки, лучшего названия переменной не придумал
            boolean flag = true;
            int[] emptyCell = null;
            int lineLength = 1;
            var move = sortedMoves.get(i);
            for (int y = move[Y] + dy, x = move[X] + dx;
                 isCellValid(y, x);
                 y += dy, x += dx) {
                if (!containMove(sortedMoves, y, x)) {
                    if (field[y][x] != EMPTY_DOT || !flag)
                        break;
                    flag = false;
                    emptyCell = new int[]{y, x};
                }
                lineLength++;
                if (emptyCell != null && lineLength == WINLINELENGTH)
                    return emptyCell;
            }
        }

        return null;
    }

    private static int[] getIntermediateMove(ArrayList<int[]> moves) {
        var sortedMoves = sortMoves(moves, false);

        var intermediateMove = getIntermediateMoveByDirection(sortedMoves, VERTICAL_VECTOR);
        if (intermediateMove == null)
            intermediateMove = getIntermediateMoveByDirection(sortedMoves, HORIZONTAL_VECTOR);
        if (intermediateMove == null)
            intermediateMove = getIntermediateMoveByDirection(sortedMoves, MAIN_DIAGONAL_VECTOR);
        if (intermediateMove == null)
            intermediateMove = getIntermediateMoveByDirection(sortedMoves, SECOND_DIAGONAL_VECTOR);
        return intermediateMove;
    }

    private static ArrayList<int[]> getBestLine(ArrayList<int[]> moves) {
        var lines = new ArrayList<ArrayList<int[]>>();

        lines.addAll(getMaxLinesByDirection(moves, VERTICAL_VECTOR));
        lines.addAll(getMaxLinesByDirection(moves, HORIZONTAL_VECTOR));
        lines.addAll(getMaxLinesByDirection(moves, MAIN_DIAGONAL_VECTOR));
        lines.addAll(getMaxLinesByDirection(moves, SECOND_DIAGONAL_VECTOR));
        lines = sortLines(lines);
        ArrayList<int[]> line = null;
        for (int i = 0; i < lines.size(); i++) {
            if (line == null || (line != null && lines.get(i) != null && line.size() < lines.get(i).size())) {
                line = lines.get(i);
            }
        }

        return line;
    }

    private static int[] getBestMove(ArrayList<int[]> moves) {
        int x, y;
        var interMove = getIntermediateMove(moves);
        if (interMove != null) {
            return interMove;
        }
        var line = getBestLine(moves);
        y = line.getLast()[Y] + line.getLast()[DY];
        x = line.getLast()[X] + line.getLast()[DX];
        if (!isEmptyCell(y, x)) {
            y = line.getFirst()[Y] - line.getFirst()[DY];
            x = line.getFirst()[X] - line.getFirst()[DX];
        }

        return new int[]{y, x};
    }

    private static void aiStep() {
        int x, y;
        if (aiMoves.isEmpty()) {
            x = 0;
            y = 0;
            if (!isEmptyCell(y, x)) {
                y = ROWS - 1;
                x = COLUMNS - 1;
            }
        } else {
            int[] move;
            var playerLine = getBestLine(playerMoves);
            var aiLine = getBestLine(aiMoves);
            var playerInterMove = getIntermediateMove(playerMoves);
            var aiInterMove = getIntermediateMove(aiMoves);

            if(aiInterMove != null || aiLine.size() == WINLINELENGTH - 1)
                move = getBestMove(aiMoves);
            else if(playerInterMove != null || playerLine.size() == WINLINELENGTH - 1)
                move = getBestMove(playerLine);
            else
                move = getBestMove(aiMoves);

            y = move[Y];
            x = move[X];
        }

        setSym(y, x, AI_DOT);
        aiMoves.add(new int[]{y, x});
    }

    private static boolean isFieldFull() {
        return playerMoves.size() + aiMoves.size() >= COLUMNS * ROWS;
    }

    private static boolean isDeltaValid(int dx, int dy) {
        return dy * dy <= 1 & dx * dx <= 1 && !(dx == 0 && dy == 0);
    }

    private static boolean isCellValid(int y, int x) {
        return !(x < 0 || y < 0 || x > COLUMNS - 1 || y > ROWS - 1);
    }

    private static boolean isGoodCell(int y, int x, char sym) {
        return isCellValid(y, x) && (field[y][x] == sym || field[y][x] == EMPTY_DOT);
    }

    private static boolean isEmptyCell(int y, int x) {
        return isCellValid(y, x) && field[y][x] == EMPTY_DOT;
    }

    private static boolean containMove(ArrayList<int[]> sortedMoves, int y, int x) {
        for (int[] move : sortedMoves) {
            if (move[X] == x && move[Y] == y)
                return true;
        }
        return false;
    }

    private static ArrayList<ArrayList<int[]>> sortLines(ArrayList<ArrayList<int[]>> lines) {
        for (int i = 0; i < lines.size() - 1; i++) {
            if (isEqualList(lines.get(i), lines.get(i + 1))) {
                lines.remove(lines.get(i + 1));
                i--;
                continue;
            }
            if (lines.get(i + 1).size() < lines.get(i).size()) {
                var swap = lines.get(i);
                lines.set(i, lines.get(i + 1));
                lines.set(i + 1, swap);
            }
        }

        return lines;
    }

    private static ArrayList<int[]> sortMoves(ArrayList<int[]> moves, boolean sortByY) {
        int priorityIndex = sortByY ? 0 : 1;
        int index = !sortByY ? 0 : 1;
        moves = new ArrayList<>(moves);

        for (int i = 0; i < moves.size() - 1; i++) {
            for (int j = 0; j < moves.size() - i - 1; j++) {
                if (moves.get(j + 1)[priorityIndex] < moves.get(j)[priorityIndex]
                        || moves.get(j + 1)[priorityIndex] == moves.get(j)[priorityIndex] && moves.get(j + 1)[index] < moves.get(j)[index]) {
                    int[] swap = moves.get(j);
                    moves.set(j, moves.get(j + 1));
                    moves.set(j + 1, swap);
                }
            }
        }
        return moves;
    }

    private static boolean checkWinPossibility(int[] move, int dy, int dx) {
        return isCellValid(move[Y] + (WINLINELENGTH - 1) * dy, move[X] + (WINLINELENGTH - 1) * dx);
    }

    private static boolean checkWinLineByDirection(ArrayList<int[]> moves, int[] vector) {
        int dx = vector[X];
        int dy = vector[Y];
        if (!isDeltaValid(dx, dy))
            return false;

        var sortedMoves = sortMoves(moves, false);

        for (int i = 0; i < sortedMoves.size(); i++) {
            int lineLength = 1;
            var move = sortedMoves.get(i);
            for (int j = 1; isCellValid(move[Y] + j * dy, move[X] + j * dx); j++) {
                if (!containMove(sortedMoves, move[Y] + j * dy, move[X] + j * dx)) {
                    break;
                }
                lineLength++;
                if (lineLength == WINLINELENGTH)
                    return true;
            }
        }
        return false;
    }

    private static boolean checkWin(ArrayList<int[]> moves) {
        return checkWinLineByDirection(moves, VERTICAL_VECTOR)
                || checkWinLineByDirection(moves, HORIZONTAL_VECTOR)
                || checkWinLineByDirection(moves, MAIN_DIAGONAL_VECTOR)
                || checkWinLineByDirection(moves, SECOND_DIAGONAL_VECTOR);
    }

    public static void main(String[] args) {
        inputGameParams();
        initField();
        printField();

        while (true) {
            playerStep();
            printField();
            if (checkWin(playerMoves)) {
                checkWin(playerMoves);
                System.out.println("Player WIN!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("DRAW");
                break;
            }

            aiStep();
            printField();
            if (checkWin(aiMoves)) {
                System.out.println("Win SkyNet!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("DRAW!");
                break;
            }
        }
    }
}
