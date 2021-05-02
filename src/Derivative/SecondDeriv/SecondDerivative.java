package Derivative.SecondDeriv;

import Derivative.FirstDeriv.Derivative;
import Util.functions.dfdX;

import static java.lang.Math.pow;

/**
 * @NumericalMath/src/HigherDerivatives.png
 */
public class SecondDerivative extends Derivative
{
    private static dfdX secondDerivative = (x, f1) -> ((f.f_x(x-h) - (2*f.f_x(x)) + f.f_x(x+ h))/(pow(h,2)));

    private static dfdX ThreePointScheme = (x, f)-> (f(x) - (2*f(x+h))+ f(x+(2*h)))/(pow(h,2));

    private static dfdX FivePointForward = (x, f)-> (-f(x-2*h) + 16*f(x-h) - 30*f(x) + 16*f(x+h) - f(x+2*h))/(12*pow(h,2));

    private static dfdX FivePointAsymm = (x, f) -> (11*f(x-h)- 20*f(x) + 6*f(x+h) + 4*f(x+2*h) - f(x+3*h))/(12*pow(h,2));

    private static dfdX FivePointCentred = (x, f)-> (35*f(x)- 104*f(x+h) + 114*f(x+2*h)- 56*f(x+3*h) + 11*f(x+4*h))/(12*pow(h,2));

    public static void main(String[] args) {
        SecondDerivative d = new SecondDerivative();
        d.setH(0.0004);
        d.setDe(secondDerivative);
        System.out.println(d.derive(2,f));
        d.setDe(ThreePointScheme);
        System.out.println(d.derive(2,f));
        d.setDe(FivePointForward);
        System.out.println(d.derive(2,f));
        d.setDe(FivePointAsymm);
        System.out.println(d.derive(2,f));
        d.setDe(FivePointCentred);
        System.out.println(d.derive(2,f));
    }


    public static double _3PForwardStep(double h, double fx, double fxh, double fx2h){
        return (fx - (2*fxh) + fx2h)/ pow(h,2);
    }

    public static double _3PBackwardStep(double h, double f2hx, double fhx, double fx){
        return (f2hx - 2*fhx + fx)/ pow(h,2);
    }

    public static double _3PCentredStep(double h, double fhx,double fx, double fxh){
        return (fhx - (2*fx) +  fxh)/  pow(h,2);
    }


}
