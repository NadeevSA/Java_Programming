import java.util.Random;
import java.util.Scanner;


class cross {

    // 3. Определяем размеры массива
    static final int SIZE_X = 6;
    static final int SIZE_Y = 6;

    static final int WIN_CONDITION = 3;

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
        var PointToStep = checkWillWin(AI_DOT);
        PointToStep = PointToStep == null ? checkWillWin(PLAYER_DOT) : PointToStep;
        PointToStep = PointToStep == null ? findTheBestStep(AI_DOT) : PointToStep;
        if (PointToStep == null) {
            do {
                PointToStep = new Pair(rand.nextInt(SIZE_X), rand.nextInt(SIZE_Y));
            } while (!isCellValid(PointToStep.getY(), PointToStep.getX()));
        }
        setSym(PointToStep.getY(), PointToStep.getX(), AI_DOT);
    }

    // 14. Проверка победы
    private static boolean checkWin(char sym) {
        for (int y = 0; y < SIZE_Y; y++)
            for (int x = 0; x < SIZE_X; x++) {
                if (checkDiagonalToUp(sym, x, y)
                    || checkDiagonalToDown(sym, x, y)
                    || checkHorizontal(sym, x, y)
                    || checkVertical(sym, x, y))
                    return true;
            }
        return false;
    }

    // Проверка, наступила ли победа по диагонали снизу вверх
    private static boolean checkDiagonalToUp(char sym, int x, int y) {
        return cellsInARow(sym,0, x, y, 1, -1) >= WIN_CONDITION;
    }


    // Проверка, наступила ли победа по диагонали снизу вверх
    private static boolean checkDiagonalToDown(char sym, int x, int y) {
        return cellsInARow(sym,0, x, y, 1, 1) >= WIN_CONDITION;
    }


    // Проверка, наступила ли победа по горизонтали
    private static boolean checkHorizontal(char sym, int x, int y) {
        return cellsInARow(sym,0, x, y, 1, 0) >= WIN_CONDITION;
    }


    // Проверка, наступила ли победа по вертикали
    private static boolean checkVertical(char sym, int x, int y) {
        return cellsInARow(sym,0, x, y, 0, 1) >= WIN_CONDITION;
    }

    // Считаем сколько клеток подряд заполнено определенным символом в заданном направлении
    private static int cellsInARow(char sym, int sum, int x, int y, int dx, int dy)
    {
        if(!(x < 0 || y < 0 || x > SIZE_X -1 || y > SIZE_Y - 1)) {
            if (field[y][x] == sym) {
                sum++;
                return cellsInARow(sym,sum,x + dx,y + dy,dx,dy);
            }
        }
        return sum;
    }

    // Проверяем, будет ли победа при установленном символе в ячейку
    // Диагональ сверху вниз
    private static int checkTopDiagWinStep(char sym, int x, int y) {
        int sum = 0;
        sum = cellsInARow(sym, sum, x + 1, y + 1, 1, 1);
        sum = cellsInARow(sym, sum, x - 1, y - 1, -1, -1);
        sum++;
        return sum;
    }

    // Проверяем, будет ли победа при установленном символе в ячейку
    // Диагональ снизу вверх
    private static int checkBotDiagWinStep(char sym, int x, int y) {
        int sum = 0;
            sum++;
            sum = cellsInARow(sym, sum, x +1 , y -1, 1, -1);
            sum = cellsInARow(sym, sum, x - 1, y+1, -1, 1);
        return sum;
    }

    // Проверяем, будет ли победа при установленном символе в ячейку
    // Горизонталь
    private static int checkHorizotantalWinStep(char sym, int x, int y) {
        int sum = 0;
            sum++;
            sum = cellsInARow(sym, sum, x + 1, y, 1, 0);
            sum = cellsInARow(sym, sum, x - 1, y, -1, 0);
        return sum;
    }

    // Проверяем, будет ли победа при установленном символе в ячейку
    // Вертикаль
    private static int checkVerticalWinStep(char sym, int x, int y) {
        int sum = 0;
            sum++;
            sum = cellsInARow(sym, sum, x, y -1, 0, -1);
            sum = cellsInARow(sym, sum, x, y + 1, 0, 1);
        return sum;
    }

    // Поиск оптимальной клетки для установки символа
    private static Pair checkWillWin(char sym) {
        var valuablePoint = findMaxValuePoint(sym);
        return (valuablePoint.getMaxVal() >= WIN_CONDITION
                ? new Pair(valuablePoint.getMaxI(), valuablePoint.getMaxJ())
                : null);
    }

    private static Pair findTheBestStep(char sym) {
        var valuablePoint = findMaxValuePoint(sym);
        return (valuablePoint.getMaxVal() > 0
                ? new Pair(valuablePoint.getMaxI(), valuablePoint.getMaxJ())
                : null);
    }

    private static MaxValues findMaxValuePoint(char sym) {
        int max = 0;
        int max_i = -1;
        int max_j = -1;
        for (int i=0; i < SIZE_X; i++)
            for (int j=0; j< SIZE_Y; j++) {
                if (isCellValid(j,i)) {
                    var cur_max = 0;
                    cur_max = Math.max(checkBotDiagWinStep(sym, i, j), cur_max);
                    cur_max = Math.max(checkTopDiagWinStep(sym, i, j), cur_max);
                    cur_max = Math.max(checkHorizotantalWinStep(sym, i, j), cur_max);
                    cur_max = Math.max(checkVerticalWinStep(sym, i, j), cur_max);
                    if (cur_max > max) {
                        max = cur_max;
                        max_j = j;
                        max_i = i;
                    }
                }
            }
        return new MaxValues(max_i, max_j, max);
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
        }

    }
}
