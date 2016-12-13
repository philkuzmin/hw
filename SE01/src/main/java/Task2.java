/**
 * Найти наименьший номер элемента последовательности, для которого выполняется условие M.
 * Вывести на экран этот номер и все элементы ai  где i = 1, 2, ..., п.
 */
public class Task2 {

    public static void main(String[] args) {

        double eps = 0.009;
        double result;
        int i = 0;

        do {
            result = getElement(i);
            System.out.printf("a%d = %.10f\n", ++i, result);
        } while (result > eps);

        System.out.println("The smallest number is " + i);
    }

    static double getElement(int n) {
        return (1 / (Math.pow((n + 1), 2)));
    }

}
