package NM.Roots;

import NM.Util.functions.fX;

public class Derivative
{
    static double h = 0.001;
    public static double derive(fX f, double x0){
        return (f.f_x(x0+h) - f.f_x(x0))/h;
    }
}
