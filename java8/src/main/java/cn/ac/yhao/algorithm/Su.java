package cn.ac.yhao.algorithm;

public class Su {

    public static void main(String[] args) {
        System.out.println(geometricSum(100, 1, 2));
    }

    /**
     * 等比数列求和
     * Sn = a1*(1-q^n)/(1-q)
     * @param a  首项
     * @param q  等比
     * @param n  项数
     */
    public static double geometricSum(double a, double q, int n) {
        if (q == 1) {
            return a*n;
        }
        return a * (1-Math.pow(q, n))/(1-q);
    }
}
