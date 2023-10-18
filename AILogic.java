import Enums.MinimaxScore;
import Enums.Token;
import Utils.CharArrCopier;

import java.util.ArrayList;

public class AILogic {
    private final GameLogic _controller = new GameLogic();

    public PossibleMove getPossibleMove(Board board, Token token, int aiCoordX, int aiCoordY, int playerCoordX, int playerCoordY, int targetScore, int depth) {
        if (depth == 0) {
            int coordX = 0;
            int coordY = 0;
            int score = 0;

            if (token == Token.PLAYERTOKEN) {
                coordX = playerCoordX;
                coordY = playerCoordY;
                score = MinimaxScore.PLAYERSCORE.getScore();
            } else if (token == Token.AITOKEN) {
                coordX = aiCoordX;
                coordY = aiCoordY;
                score = MinimaxScore.AISCORE.getScore();
            }

            PossibleMove possibleMove = new PossibleMove();
            possibleMove.setCoordX(coordX);
            possibleMove.setCoordY(coordY);
            possibleMove.setScore(score);

            return possibleMove;
        }

        Board boardCopy = new Board(board.sizeX, board.sizeY);
        boardCopy.boardRepr = CharArrCopier.getCopyOf2DArray(board.boardRepr);
        ArrayList<int[]> emptyCells = boardCopy.getEmptyCells();
        int bestMoveIndex = 0;

        if (_controller.isWinner(playerCoordX, playerCoordY, Token.PLAYERTOKEN, targetScore, boardCopy)) {
            PossibleMove possibleMove = new PossibleMove();
            possibleMove.setCoordX(playerCoordX);
            possibleMove.setCoordY(playerCoordY);
            possibleMove.setScore(MinimaxScore.PLAYERSCORE.getScore());

            return possibleMove;
        } else if (_controller.isWinner(aiCoordX, aiCoordY, Token.AITOKEN, targetScore, boardCopy)) {
            PossibleMove possibleMove = new PossibleMove();
            possibleMove.setCoordX(aiCoordX);
            possibleMove.setCoordY(aiCoordY);
            possibleMove.setScore(MinimaxScore.AISCORE.getScore());

            return possibleMove;
        } else if (emptyCells.isEmpty()) {
            int coordX = 0;
            int coordY = 0;

            if (token == Token.PLAYERTOKEN) {
                coordX = playerCoordX;
                coordY = playerCoordY;
            } else if (token == Token.AITOKEN) {
                coordX = aiCoordX;
                coordY = aiCoordY;
            }

            PossibleMove possibleMove = new PossibleMove();
            possibleMove.setCoordX(coordX);
            possibleMove.setCoordY(coordY);
            possibleMove.setScore(MinimaxScore.DRAWSCORE.getScore());

            return possibleMove;
        }

        PossibleMove[] possibleMoves = new PossibleMove[emptyCells.size()];

        for (int i = 0; i < emptyCells.size(); i++) {
            int newCoordX = emptyCells.get(i)[1];
            int newCoordY = emptyCells.get(i)[0];
            PossibleMove possibleMove = new PossibleMove();

            possibleMove.setCoordX(newCoordX);
            possibleMove.setCoordY(newCoordY);

            boardCopy.boardRepr[newCoordY][newCoordX] = token.getToken();

            if (token == Token.AITOKEN) {
                int score = getPossibleMove(boardCopy, Token.PLAYERTOKEN, newCoordX, newCoordY, playerCoordX, playerCoordY, targetScore, depth - 1).getScore();
                possibleMove.setScore(score);
            } else if (token == Token.PLAYERTOKEN) {
                int score = getPossibleMove(boardCopy, Token.AITOKEN, aiCoordX, aiCoordY, newCoordX, newCoordY, targetScore, depth - 1).getScore();
                possibleMove.setScore(score);
            }

            possibleMoves[i] = possibleMove;
            boardCopy.boardRepr[newCoordY][newCoordX] = Token.EMPTYTOKEN.getToken();
        }

        if (token == Token.AITOKEN) {
            int bestPossibleScore = MinimaxScore.AIMINSCORE.getScore();

            for (int i = 0; i < possibleMoves.length; i++) {
                if (possibleMoves[i].getScore() > bestPossibleScore) {
                    bestPossibleScore = possibleMoves[i].getScore();
                    bestMoveIndex = i;
                }
            }
        } else if (token == Token.PLAYERTOKEN) {
            int bestPossibleScore = MinimaxScore.PLAYERMINSCORE.getScore();

            for (int i = 0; i < possibleMoves.length; i++) {
                if (possibleMoves[i].getScore() < bestPossibleScore) {
                    bestPossibleScore = possibleMoves[i].getScore();
                    bestMoveIndex = i;
                }
            }
        }

        PossibleMove bestPossibleMove = new PossibleMove();
        bestPossibleMove.setCoordX(possibleMoves[bestMoveIndex].getCoordX());
        bestPossibleMove.setCoordY(possibleMoves[bestMoveIndex].getCoordY());
        bestPossibleMove.setScore(possibleMoves[bestMoveIndex].getScore());

        return bestPossibleMove;
    }

    public void makeAIMove(int coordX, int coordY, Board board) {
        board.boardRepr[coordY][coordX] = Token.AITOKEN.getToken();
    }
}
