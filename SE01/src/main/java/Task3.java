/**
 * Составить программу для вычисления значений функции F(x) на отрезке [а, b] с шагом h.
 * Результат представить в виде таблицы, первый столбец которой – значения аргумента,
 * второй - соответствующие значения функции
 */
public class Task3 {

    public static void main(String[] args) {

        double a = 0.1;     // задаем начало отрезка
        double b = 2.99;    // задаем конец отрезка
        double h = 0.35;    // задаем шаг

        System.out.printf("%-12s%-12s\n", "Argument", "Function");

        for (double arg = a; arg <= b; arg += h) {
            System.out.printf("%-12.2f%-12.3f\n", arg, calculateFunction(arg));
        }

    }

    private static double calculateFunction(double arg) {
        return Math.tan(2 * arg) - 3;
    }
}
