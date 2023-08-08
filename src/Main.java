import java.util.Random;
import java.util.Scanner;

public class Main {
    static char[][] map;
    static final int SIZE = 3;

    static final char DOT_EMPTY = '.';
    static final char DOT_X = 'X';
    static final char DOT_O = 'O';


    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            userTurn();
            printMap();
            if (checkWin(DOT_X)){
                System.out.println("Вы победили! :-)");
                break;
            }
            if (ifMapFull()){
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)){
                System.out.println("Вы проиграли :-(");
                break;
            }
            if (ifMapFull()){
                System.out.println("Ничья");
                break;
            }
        }
    }

    private static void aiTurn(){
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компухктер походил в " + (x + 1) + (y + 1));
        map[y][x] = DOT_O;
    }

    private static void initMap(){
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    private static void printMap(){
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

    private static void userTurn(){
        Scanner scn = new Scanner(System.in);
        int x, y;
        do {
            System.out.println("Введите координаты по X и Y");
            x = scn.nextInt() - 1;
            y = scn.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
    }

    private static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }

    private static boolean checkWin(char symb) {
        if (map[0][0] == symb && map[0][1] == symb && map[0][2] == symb) return true;
        if (map[1][0] == symb && map[1][1] == symb && map[1][2] == symb) return true;
        if (map[2][0] == symb && map[2][1] == symb && map[2][2] == symb) return true;
        if (map[0][0] == symb && map[1][0] == symb && map[2][0] == symb) return true;
        if (map[0][1] == symb && map[1][1] == symb && map[2][1] == symb) return true;
        if (map[0][2] == symb && map[1][2] == symb && map[2][2] == symb) return true;
        if (map[0][0] == symb && map[1][1] == symb && map[2][2] == symb) return true;
        if (map[2][0] == symb && map[1][1] == symb && map[0][2] == symb) return true;


        return false;
    }

    private static boolean ifMapFull() {
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
    return true;
    }

}