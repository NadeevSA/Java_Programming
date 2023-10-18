import Enums.Token;
import Enums.TokenName;
import Utils.LineDecorator;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private Board board = null;
    private final Scanner scanner = new Scanner(System.in);
    private final GameLogic _controller = new GameLogic();
    private final AILogic _ai = new AILogic();

    public void play() {
        int boardSizeX = 0;
        int boardSizeY = 0;
        int winStreak = 0;
        int minimaxDepth = 0;
        int playerCoordX = 0;
        int playerCoordY = 0;
        int aiCoordX = 0;
        int aiCoordY = 0;
        int currTurn = 0;
        TokenName token;

        System.out.println("Введите размерность игрового поля");

        do {
            System.out.print("Длина по X: ");
            boardSizeX = scanner.nextInt();

            System.out.print("Длина по Y: ");
            boardSizeY = scanner.nextInt();

            if (boardSizeX <= 1 || boardSizeY <= 1)
                System.out.println("Длина каждой стороны поля должна быть больше 1");
        } while (boardSizeX <= 1 || boardSizeY <= 1);

        board = new Board(boardSizeX, boardSizeY);

        int maxWinStreak = Math.min(boardSizeX, boardSizeY);
        System.out.println(String.format("Введите выйгрышную серию (от 2 до %d)", maxWinStreak));

        do {
            System.out.print("Выйгрышная серия: ");
            winStreak = scanner.nextInt();

            if (winStreak < 2 || winStreak > maxWinStreak)
                System.out.println(String.format("Серия должна быть от 2 до %d", maxWinStreak));
        } while (winStreak < 2 || winStreak > maxWinStreak);

        System.out.println("Введите сложность ИИ (от 1 до 5)");
        System.out.println("Внимание! Время хода компьютера напрямую влияет от выбранной слоности и размера игрового поля");

        do {
            System.out.print("Сложность: ");
            minimaxDepth = scanner.nextInt();

            if (minimaxDepth < 1 || minimaxDepth > 5)
                System.out.println("Сложность должна быть от 1 до 5");
        } while (minimaxDepth < 1 || minimaxDepth > 5);

        minimaxDepth *= 2;

        while (true) {
            currTurn++;
            token = TokenName.PLAYERNAME;
            showStats(currTurn, boardSizeX, token);
            board.showBoard();

            do {
                System.out.println();
                System.out.print("Введите координату X: ");
                playerCoordX = scanner.nextInt() - 1;

                System.out.print("Введите координату Y: ");
                playerCoordY = scanner.nextInt() - 1;

                if (playerCoordX >= board.sizeX || playerCoordX < 0 || playerCoordY >= board.sizeY || playerCoordY < 0 || !board.isCellClear(playerCoordX, playerCoordY))
                    System.out.println("Координаты не соответствуют текущему игровому полю");
            } while (playerCoordX >= board.sizeX || playerCoordX < 0 || playerCoordY >= board.sizeY || playerCoordY < 0 || !board.isCellClear(playerCoordX, playerCoordY));

            _controller.makePlayerMove(playerCoordX, playerCoordY, board);

            if (_controller.isWinner(playerCoordX, playerCoordY, Token.PLAYERTOKEN, winStreak, board)) {
                endGame(TokenName.PLAYERNAME, boardSizeX);
                board.showBoard();
                break;
            } else if (board.getEmptyCells().isEmpty()) {
                endGame(TokenName.TIE, boardSizeX);
                board.showBoard();
                break;
            }

            board.showBoard();
            currTurn++;
            token = TokenName.AINAME;
            showStats(currTurn, boardSizeX, token);

            if (currTurn == 2) {
                Random random = new Random();

                do {
                    aiCoordX = random.nextInt(board.sizeX);
                    aiCoordY = random.nextInt(board.sizeY);
                } while (!board.isCellClear(aiCoordX, aiCoordY));
            }

            PossibleMove possibleMove = _ai.getPossibleMove(board, Token.AITOKEN, aiCoordX, aiCoordY, playerCoordX, playerCoordY, winStreak, minimaxDepth);
            _ai.makeAIMove(possibleMove.getCoordX(), possibleMove.getCoordY(), board);

            if (_controller.isWinner(possibleMove.getCoordX(), possibleMove.getCoordY(), Token.AITOKEN, winStreak, board)) {
                endGame(TokenName.AINAME, boardSizeX);
                board.showBoard();
                break;
            } else if (board.getEmptyCells().isEmpty()) {
                endGame(TokenName.TIE, boardSizeX);
                board.showBoard();
                break;
            }
        }
    }

    private void showStats(int currTurn, int sizeX, TokenName token) {
        System.out.println();
        LineDecorator.drawLine(sizeX);
        System.out.println();
        System.out.println(String.format("Ход №%d. Ходит %s", currTurn, token.getToken()));
        LineDecorator.drawLine(sizeX);
        System.out.println();
    }

    private void endGame(TokenName token, int sizeX) {
        System.out.println();
        LineDecorator.drawLine(sizeX);
        System.out.println();
        System.out.println(String.format("(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧ Победил %s!!! ٩(｡•́‿•̀｡)۶", token.getToken()));
        LineDecorator.drawLine(sizeX);
        System.out.println();
    }
}
