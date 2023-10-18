package Enums;

public enum MinimaxScore {
    PLAYERSCORE(-10),
    PLAYERMINSCORE(100000),
    AISCORE(10),
    AIMINSCORE(-100000),
    DRAWSCORE(0);

    private final int _score;

    MinimaxScore(int score) {
        this._score = score;
    }

    public int getScore() {
        return _score;
    }
}
