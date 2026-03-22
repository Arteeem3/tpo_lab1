package task1;

public class AsinPowerRow {
    public double asin(double x, int n) {
        if (Double.isNaN(x) || x > 1 || x < -1) {
            return Double.NaN;
        }

        double term = x;
        double res = term;

        for (int k = 1; k <= n; k++) {
            term *= (2.0 * k - 1) * (2.0 * k - 1) * x * x / (2.0 * k * (2.0 * k + 1));
            res += term;
        }
        return res;
    }
}
