/**
 * Created by Phil on 13.12.16.
 */
public class Task3 {

    public static void main(String[] args) {

        double a = 0.1;
        double b = 2.99;
        double h = 0.35;

        System.out.printf("%-12s%-12s\n", "Argument", "Function");

        for (double arg = a; arg <= b; arg += h) {
            System.out.printf("%-12.2f%-12.3f\n", arg, calculateFunction(arg));
        }

    }

    private static double calculateFunction(double arg) {
        return Math.tan(2 * arg) - 3;
    }
}
