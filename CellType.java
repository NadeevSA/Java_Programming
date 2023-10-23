package tictactoe;

public enum CellType {
    user('X'), computer('0'), empty(' ');

    public final char type;
    CellType(char x) {
        this.type = x;
    }
}
