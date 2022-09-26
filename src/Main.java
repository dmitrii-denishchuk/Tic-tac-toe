import java.util.Random;

public class Main {
    public static final int SIZE = 5;
    public static final char EMPTY = '-';
    public static final char CROSS = 'X';
    public static final char ZERO = 'O';

    public static void main(String[] args) {
        Random random = new Random();
        char[][] field = new char[SIZE][SIZE];
        for (char row = 0; row < SIZE; row++) {
            for (char cell = 0; cell < SIZE; cell++) {
                field[row][cell] = EMPTY;
            }
        }

        boolean isCrossTurn = true;
        int finish = 0;
        while (true) {
            int r = random.nextInt(SIZE);
            int c = random.nextInt(SIZE);
            if (field[r][c] != EMPTY) {
                continue;
            }
            field[r][c] = isCrossTurn ? CROSS : ZERO;
            if (isWin(field, isCrossTurn ? CROSS : ZERO)) {
                printField(field);
                System.out.println("Победили " + (isCrossTurn ? "крестики" : "нолики"));
                break;
            } else {
                isCrossTurn = !isCrossTurn;
                finish++;
            }
            if (finish == SIZE * SIZE) {
                printField(field);
                System.out.println("Ничья!");
                break;
            }
        }
        System.out.println("Игра закончена!");
    }

    public static boolean isWin(char[][] field, int player) {
        int count = 0;
        for (int row = 0; row < SIZE; row++) {
            for (int cell = 0; cell < SIZE; cell++) {
                if (field[row][cell] == player) {
                    count++;
                    if (count == SIZE) {
                        return true;
                    }
                }
            }
            count = 0;
            for (int cell = 0; cell < SIZE; cell++) {
                if (field[cell][row] == player) {
                    count++;
                    if (count == SIZE) {
                        return true;
                    }
                }
            }
            count = 0;
        }

        for (int dia = 0; dia < SIZE; dia++) {
            if (field[dia][dia] == player) {
                count++;
                if (count == SIZE) {
                    return true;
                }
            }
        }
        count = 0;

        for (int dia = 0; dia < SIZE; dia++) {
            if (field[dia][SIZE - 1 - dia] == player) {
                count++;
                if (count == SIZE) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void printField(char[][] field) {
        for (char[] row : field) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}