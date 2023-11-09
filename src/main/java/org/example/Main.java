package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static final char PLAYER_DOT = 'X';
    static final char AI_DOT = 'O';
    static final char EMPTY_DOT = '.';

    static int SIZE_X;
    static int SIZE_Y;
    static char[][] field;
    static int WIN_COMBINATION;

    // Computer win steps X coordinate
    static int[] ai_win_steps_x;

    // Computer win steps Y coordinate
    static int[] ai_win_steps_y;

    static Scanner scanner = new Scanner(System.in);
    static final Random rand = new Random();

    /**
     * Initialize game fields
     */
    private static void initFields() {
        field = new char[SIZE_X][SIZE_Y];

        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }

    /**
     * Visualize fields
     */
    private static void printField() {
        System.out.println("-----------");
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                System.out.print(field[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println("-----------");
    }

    /**
     * Check field is empty
     */
    private static boolean isValidCell(int x, int y) {
        if (x < 0 || y < 0 || x > SIZE_X - 1 || y > SIZE_Y - 1) {
            return false;
        }

        return field[x][y] == EMPTY_DOT;
    }

    /**
     * Fill field with symbol
     */
    private static void setSym(int x, int y, char sym) {
        field[x][y] = sym;
    }

    /**
     * Step for player
     */
    private static void playerStep() {
        int x;
        int y;
        do {
            System.out.print("X=");
            x = scanner.nextInt() - 1;
            System.out.print("Y=");
            y = scanner.nextInt() - 1;
        } while (!isValidCell(x, y));

        setSym(x, y, PLAYER_DOT);
    }

    /**
     * Step for computer
     */
    private static void aiStep() {
        if (aiBlockingPlayerStep()) {
            return;
        }

        if (aiWinStep()) {
            return;
        }

        aiRandomStep();
    }

    /**
     * Random step for computer
     */
    private static void aiRandomStep() {
        int x;
        int y;

        do {
            x = rand.nextInt(SIZE_X + 1);
            y = rand.nextInt(SIZE_Y + 1);
        } while (!isValidCell(x, y));

        setSym(x, y, AI_DOT);
    }

    /**
     * Win step by target for computer
     */
    private static boolean aiWinStep() {
        if (!isValidAiWinSteps() && !generateAiWinSteps()) {
            return false;
        }

        int step;
        do {
            step = rand.nextInt(WIN_COMBINATION);
        } while (field[ai_win_steps_x[step]][ai_win_steps_y[step]] != EMPTY_DOT);

        setSym(ai_win_steps_x[step], ai_win_steps_y[step], AI_DOT);

        return true;
    }

    /**
     * Check computer target win steps is valid
     */
    private static boolean isValidAiWinSteps() {
        if (ai_win_steps_x == null || ai_win_steps_y == null ||
                ai_win_steps_x.length != WIN_COMBINATION || ai_win_steps_y.length != WIN_COMBINATION) {
            return false;
        }

        for (int i = 0; i < ai_win_steps_x.length; i++) {
            if (field[ai_win_steps_x[i]][ai_win_steps_y[i]] == PLAYER_DOT) {
                return false;
            }
        }

        return true;
    }

    /**
     * Generate target win steps for computer
     */
    private static boolean generateAiWinSteps() {
        for (int start_x = 0; start_x + WIN_COMBINATION <= SIZE_X; start_x++) {
            for (int start_y = 0; start_y + WIN_COMBINATION <= SIZE_Y; start_y++) {
                if (generateWinTargetWidth(start_x, start_y) || generateWinTargetHeight(start_x, start_y)
                        || generateWinTargetDiagPositive(start_x, start_y) || generateWinTargetDiagNegative(start_x, start_y)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Generate target win steps for computer in row
     */
    private static boolean generateWinTargetWidth(int start_x, int start_y) {
        for (int i = start_x; i < WIN_COMBINATION + start_x; i++) {
            int[] steps_x = new int[WIN_COMBINATION];
            int[] steps_y = new int[WIN_COMBINATION];
            int step = 0;

            for (int j = start_y; j < WIN_COMBINATION + start_y; j++, step++) {
                if (field[i][j] == PLAYER_DOT) {
                    break;
                }

                steps_x[step] = i;
                steps_y[step] = j;
            }

            if (step == WIN_COMBINATION) {
                ai_win_steps_x = steps_x;
                ai_win_steps_y = steps_y;
                return true;
            }
        }

        return false;
    }

    /**
     * Generate target win steps for computer in diagonal positive
     */
    private static boolean generateWinTargetDiagPositive(int start_x, int start_y) {
        int[] steps_x = new int[WIN_COMBINATION];
        int[] steps_y = new int[WIN_COMBINATION];
        int step = 0;

        for (int i = start_x, j = start_y; (i < WIN_COMBINATION + start_x) && (j < WIN_COMBINATION + start_y); i++, j++, step++) {
            if (field[i][j] == PLAYER_DOT) {
                break;
            }

            if (step == WIN_COMBINATION) {
                ai_win_steps_x = steps_x;
                ai_win_steps_y = steps_y;
                return true;
            }
        }

        return false;
    }

    /**
     * Generate target win steps for computer in diagonal negative
     */
    private static boolean generateWinTargetDiagNegative(int start_x, int start_y) {
        int[] steps_x = new int[WIN_COMBINATION];
        int[] steps_y = new int[WIN_COMBINATION];
        int step = 0;

        for (int i = start_x + WIN_COMBINATION - 1, j = start_y; (i > 0) && (j < WIN_COMBINATION + start_y); i--, j++, step++) {
            if (field[i][j] == PLAYER_DOT) {
                break;
            }

            if (step == WIN_COMBINATION) {
                ai_win_steps_x = steps_x;
                ai_win_steps_y = steps_y;
                return true;
            }
        }

        return false;
    }

    /**
     * Generate target win steps for computer in column
     */
    private static boolean generateWinTargetHeight(int start_x, int start_y) {
        for (int i = start_x; i < WIN_COMBINATION + start_x; i++) {
            int[] steps_x = new int[WIN_COMBINATION];
            int[] steps_y = new int[WIN_COMBINATION];
            int step = 0;

            for (int j = start_y; j < WIN_COMBINATION + start_y; j++, step++) {
                if (field[j][i] == PLAYER_DOT) {
                    break;
                }

                steps_x[step] = j;
                steps_y[step] = i;
            }

            if (step == WIN_COMBINATION) {
                ai_win_steps_x = steps_x;
                ai_win_steps_y = steps_y;
                return true;
            }
        }

        return false;
    }

    /**
     * Block player next win step
     */
    private static boolean aiBlockingPlayerStep() {
        for (int start_x = 0; start_x + WIN_COMBINATION <= SIZE_X; start_x++) {
            for (int start_y = 0; start_y + WIN_COMBINATION <= SIZE_Y; start_y++) {
                if(aiBlockingPlayerWidth(start_x, start_y) || aiBlockingPlayerHeight(start_x,start_y)
                    || aiBlockingPlayerDiagPositive(start_x,start_y) || aiBlockingPlayerDiagNegative(start_x, start_y)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Block player next win step for row
     */
    private static boolean aiBlockingPlayerWidth(int start_x, int start_y) {
        for (int i = start_x; i < WIN_COMBINATION + start_x; i++) {
            int targ_x = 0;
            int targ_y = 0;
            int free_field = 0;
            int filled = 0;

            for (int j = start_y; j < WIN_COMBINATION + start_y; j++) {
                if (field[i][j] == PLAYER_DOT) {
                    filled++;
                } else if (field[i][j] == EMPTY_DOT) {
                    free_field++;
                    targ_x = i;
                    targ_y = j;
                }

                if (free_field > 1) {
                    break;
                }
            }

            if ((filled == WIN_COMBINATION - free_field) && free_field == 1) {
                setSym(targ_x, targ_y, AI_DOT);
                return true;
            }
        }

        return false;
    }

    /**
     * Block player next win step diagonal positive
     */
    private static boolean aiBlockingPlayerDiagPositive(int start_x, int start_y) {
        int targ_x = 0;
        int targ_y = 0;
        int free_field = 0;
        int filled = 0;

        for (int i = start_x, j = start_y; (i < WIN_COMBINATION + start_x) && (j < WIN_COMBINATION + start_y); i++, j++) {
            if (field[i][j] == PLAYER_DOT) {
                filled++;
            } else if (field[i][j] == EMPTY_DOT) {
                free_field++;
                targ_x = i;
                targ_y = j;
            }

            if (free_field > 1) {
                break;
            }
        }

        if ((filled == WIN_COMBINATION - free_field) && free_field == 1) {
            setSym(targ_x, targ_y, AI_DOT);
            return true;
        }

        return false;
    }


    /**
     * Block player next win step diagonal negative
     */
    private static boolean aiBlockingPlayerDiagNegative(int start_x, int start_y) {
        int targ_x = 0;
        int targ_y = 0;
        int free_field = 0;
        int filled = 0;

        for (int i = start_x + WIN_COMBINATION - 1, j = start_y; i > 0 && (j < WIN_COMBINATION + start_y); i--, j++) {
            if (field[i][j] == PLAYER_DOT) {
                filled++;
            } else if (field[i][j] == EMPTY_DOT) {
                free_field++;
                targ_x = i;
                targ_y = j;
            }

            if (free_field > 1) {
                break;
            }
        }

        if ((filled == WIN_COMBINATION - free_field) && free_field == 1) {
            setSym(targ_x, targ_y, AI_DOT);
            return true;
        }

        return false;
    }

    /**
     * Block player next win step for column
     */
    private static boolean aiBlockingPlayerHeight(int start_x, int start_y) {
        for (int i = start_x; i < WIN_COMBINATION + start_x; i++) {
            int targ_x = 0;
            int targ_y = 0;
            int free_field = 0;
            int filled = 0;

            for (int j = start_y; j < WIN_COMBINATION + start_y; j++) {
                if (field[j][i] == PLAYER_DOT) {
                    filled++;
                } else if (field[j][i] == EMPTY_DOT) {
                    free_field++;
                    targ_x = j;
                    targ_y = i;
                }

                if (free_field > 1) {
                    break;
                }
            }

            if ((filled == WIN_COMBINATION - free_field) && free_field == 1) {
                setSym(targ_x, targ_y, AI_DOT);
                return true;
            }
        }

        return false;
    }

    /**
     * Check game map have not empty fields
     */
    private static boolean checkIsFillField() {
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                if (field[i][j] == EMPTY_DOT) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Check for win by symbol
     */
    private static boolean checkWin(char currentSym) {
        for (int start_x = 0; start_x + WIN_COMBINATION <= SIZE_X; start_x++) {
            for (int start_y = 0; start_y + WIN_COMBINATION <= SIZE_Y; start_y++) {
                if (checkWinWidthHeight(start_x, start_y, currentSym) || checkWinDiag(start_x, start_y, currentSym)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Check for win by symbol in column and row
     */
    private static boolean checkWinWidthHeight(int start_x, int start_y, char currentSym) {
        for (int i = start_x; i < WIN_COMBINATION + start_x; i++) {
            int widthFilled = 0;
            int heightFilled = 0;
            
            for (int j = start_y; j < WIN_COMBINATION + start_y; j++) {
                if (field[i][j] == currentSym) {
                    widthFilled++;
                }
                
                if (field[j][i] == currentSym) {
                    heightFilled++;
                }
            }
            
            if (widthFilled == WIN_COMBINATION || heightFilled == WIN_COMBINATION) {
                return true;
            }
        }
        
        return false;
    }

    /**
     * Check for win by symbol in row negative and positive diagonal
     */
    private static boolean checkWinDiag(int start_x, int start_y, char currentSym) {
        int positive_diag_filled = 0;
        int negative_diag_filled = 0;
        
        for (int i = start_x, j = start_y; i < WIN_COMBINATION + start_x && j < WIN_COMBINATION + start_y; i++, j++) {
            if (field[i][j] == currentSym) {
                positive_diag_filled++;
            }

            if (field[WIN_COMBINATION + start_x + start_x - i - 1][j] == currentSym) {
                negative_diag_filled++;
            }
        }
        
        return positive_diag_filled == WIN_COMBINATION || negative_diag_filled == WIN_COMBINATION;
    }

    /**
     * Set game options
     */
    private static void setGameOptions() {
        do {
            System.out.print("Size X=");
            SIZE_X = scanner.nextInt();
        } while (SIZE_X < 3 || SIZE_X > 10);

        do {
            System.out.print("Size Y=");
            SIZE_Y = scanner.nextInt();
        } while (SIZE_Y < 3 || SIZE_Y > 10);

        do {
            System.out.print("Win combination=");
            WIN_COMBINATION = scanner.nextInt();
        } while (WIN_COMBINATION < 3 || WIN_COMBINATION > SIZE_X || WIN_COMBINATION > SIZE_Y);

        System.out.println(WIN_COMBINATION);
    }

    public static void main(String[] args) {
        setGameOptions();
        initFields();
        printField();
        do {
            playerStep();
            printField();

            if (checkWin(PLAYER_DOT)) {
                System.out.println("PLAYER WIN");
                return;
            }

            if (checkIsFillField()) {
                System.out.println("Winner not found");
                return;
            }

            aiStep();
            printField();
            if (checkWin(AI_DOT)) {
                System.out.println("AI WIN");
                return;
            }

            if (checkIsFillField()) {
                System.out.println("Winner not found");
                return;
            }
        } while (true);
    }
}