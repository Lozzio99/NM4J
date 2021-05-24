package NM.Derivative.FirstDeriv;

import NM.Util.functions.dfdX;

public class FivePointFormulae extends Derivative {
    private static final dfdX<Double> FPForward = (x, f) -> ((-25 * f.f_x(x)) + (48 * f.f_x(x + h)) - (36 * f.f_x(x + (2 * h))) + (16 * f.f_x(x + (3 * h))) - (3 * f.f_x(x + (4 * h)))) / (12 * h);

    private static final dfdX<Double> FPAsymmetric = (x, f) -> ((-3 * f.f_x(x - h)) - (10 * f.f_x(x)) + (18 * f.f_x(x + h)) - (6 * f.f_x(x + (2 * h))) + f.f_x(x + (3 * h))) / (12 * h);

    private static final dfdX<Double> FPCentred = (x, f) -> (f.f_x(x - (2 * h)) - (8 * f.f_x(x - h)) + (8 * f.f_x(x + h)) - f.f_x(x + (2 * h))) / (12 * h);

    public static void main(String[] args) {
        FivePointFormulae d = new FivePointFormulae();
        d.setDe(FPForward);
        System.out.println(d.derive(2,f,0.01));
        d.setDe(FPAsymmetric);
        System.out.println(d.derive(2,f,0.01));
        d.setDe(FPCentred);
        System.out.println(d.derive(2,f,0.01));
    }
}