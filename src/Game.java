import java.util.Scanner;

public class Game {

    // define the game map
    static String[][] map = {
            {"S", ".", "O", ".", "O", "G"},
            {".", ".", ".", ".", ".", "."},
            {".", ".", ".", "O", ".", "."},
            {".", "O", ".", ".", ".", "."},
            {".", ".", ".", ".", ".", "."},
    };

    // define the start and goal positions
    static int startX = 0;
    static int startY = 0;
    static int goalX = 0;
    static int goalY = 5;

    // define the player position
    static int playerX = startX;
    static int playerY = startY;

    // define the player icon
    static String playerIcon = "P";

    // define the movement directions
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static char[] moveChars = {'d', 's', 'a', 'w'};
    public static void printMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                String cell = map[i][j];
                if (i == playerX && j == playerY) {
                    cell = playerIcon;
                }
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // print the initial game map
        printMap();

        // create a scanner to read user input
        Scanner scanner = new Scanner(System.in);

        // loop until the player reaches the goal
        while (playerX != goalX || playerY != goalY) {
            // read user input
            System.out.println("Enter a move (WASD): ");
            String input = scanner.nextLine();

            // check if the input is valid
            if (input.length() != 1 || !isValidMove(input.charAt(0))) {
                System.out.println("Invalid move, try again!");
                continue;
            }

            // calculate the new player position
            int move = getMoveIndex(input.charAt(0));
            int newX = playerX + dx[move];
            int newY = playerY + dy[move];

            // check if the new position is valid
            if (newX < 0 || newX >= map.length || newY < 0 || newY >= map[0].length || map[newX][newY].equals("O")) {
                System.out.println("Invalid move, try again!");
                continue;
            }

            // update the player position and print the updated map
            playerX = newX;
            playerY = newY;
            printMap();
        }

        // the player has reached the goal
        System.out.println("Congratulations, you have reached the goal!");
    }

    // checks if the given character is a valid move
    public static boolean isValidMove(char move) {
        for (char c : moveChars) {
            if (c == move) {
                return true;
            }
        }
        return false;
    }

    public static int getMoveIndex(char move) {
        for (int i = 0; i < moveChars.length; i++) {
            if (moveChars[i] == move) {
                return i;
            }
        }
        return -1;
    }
}

