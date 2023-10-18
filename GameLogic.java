import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class GameLogic {
    public void makePlayerMove(int coordX, int coordY, Board board) {
        board.boardRepr[coordY][coordX] = Enums.Token.PLAYERTOKEN.getToken();
    }

    public void makeAIMove(Board board) {
        Random random = new Random();
        int coordX = 0;
        int coordY = 0;

        System.out.println("Компуктер думает...");

        do {
            coordX = random.nextInt(board.sizeX - 1) + 1;
            coordY = random.nextInt(board.sizeY - 1) + 1;
        } while (!board.isCellClear(coordX, coordY));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Компуктер ходит!");
        board.boardRepr[coordY][coordX] = Enums.Token.AITOKEN.getToken();
    }

    private int getMaxPossibleScoreOneDirection(int coordX, int coordY, int xMult, int yMult, Enums.Token currToken, int currScore, Board board) {
        if (coordX < 0 || coordX >= board.sizeX || coordY < 0 || coordY >= board.sizeY || currToken.getToken() != board.boardRepr[coordY][coordX])
            return currScore;

        currScore += 1;
        currScore = getMaxPossibleScoreOneDirection(coordX + xMult, coordY + yMult, xMult, yMult, currToken, currScore, board);

        return currScore;
    }

    public boolean isWinner(int coordX, int coordY, Enums.Token currToken, int targetScore, Board board) {
        int scoreVertical = getMaxPossibleScoreOneDirection(coordX, coordY, 0, -1, currToken, 0, board) + getMaxPossibleScoreOneDirection(coordX, coordY, 0, 1, currToken, 0, board) - 1;
        int scoreHorizontal = getMaxPossibleScoreOneDirection(coordX, coordY, -1, 0, currToken, 0, board) + getMaxPossibleScoreOneDirection(coordX, coordY, 1, 0, currToken, 0, board) - 1;
        int scoreDiagonal13Radian = getMaxPossibleScoreOneDirection(coordX, coordY, 1, -1, currToken, 0, board) + getMaxPossibleScoreOneDirection(coordX, coordY, -1, 1, currToken, 0, board) - 1;
        int scoreDiagonal24Radian = getMaxPossibleScoreOneDirection(coordX, coordY, -1, -1, currToken, 0, board) + getMaxPossibleScoreOneDirection(coordX, coordY, 1, 1, currToken, 0, board) - 1;

        int maxScore = Collections.max(Arrays.asList(
                scoreVertical,
                scoreHorizontal,
                scoreDiagonal13Radian,
                scoreDiagonal24Radian
        ));

        return maxScore >= targetScore;
    }
}
