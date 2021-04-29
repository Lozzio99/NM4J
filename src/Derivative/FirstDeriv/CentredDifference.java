package Derivative.FirstDeriv;

import functions.dfdX;
import functions.fX;

import static java.lang.Math.pow;

public class CentredDifference
{

    static double h;
    static dfdX de = (x, f)->(f.f_x(x+h) - f.f_x(x-h))/ (2*h);

    public CentredDifference(double dx) {
        h = dx;
    }

    public double derive(double x, fX f, double h){
        CentredDifference.h = h;
        return de.dfdx(x,f);
    }

    public static void main(String[] args) {
        CentredDifference d = new CentredDifference(0.1);
        fX f = (x)-> 1/ (1 + pow(x,2));
        System.out.println(d.derive(2,f,0.1));
        System.out.println(d.derive(2,f,0.001));
    }
}
