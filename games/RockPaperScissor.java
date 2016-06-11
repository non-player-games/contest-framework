package games;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A quick rock paper scissor game for testing game engine implementation
 */
public class RockPaperScissor {
    public enum Type {
        ROCK("ROCK"),
        SCISSOR("SCISSOR"),
        PAPER("PAPER");

        static {
            ROCK.winAgainst = SCISSOR;
            SCISSOR.winAgainst = PAPER;
            PAPER.winAgainst = ROCK;
        }

        private final String name;
        private Type winAgainst;

        Type(String name) {
            this.name = name;
        }

        public static Type fromName(String name) {
            for (Type type: values()) {
                if (type.name.equalsIgnoreCase(name)) {
                    return type;
                }
            }

            throw new NoSuchElementException("Name is not found within types");
        }
    }

    public enum Result {
        WIN,
        LOSE,
        TIE;

        static {
            WIN.opposite = LOSE;
            LOSE.opposite = WIN;
            TIE.opposite = TIE;
        }

        Result opposite;
    }

    protected Result result = Result.TIE;

    public static void main(String[] args) {
        RockPaperScissor game = new RockPaperScissor();
        Scanner in = new Scanner(System.in);

        while (true) {
            switch (in.nextLine()) {
                case "game_setup":
                    // set up game engine
                    break;
                case "game_start":
                    // game started!
                    break;
                case "turn_start":
                    game.turnStart();
                    break;
                case "turn_end":
                    Type p1Move = Type.ROCK;
                    Type p2Move = Type.ROCK;
                    try {
                        p1Move = Type.fromName(in.nextLine());
                    } catch (NoSuchElementException e) {
                        game.result = Result.LOSE;
                    }
                    try {
                        p2Move = Type.fromName(in.nextLine());
                    } catch (NoSuchElementException e) {
                        game.result = (game.result == Result.LOSE) ? Result.TIE : Result.WIN;
                    }
                    game.turnEnd(p1Move, p2Move);
                    game.gameEnd();
                    System.out.println("P1: " + game.result);
                    System.out.println("P1: " + game.result.opposite);
                    System.out.flush();
                    break;
            }
        }
    }

    public void start() {

    }

    public void turnStart() {

    }

    public void turnEnd(Type p1Move, Type p2Move) {
        if (p1Move.winAgainst == p2Move) {
            result = Result.WIN;
        } else if (p1Move == p2Move.winAgainst) {
            result = Result.LOSE;
        } else {
            result = Result.TIE;
        }
    }

    public void gameEnd() {

    }
}
