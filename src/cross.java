import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class cross {

    // 3. Определяем размеры массива
    static final int SIZE_X = 5;
    static final int SIZE_Y = 3;

    // Количества ячеек для победы
    static final int WIN_LINE = 3;

    // 1. Создаем двумерный массив
    static char[][] field = new char[SIZE_Y][SIZE_X];

    // 2. Обозначаем кто будет ходить какими фишками
    static final char PLAYER_DOT = 'X';
    static final char AI_DOT = '0';
    static final char EMPTY_DOT = '.';

    // 8. Создаем сканер
    static Scanner scanner = new Scanner(System.in);
    // 12. Создаем рандом
    static final Random rand = new Random();

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
    private static void setSym(int y, int x, char sym){
        field[y][x] = sym;
    }

    // 9. Ход игрока
    private static void playerStep() {
        // 11. с проверкой
        int x;
        int y;
        do {
            System.out.println("Введите координаты: X Y (1-3)");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(y,x));
        setSym(y, x, PLAYER_DOT);

    }

    // 13. Ход ПК
    private static void aiStep() {
        int x;
        int y;
        do{
            x = rand.nextInt(SIZE_X);
            y = rand.nextInt(SIZE_Y);
        } while(!isCellValid(y,x));
        setSym(y, x, AI_DOT);
    }

    // 14. Проверка победы
    private static boolean checkWin(char sym) {
        var pattern = Pattern.compile(sym + "{" + WIN_LINE + "}");

        return lineCheck(pattern, (Integer i, Integer j) -> field[i][j], SIZE_Y, SIZE_X) |
                lineCheck(pattern, (Integer i, Integer j) -> field[j][i], SIZE_X, SIZE_Y) |
                diagonalCheck(pattern);
    }

    @FunctionalInterface
    public interface Func2Args<T, T1, R> {
        R apply(T t, T1 t1);
    }

    private static boolean lineCheck(Pattern pattern, Func2Args<Integer, Integer, Character> func2Args, int n, int m){
        var stringBuilder = new StringBuilder(Math.max(n, m));

        for (int i = 0; i < n; ++i){
            stringBuilder.setLength(0);

            for (int j = 0; j < m; ++j){
                stringBuilder.append(func2Args.apply(i, j));
            }

            if (pattern.matcher(stringBuilder).find()) {
                return true;
            }
        }

        return false;
    }

    private static boolean diagonalCheck(Pattern pattern){
        var stringBuilder = new StringBuilder(Math.max(SIZE_X, SIZE_Y));
        var min = Math.min(SIZE_X, SIZE_Y);

        for (int i = min - 1; i >= 0; --i){
            var current_i = i;
            stringBuilder.setLength(0);

            for (int j = 0; current_i < min; ++j, ++current_i){
                stringBuilder.append(field[current_i][j]);
            }

            if (pattern.matcher(stringBuilder).find()) {
                return true;
            }
        }

        for (int i = 0; i < min; ++i){
            var current_i = i;
            stringBuilder.setLength(0);

            for (int j = 0; current_i >= 0; ++j, --current_i){
                stringBuilder.append(field[j][current_i]);
            }

            if (pattern.matcher(stringBuilder).find()) {
                return true;
            }
        }

        return false;
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
            if(checkWin(PLAYER_DOT)) {
                System.out.println("Player WIN!");
                break;
            }
            if(isFieldFull()) {
                System.out.println("DRAW");
                break;
            }

            aiStep();
            printField();
            if(checkWin(AI_DOT)) {
                System.out.println("Win SkyNet!");
                break;
            }
            if(isFieldFull()) {
                System.out.println("DRAW!");
                break;
            }
        }/**/

    }
}
