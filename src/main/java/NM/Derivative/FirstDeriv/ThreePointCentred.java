package NM.Derivative.FirstDeriv;

import NM.Util.functions.dfdX;
import NM.Util.functions.fX;

import static java.lang.Math.pow;

public class ThreePointCentred extends Derivative
{

    static double h;
    static dfdX de = (x, f)->(f.f_x(x+h) - f.f_x(x-h))/ (2*h);

    public ThreePointCentred(double dx) {
        h = dx;
    }

    public double derive(double x, fX f, double h){
        ThreePointCentred.h = h;
        return de.dfdx(x,f);
    }

    public static void main(String[] args) {
        ThreePointCentred d = new ThreePointCentred(0.1);
        fX f = (x)-> 1/ (1 + pow(x,2));
        System.out.println(d.derive(2,f,0.1));
        System.out.println(d.derive(2,f,0.001));
    }



    public static double stepForward(double h, double fhx, double fxh){
        return (fxh- fhx)/(2*h);
    }

}
