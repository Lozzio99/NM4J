package NM.Derivative.FirstDeriv;

import NM.Util.functions.Fx;
import NM.Util.functions.dfdX;

import static java.lang.Math.pow;

public class Derivative
{
    public static Fx<Double> f = (x) -> 1 / (1 + pow(x, 2));

    protected static double h;
    private dfdX<Double> de;

    protected double [] xs,ys;
    protected double[] f1x,f2x;

    public Derivative(double dx){
        h = dx;
    }

    public Derivative(){
        h = 0.1;
    }

    public static void main(String[] args) {
        Fx<Double> f = (x) -> 1 / (1 + pow(x, 2));
        Derivative d = new Derivative(0.5);
        System.out.println(d.derive(2, f, 0.5));
        System.out.println(d.derive(2, f, 0.1));
        System.out.println(d.derive(2, f, 0.01));
        System.out.println(d.derive(2, f, -0.1));
    }

    public void setH(double h) {
        Derivative.h = h;
    }

    public void setDe(dfdX<Double> de) {
        this.de = de;
    }

    public double derive(double x, Fx<Double> fx) {
        return de.dfdx(x, fx);
    }

    public double derive(double x, Fx<Double> fx, double dx) {
        h = dx;
        return de.dfdx(x, fx);
    }

    public static double f(double x) {
        return f.f_x(x);
    }
}
