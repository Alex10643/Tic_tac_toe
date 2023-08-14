import java.util.Random;
import java.util.Scanner;

public class Main {
    static char[][] map;
    static final int SIZE = 3;

    static final char DOT_EMPTY = '.';
    static final char DOT_X = 'X';
    static final char DOT_O = 'O';


    public static void main(String[] args) {
        System.out.println("Hello!");
        game();
    }

    private static void game() {
        initMap();
        printMap();
        while (true) {
            userTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("You win! :-)");
                break;
            }
            if (ifMapFull()) {
                System.out.println("Draw");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("You loose :-(");
                break;
            }
            if (ifMapFull()) {
                System.out.println("draw");
                break;
            }
        }
        restartGame();
    }

    private static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    private static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void userTurn() {
        Scanner scn = new Scanner(System.in);
        int x, y;
        do {
            System.out.println("Enter X and Y coordinates");
            x = scn.nextInt() - 1;
            y = scn.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
    }

    private static void aiTurn() {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("AI took a step into " + (x + 1) + (y + 1));
        map[y][x] = DOT_O;
    }

    private static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }

    static boolean checkWin(char symb) {
    /*    if (map[0][0] == symb && map[0][1] == symb && map[0][2] == symb) return true;
        if (map[1][0] == symb && map[1][1] == symb && map[1][2] == symb) return true;
        if (map[2][0] == symb && map[2][1] == symb && map[2][2] == symb) return true;
        if (map[0][0] == symb && map[1][0] == symb && map[2][0] == symb) return true;
        if (map[0][1] == symb && map[1][1] == symb && map[2][1] == symb) return true;
        if (map[0][2] == symb && map[1][2] == symb && map[2][2] == symb) return true;
        if (map[0][0] == symb && map[1][1] == symb && map[2][2] == symb) return true;
        if (map[2][0] == symb && map[1][1] == symb && map[0][2] == symb) return true;
     */
        for (int i = 0; i < SIZE; i++) {
            if ((map[i][0] == symb && map[i][1] == symb && map[i][2] == symb) || (map[0][i] == symb && map[1][i] == symb && map[2][i] == symb))
                return true;
            if ((map[0][0] == symb && map[1][1] == symb && map[2][2] == symb) || (map[2][0] == symb && map[1][1] == symb && map[0][2] == symb))
                return true;
        }
        return false;
    }

    private static boolean ifMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    private static void restartGame() {
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to try again? Yes or no?");
        String ans = input.nextLine();
        switch (ans.toLowerCase()) {
            case "yes":
                game();
                break;
            case "no":
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Try entering the answer again");
                restartGame();
        }
    }

}