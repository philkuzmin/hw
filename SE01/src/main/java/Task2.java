/**
 * Created by Phil on 13.12.16.
 */
public class Task2 {

    public static void main(String[] args) {

        double eps = 0.26;
        double result;
        int i = 1;

        do {
            result = getElement(i);
            System.out.printf("a%d = %.6f\n", i++, result);
        } while (i<10);

    }

    static double getElement(int n) {
        return (1 / (Math.pow((n + 1), 2)));
    }

}
