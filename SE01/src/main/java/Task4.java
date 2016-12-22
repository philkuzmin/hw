/**
 * Даны действительные числа a1, a2 … an.  Найти max
 */
public class Task4 {

    public static void main(String[] args) {

        double[] arr = {1.0, 2.0, 8.0, 4.0, 80.0, 6.0, -4.0, 0.1, -15.0, 3.0};  // задаем последовательность чисел
        System.out.println(findMax(summedArray(arr)));

    }

    private static double[] summedArray(double[] source) {
        double[] result = new double[source.length / 2];
        for (int i = 0; i < result.length; i++) {
            result[i] = source[i] + source[source.length - i - 1];
        }
        return result;
    }

    private static double findMax(double[] source) {
        double max = source[0];
        for (int i = 1; i < source.length; i++) {
            if (source[i] > max) {
                max = source[i];
            }
        }
        return max;
    }

}
