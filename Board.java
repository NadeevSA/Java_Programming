import java.util.ArrayList;

public class Board {
    public final int sizeX;
    public final int sizeY;
    public char[][] boardRepr;

    public Board(int inputX, int inputY) {
        sizeX = inputX;
        sizeY = inputY;
        boardRepr = new char[sizeY][sizeX];
        fillBoardWithInitValues();
    }

    private void fillBoardWithInitValues() {
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                boardRepr[i][j] = Enums.Token.EMPTYTOKEN.getToken();
            }
        }
    }

    public void showBoard() {
        System.out.println();

        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                System.out.print(String.format("| %c ", boardRepr[i][j]));
            }
            System.out.print('|');
            System.out.println();
        }
    }

    public ArrayList<int[]> getEmptyCells() {
        ArrayList<int[]> emptyCellsCoords = new ArrayList<>();

        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                if (isCellClear(j, i)) {
                    int[] coords = {i, j};
                    emptyCellsCoords.add(coords);
                }
            }
        }

        return emptyCellsCoords;
    }

    public boolean isCellClear(int coordX, int coordY) {
        return boardRepr[coordY][coordX] == Enums.Token.EMPTYTOKEN.getToken();
    }
}
