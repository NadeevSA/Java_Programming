
import java.util.Random;
import java.util.Scanner;

public class XOX {

    // 3. Определяем размеры массива
    static final int SIZE_X = 4;
    static final int SIZE_Y = 5;
    static final int SIZE_W = 3;

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
        checkPlayerWin();
    }
    private static void checkPlayerWin() {
        for (int i = 0; i < SIZE_X; i++) {          // ползём по всему полю
            for (int j = 0; j < SIZE_Y; j++) {
                if (checkPlayerLine(i, j, 1, 0)) {return;}  // проверим линию по х
                if (checkPlayerLine(i, j, 1, 1)) {return;}  // проверим по диагонали х у
                if (checkPlayerLine(i, j, 0, 1)) {return;}  // проверим линию по у
                if (checkPlayerLine(i, j, 1, -1)) {return;} // проверим по диагонали х -у
            }
        }
        int x;
        int y;
        do{
            x = rand.nextInt(SIZE_X);
            y = rand.nextInt(SIZE_Y);
        } while(!isCellValid(y,x));
        setSym(y, x, AI_DOT);
        return;
    }

    private static boolean checkPlayerLine(int x, int y, int vx, int vy) {
        final int last_x = x + (SIZE_W - 1) * vx;
        final int last_y = y + (SIZE_W - 1) * vy;

        if (!(last_x<0 || last_x >=SIZE_X || last_y<0 || last_y>=SIZE_Y)) {

            int sum = 0;
            for (int n = 0; n < SIZE_W; n++) {
                System.out.println(n + " " + field[y + n * vy][x + n * vx]);
                if (field[y + n * vy][x + n * vx] == PLAYER_DOT) {
                    sum++; //return false;
                }
            }
            if (sum == SIZE_W-1) {
                setSym(last_y, last_x, AI_DOT);
                return true;
            }
            else return false;
        }
        else {
            return false;
        }
    }

    // 14. Проверка победы
    private static boolean checkWin(char sym) {
        for (int i = 0; i < SIZE_X; i++) {          // ползём по всему полю
            for (int j = 0; j < SIZE_Y; j++) {
                if (checkLine(i, j, 1, 0, sym)) {return true;}  // проверим линию по х
                if (checkLine(i, j, 1, 1, sym)) {return true;}  // проверим по диагонали х у
                if (checkLine(i, j, 0, 1, sym)) {return true;}  // проверим линию по у
                if (checkLine(i, j, 1, -1, sym)) {return true;} // проверим по диагонали х -у
            }
        }
        return false;
    }

    private static boolean checkLine(int x, int y, int vx, int vy, char sym) {
        final int last_x = x + (SIZE_W - 1) * vx;
        final int last_y = y + (SIZE_W - 1) * vy;

        if (!(last_x<0 || last_x >=SIZE_X || last_y<0 || last_y>=SIZE_Y)) {
            /*System.out.println("-----");
            System.out.println("[" + x + "; " + y + "] | [" + last_x + "; " + last_y + "]");
            System.out.println("-----");*/
            int sum = 0;
            for (int n = 0; n < SIZE_W; n++) {
                //System.out.println(n + " " + field[y + n * vy][x + n * vx]);
                if (field[y + n * vy][x + n * vx] == sym) {
                    sum++; //return false;
                }
            }
            /*
            System.out.println("-----");
            System.out.println(sum);
            System.out.println("-----");
            */
            if (sum == SIZE_W) return true;
            else return false;
        }
        else {
            return false;
        }
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
        if (x<0 || x >=SIZE_X || y<0 || y>=SIZE_Y) return false;
        else if (!(field[y][x]==EMPTY_DOT)) return false;

        return true;
    }

    public static void main(String[] args) {
        // 1 - 1 иницируем и выводим на печать
        initField();
        printField();
        // 1 - 1 иницируем и выводим на печать

        // 15 Основной ход программы
        do {
            playerStep();
            System.out.println("Ваш ход на поле");
            printField();
            if (checkWin(PLAYER_DOT)) {
                System.out.println("Вы выиграли");
                break;
            } else if (isFieldFull()) break;
            aiStep();
            System.out.println("Ход Компа на поле");
            printField();
            if (checkWin(AI_DOT)) {
                System.out.println("Выиграли Комп");
                break;
            } else if (isFieldFull()) break;
        } while (true);
        System.out.println("!Конец игры!");

    }
}
