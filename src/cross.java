import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class cross {

    // 3. Определяем размеры массива
    static final int SIZE_X = 5;
    static final int SIZE_Y = 5;

    // Количества ячеек для победы
    static final int WIN_LINE = 4;

    // 1. Создаем двумерный массив
    static char[][] field = new char[SIZE_Y][SIZE_X];

    static int[][] heat_map_player = new int[SIZE_Y][SIZE_X];
    static int[][] heat_map_ai = new int[SIZE_Y][SIZE_X];

    static int stepX = 0;
    static int stepY = 0;

    // 2. Обозначаем кто будет ходить какими фишками
    static final char PLAYER_DOT = 'X';
    static final char AI_DOT = '0';
    static final char EMPTY_DOT = '.';

    // 8. Создаем сканер
    static Scanner scanner = new Scanner(System.in);
    // 12. Создаем рандом

    // 4. Заполняем на массив
    private static void initField() {
        for(int i = 0; i < SIZE_Y; i++) {
            for(int j = 0; j < SIZE_X; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }

    // 5. Выводим на массив на печать
    private static void printField() {
        //6. украшаем картинку
        System.out.println("-------");
        for(int i = 0; i < SIZE_Y; i++) {
            System.out.print("|");
            for(int j = 0; j < SIZE_X; j++) {
                System.out.print(field[i][j] + "|");
            }
            System.out.println();
        }
        //6. украшаем картинку
        System.out.println("-------");
    }

    // 7. Метод который устанавливает символ
    private static void setSym(int y, int x, char sym) {
        field[y][x] = sym;
    }

    // 9. Ход игрока
    private static void playerStep() {
        // 11. с проверкой
        do {
            System.out.println("Введите координаты: X Y (" + SIZE_X + "-" + SIZE_Y + ")");
            stepX = scanner.nextInt() - 1;
            stepY = scanner.nextInt() - 1;
        } while (!isCellValid(stepY,stepX));
        setSym(stepY, stepX, PLAYER_DOT);

    }

    // 13. Ход ПК
    private static void aiStep() {
        var high_cells_nearby_player = Integer.MIN_VALUE;
        var ip = 0;
        var jp = 0;
        for (int i = 0; i < SIZE_Y; ++i) {
            for (int j = 0; j < SIZE_X; ++j) {
                if (isCellValid(i,j) && high_cells_nearby_player < heat_map_player[i][j]) {
                    high_cells_nearby_player = heat_map_player[i][j];
                    ip = i;
                    jp = j;
                }
            }
        }

        var high_cells_nearby_ai = Integer.MIN_VALUE;
        var ia = 0;
        var ja = 0;
        for (int i = 0; i < SIZE_Y; ++i) {
            for (int j = 0; j < SIZE_X; ++j) {
                if (isCellValid(i,j) && high_cells_nearby_ai < heat_map_ai[i][j]) {
                    high_cells_nearby_ai = heat_map_ai[i][j];
                    ia = i;
                    ja = j;
                }
            }
        }

        if (high_cells_nearby_player > high_cells_nearby_ai){
            setSym(ip, jp, AI_DOT);
            stepX = jp;
            stepY = ip;
        }
        else{
            setSym(ia, ja, AI_DOT);
            stepX = ja;
            stepY = ia;
        }
    }

    private static boolean checkVertical(char sym, int[][] heat_map) {
        // наверх
        var count = 1;
        var y = stepY;

        // вниз
        for (int i = stepY - 1; i >= 0; --i) {
            if (field[i][stepX] == sym) {
                count++;
            } else {
                y = i;
                break;
            }
        }

        // вниз
        var y1 = stepY;
        for (int i = stepY + 1; i < SIZE_Y; ++i) {
            if (field[i][stepX] == sym) {
                count++;
            } else {
                y1 = i;
                break;
            }
        }

        heat_map[y][stepX] = Math.max(heat_map[y][stepX], count);
        heat_map[y1][stepX] = Math.max(heat_map[y1][stepX], count);

        return WIN_LINE == count;
    }

    private static boolean checkHorizontal(char sym, int[][] heat_map) {
        var count = 1;
        var x = stepX;

        // влево
        for (int i = stepX + 1; i < SIZE_X; ++i) {
            if (field[stepY][i] == sym) {
                count++;
            } else {
                x = i;
                break;
            }
        }

        // вправо
        var x1 = stepX;
        for (int i = stepX - 1; i >= 0; --i) {
            if (field[stepY][i] == sym) {
                count++;
            } else {
                x1 = i;
                break;
            }
        }

        heat_map[stepY][x] = Math.max(heat_map[stepY][x], count);
        heat_map[stepY][x1] = Math.max(heat_map[stepY][x1], count);

        return WIN_LINE == count;
    }

    private static boolean checkMainDiagonal(char sym, int[][] heat_map) {
        var count = 1;
        var x = stepX;
        var y = stepY;

        for (int i = stepX - 1, j = stepY - 1; i >= 0 && j >= 0; --i, --j) {
            if (field[j][i] == sym) {
                count++;
            } else {
                x = i;
                y = j;
                break;
            }
            if (i == 0 || j == 0) {
                x = i;
                y = j;
                break;
            }
        }

        var x1 = stepX;
        var y1 = stepY;
        for (int i = stepX + 1, j = stepY + 1; i < SIZE_X && j < SIZE_Y; ++i, ++j) {
            if (field[j][i] == sym) {
                count++;
            }
            else {
                x1 = i;
                y1 = j;
                break;
            }
            if (i == SIZE_X - 1 || j == SIZE_Y - 1){
                x1 = i;
                y1 = j;
                break;
            }
        }

        heat_map[y][x] = Math.max(heat_map[y][x], count);
        heat_map[y1][y1] = Math.max(heat_map[y1][x1], count);

        return WIN_LINE == count;
    }

    private static boolean checkMinorDiagonal(char sym, int[][] heat_map) {
        var count = 1;
        var x = stepX;
        var y = stepY;
        for (int i = stepX - 1, j = stepY + 1; i >= 0 && j < SIZE_Y; --i, ++j) {
            if (field[j][i] == sym) {
                count++;
            }
            else {
                x = i;
                y = j;
                break;
            }
            if (i == 0 || j == SIZE_Y - 1) {
                x = i;
                y = j;
                break;
            }
        }

        var x1 = stepX;
        var y1 = stepY;
        for (int i = stepX + 1, j = stepY - 1; i < SIZE_X && j >= 0; ++i, --j) {
            if (field[j][i] == sym) {
                count++;
            }
            else {
                x1 = i;
                y1 = j;
                break;
            }
            if (i == SIZE_X - 1 || j == 0) {
                x1 = i;
                y1 = j;
                break;
            }
        }

        heat_map[y][x] = Math.max(heat_map[y][x], count);
        heat_map[y1][x1] = Math.max(heat_map[y1][x1], count);

        return WIN_LINE == count;
    }

    // 14. Проверка победы
    private static boolean checkWin(char sym, int[][] heat_map) {
        var checkHorizontal = checkHorizontal(sym, heat_map);
        var checkVertical = checkVertical(sym, heat_map);
        var checkMainDiagonal = checkMainDiagonal(sym, heat_map);
        var checkMinorDiagonal = checkMinorDiagonal(sym, heat_map);

        heat_map[stepY][stepX] = -1;
        return checkHorizontal || checkMainDiagonal || checkVertical || checkMinorDiagonal;
    }

    // 16. Проверка полное ли поле? возможно ли ходить?
    private static boolean isFieldFull() {
        for (int i = 0; i < SIZE_Y; i++) {
            for(int j = 0; j < SIZE_X; j++) {
                if(field[i][j] == EMPTY_DOT) {
                    return false;
                }
            }
        }
        return true;
    }

    // 10. Проверяем возможен ли ход
    private static boolean isCellValid(int y, int x) {
        // если вываливаемся за пределы возвращаем false
        if(x < 0 || y < 0 || x > SIZE_X -1 || y > SIZE_Y - 1) {
            return false;
        }
        // если не путое поле тоже false
        return (field[y][x] == EMPTY_DOT);
    }

    public static void main(String[] args) {
        // 1 - 1 иницируем и выводим на печать
        initField();
        printField();
        // 1 - 1 иницируем и выводим на печать

        // 15 Основной ход программы

        while (true) {
            playerStep();
            printField();
            if(checkWin(PLAYER_DOT, heat_map_player)) {
                System.out.println("Player WIN!");
                break;
            }
            if(isFieldFull()) {
                System.out.println("DRAW");
                break;
            }

            aiStep();
            printField();
            if(checkWin(AI_DOT, heat_map_ai)) {
                System.out.println("Win SkyNet!");
                break;
            }
            if(isFieldFull()) {
                System.out.println("DRAW!");
                break;
            }
        }
    }
}
