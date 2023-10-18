package Enums;

public enum TokenName {
    PLAYERNAME("Игрок"),
    AINAME("Компьютер"),
    TIE("Никто");

    private final String _token;

    TokenName(String token) {
        this._token = token;
    }

    public String getToken() {
        return _token;
    }
}
