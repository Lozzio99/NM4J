package Derivative.FirstDeriv;

import functions.dfdX;
import functions.fX;

import static java.lang.Math.pow;

public class ThreePointForward extends Derivative
{

    dfdX de = (x, f)-> (-(f.f_x(x + (2*h))) + (4 * f.f_x(x + h)) - (3 * f.f_x(x))) / (2 * h);

    public ThreePointForward() {
        h = 0.1;
    }

    @Override
    public double derive(double x, fX fx) {
        return de.dfdx(x,fx);
    }

    @Override
    public double derive(double x, fX fx, double dx) {
        h = dx;
        return de.dfdx(x,fx);
    }

    public static void main(String[] args) {
        fX f = (x) -> 1/(1+pow(x,2));
        ThreePointForward d = new ThreePointForward();
        System.out.println(d.derive(2,f,0.001));
    }
}
