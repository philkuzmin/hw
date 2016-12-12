/**
 * Created by Air on 13/12/2016.
 */
public class Task5 {

    public static void main(String[] args) {

        printMatrix(generateMatrix(11));

    }

    private static int[][] generateMatrix(int size) {
        int[][] arr = new int[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if ((y == x) || (y == (size - x - 1))) { // Вводим дополнительную проверку, но сокращаем итерации
                    arr[y][x] = 1;
                    arr[y][size - x - 1] = 1;
                    break;
                }
            }
        }
        return arr;
    }

    private static void printMatrix(int[][] arr) {
        for (int[] row : arr) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
