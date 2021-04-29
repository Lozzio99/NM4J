package Polynomials;

import functions.fX;

public abstract class Interpolation {

    public fX f ;
    public double []xs ;
    public double []ys ;

    public Interpolation(fX f, double... x) {
        this.f = f;
        xs = x;
        ys = fill(xs);
    }

    public double[] fill(double[] xs)
    {
        double[] y = new double[xs.length];
        for (int i = 0; i< xs.length; i++){
            y[i] = this.f.f_x(xs[i]);
        }
        return y;
    }

    public abstract double polynomial(double x);

    public double[] getXs() {
        return xs;
    }

    public double[] getYs() {
        return ys;
    }
}
