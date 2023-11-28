
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class cross {


    // 3. Определяем размеры массива
    static final int SIZE_X = 5;
    static final int SIZE_Y = 5;

    static final int MIN_WIN_SCORE = 3;


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
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }

    // 5. Выводим на массив на печать
    private static void printField() {
        //6. украшаем картинку
        System.out.println("-------");
        for (int i = 0; i < SIZE_Y; i++) {
            System.out.print("|");
            for (int j = 0; j < SIZE_X; j++) {
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
        int x;
        int y;
        do {
            System.out.println("Введите координаты: X Y (1-3)");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(y, x));
        setSym(y, x, PLAYER_DOT);

    }

    // 13. Ход ПК
    private static void aiStep() {

        Map<String, Boolean> h_map = new HashMap<String, Boolean>();
        Map<String, Boolean> v_map = new HashMap<String, Boolean>();
        Map<String, Boolean> l_map = new HashMap<String, Boolean>();
        Map<String, Boolean> r_map = new HashMap<String, Boolean>();

        for (int y = 0; y < SIZE_Y; y++) {
            for (int x = 0; x < SIZE_X; x++) {
                String coordinate = y + "," + x;
                Boolean h_value = horizontalSearch(y, x, AI_DOT, MIN_WIN_SCORE - 1) || horizontalSearch(y, x, PLAYER_DOT, MIN_WIN_SCORE - 1);
                Boolean v_value = verticalSearch(y, x, AI_DOT, MIN_WIN_SCORE - 1) || verticalSearch(y, x, PLAYER_DOT, MIN_WIN_SCORE - 1);
                Boolean l_value = leftToRightDSearch(y, x, AI_DOT, MIN_WIN_SCORE - 1) || leftToRightDSearch(y, x, PLAYER_DOT, MIN_WIN_SCORE - 1);
                Boolean r_value = rightToLeftDSearch(y, x, AI_DOT, MIN_WIN_SCORE - 1) || rightToLeftDSearch(y, x, PLAYER_DOT, MIN_WIN_SCORE - 1);

                h_map.put(coordinate, h_value);
                v_map.put(coordinate, v_value);
                l_map.put(coordinate, l_value);
                r_map.put(coordinate, r_value);
            }
        }

        // значение 100 - дефолтное
        AtomicInteger x = new AtomicInteger(-1);
        AtomicInteger y = new AtomicInteger(-1);

        if (h_map.containsValue(true)) {
            h_map.entrySet().stream().filter(e -> e.getValue() == true).toList().forEach(e -> {
                int e_y = Integer.parseInt(e.getKey().split(",")[0]);
                int e_x = Integer.parseInt(e.getKey().split(",")[1]) + (MIN_WIN_SCORE - 1);
                if (isCellValid(e_y, e_x)) {
                    y.set(e_y);
                    x.set(e_x);
                }
            });
        }

        if (v_map.containsValue(true)) {
            v_map.entrySet().stream().filter(e -> e.getValue() == true).toList().forEach(e -> {
                int e_y = Integer.parseInt(e.getKey().split(",")[0]) + (MIN_WIN_SCORE - 1);
                int e_x = Integer.parseInt(e.getKey().split(",")[1]);
                if (isCellValid(e_y, e_x)) {
                    y.set(e_y);
                    x.set(e_x);
                }
            });
        }

        if (l_map.containsValue(true)) {
            l_map.entrySet().stream().filter(e -> e.getValue() == true).toList().forEach(e -> {
                int e_y = Integer.parseInt(e.getKey().split(",")[0]) + (MIN_WIN_SCORE - 1);
                int e_x = Integer.parseInt(e.getKey().split(",")[1]) + (MIN_WIN_SCORE - 1);
                if (isCellValid(e_y, e_x)) {
                    y.set(e_y);
                    x.set(e_x);
                }
            });
        }

        if (r_map.containsValue(true)) {
            r_map.entrySet().stream().filter(e -> e.getValue() == true).toList().forEach(e -> {
                int e_y = Integer.parseInt(e.getKey().split(",")[0]) - (MIN_WIN_SCORE - 1);
                int e_x = Integer.parseInt(e.getKey().split(",")[1]) + (MIN_WIN_SCORE - 1);
                System.out.println("r" + e_y + " " + e_x);
                if (isCellValid(e_y, e_x)) {
                    y.set(e_y);
                    x.set(e_x);
                }
            });
        }

        if (x.get() == -1 && y.get() == -1) {
            do {
                x.set(rand.nextInt(SIZE_X));
                y.set(rand.nextInt(SIZE_Y));
            } while (!isCellValid(y.get(), x.get()));
        }
        System.out.println("Ход пк: х " + (x.get() + 1) + " y " + (y.get() + 1));
        setSym(y.get(), x.get(), AI_DOT);
    }

    // 14. Проверка победы
    private static boolean checkWin(char sym) {

        ArrayList<Boolean> h_list = new ArrayList<Boolean>();
        ArrayList<Boolean> v_list = new ArrayList<Boolean>();
        ArrayList<Boolean> l_list = new ArrayList<Boolean>();
        ArrayList<Boolean> r_list = new ArrayList<Boolean>();

        for (int y = 0; y < SIZE_Y; y++) {
            for (int x = 0; x < SIZE_X; x++) {
                h_list.add(horizontalSearch(y, x, sym, MIN_WIN_SCORE));
                v_list.add(verticalSearch(y, x, sym, MIN_WIN_SCORE));
                l_list.add(leftToRightDSearch(y, x, sym, MIN_WIN_SCORE));
                r_list.add(rightToLeftDSearch(y, x, sym, MIN_WIN_SCORE));
            }
        }

        if (h_list.contains(true) || v_list.contains(true) || l_list.contains(true) || r_list.contains(true))
            return true;
        return false;
    }

    private static boolean verticalSearch(int y, int x, char sym, int score) {
        ArrayList<Boolean> list = new ArrayList<Boolean>();
        for (int i = 0; i < score; i++) {
            if (y + i < SIZE_Y) {
                list.add(field[y + i][x] == sym);
            }
        }
        if (list.size() < score) return false;
        return !list.contains(false);
    }

    private static boolean horizontalSearch(int y, int x, char sym, int score) {
        ArrayList<Boolean> list = new ArrayList<Boolean>();
        for (int i = 0; i < score; i++) {
            if (x + i < SIZE_X) {
                list.add(field[y][x + i] == sym);
            }
        }
        if (list.size() < score) return false;
        return !list.contains(false);
    }

    private static boolean leftToRightDSearch(int y, int x, char sym, int score) {
        ArrayList<Boolean> list = new ArrayList<Boolean>();
        for (int i = 0; i < score; i++) {
            if (y + i < SIZE_Y && x + i < SIZE_X) {
                list.add(field[y + i][x + i] == sym);
            }
        }
        if (list.size() < score) return false;
        return !list.contains(false);
    }

    private static boolean rightToLeftDSearch(int y, int x, char sym, int score) {
        ArrayList<Boolean> list = new ArrayList<Boolean>();
        for (int i = 0; i < score; i++) {
            if (y - i > 0 && x + i < SIZE_X) {
                list.add(field[y - i][x + i] == sym);
            }
        }
        if (list.size() < score) return false;
        return !list.contains(false);
    }

    // 16. Проверка полное ли поле? возможно ли ходить?
    private static boolean isFieldFull() {
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                if (field[i][j] == EMPTY_DOT) {
                    return false;
                }
            }
        }
        return true;
    }

    // 10. Проверяем возможен ли ход
    private static boolean isCellValid(int y, int x) {
        // если вываливаемся за пределы возвращаем false
        if (x < 0 || y < 0 || x > SIZE_X - 1 || y > SIZE_Y - 1) {
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
            if (checkWin(PLAYER_DOT)) {
                System.out.println("Player WIN!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("DRAW");
                break;
            }

            aiStep();
            printField();
            if (checkWin(AI_DOT)) {
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
