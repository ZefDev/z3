import java.util.Scanner;

public class Player {

    private int move;

    private int name;
    public Player(int name) {
        this.name = name;
    }
    public Player() {

    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
