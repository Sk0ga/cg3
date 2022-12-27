package com.company.method;
//вспомогательный для вычислений класс
public class BinomialCoefficient {
    private double coef;

    public BinomialCoefficient(double coef) {
        this.coef = coef;
    }

    public void binomialCalc(int n, int i){
        if (n == 0 || i == 0)
            coef = 1;
        else
            coef *= (double) (n - i + 1) / i;
    }

    public double getCoef() {
        return coef;
    }
}
