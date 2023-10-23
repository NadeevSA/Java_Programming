package tictactoe;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        var field = new Field(GameSettings.borderX, GameSettings.borderY);
        var user = new User(CellType.user);
        var computer = new Computer(CellType.computer, CellType.user);
        var computer2 = new Computer(CellType.user, CellType.computer);

        System.out.print("Field " + field.getBorderY() + "x" + field.getBorderX() + " created. ");
        System.out.println("Minimum win count: " + GameSettings.winCount);

        field.printField();
        while(true) {
            if (GameSettings.simulationMode) {
                Thread.sleep(500);
                computer2.move(field);
            } else {
                user.move(field);
            }
            if (field.checkFieldFull()) {
                System.out.println("Draw!");
                break;
            }
            if (field.checkForLength(CellType.user, GameSettings.winCount, new RefInt(), new RefInt())) {
                System.out.println("You win!");
                break;
            }

            computer.move(field);
            if (field.checkFieldFull()) {
                System.out.println("Draw!");
                break;
            }
            if (field.checkForLength(CellType.computer, GameSettings.winCount, new RefInt(), new RefInt())) {
                System.out.println("You lose!");
                break;
            }
        }
    }
}