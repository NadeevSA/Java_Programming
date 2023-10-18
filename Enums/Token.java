package Enums;

public enum Token {
    PLAYERTOKEN('X'),
    AITOKEN('O'),
    EMPTYTOKEN('.');

    private final char _token;

    Token(char token) {
        this._token = token;
    }

    public char getToken() {
        return _token;
    }
}
