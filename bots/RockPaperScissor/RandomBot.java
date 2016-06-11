package bots.RockPaperScissor;

import java.util.Random;
import java.util.Scanner;

public class RandomBot {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (true) {
            switch (in.nextLine()) {
                case "bot_start":
                    // bot starting warming up
                    break;
                case "turn_start":
                    System.out.print(generateRandomMove());
                    System.exit(0);
                    break;
            }
        }
    }

    private static String generateRandomMove() {
        Random rand = new Random();
        switch (rand.nextInt(3)) {
            case 0:
                return "ROCK";
            case 1:
                return "PAPER";
            default:
                return "SCISSOR";
        }
    }
}
