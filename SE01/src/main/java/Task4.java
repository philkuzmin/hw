/**
 * Created by Air on 14/12/2016.
 */
public class Task4 {

    public static void main(String[] args) {

        double[] arr = {1.0, 2.0, 8.0, 4.0, 80.0, 6.0, -4.0, 0.1, -15.0};
        System.out.println(findMax(summedArray(arr)));

    }

    static double[] summedArray(double[] source) {
        double[] result = new double[source.length / 2];
        for (int i = 0; i < (source.length / 2); i++) {
            result[i] = source[i] + source[source.length - i - 1];
        }
        return result;
    }

    static double findMax(double[] source) {
        double max = source[0];
        for (int i = 1; i < source.length; i++) {
            if (source[i] > max) {
                max = source[i];
            }
        }
        return max;
    }

}
