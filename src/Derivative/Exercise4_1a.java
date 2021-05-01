package Derivative;

import Derivative.FirstDeriv.ThreePointCentred;
import Derivative.FirstDeriv.ThreePointForward;
import Derivative.SecondDeriv.SecondDerivative;

import java.util.Arrays;

public class Exercise4_1a
{
    public static void main(String[] args)
    {
        double x0 = 0.9,  x1 = 1   , x2 = 1.1  , x3 = 1.2;
        double f0 = 7.91, f1 = 9.91, f2 = 12.35, f3 = 15.33;
        double[] f1x = new double [4];
        f1x[0] = ThreePointForward.stepForward((x1-x0),f0,f1,f2);
        f1x[1] = ThreePointCentred.stepForward((x2-x1),f0,f2);
        f1x[2] = ThreePointCentred.stepForward((x3-x2),f1,f3);
        f1x[3] = ThreePointForward.stepBackward((x3-x2),f1,f2,f3);

        double[] f2x = new double[4];
        f2x[0] = SecondDerivative._3PForwardStep((x1-x0),f0,f1,f2);
        f2x[1] = SecondDerivative._3PCentredStep((x2-x1),f0,f1,f2);
        f2x[2] = SecondDerivative._3PCentredStep((x3-x2),f1,f2,f3);
        f2x[3] = SecondDerivative._3PBackwardStep((x3-x2),f1,f2,f3);


        System.out.println(Arrays.toString(f1x));
        System.out.println("///---------------------------------------------////");
        System.out.println(Arrays.toString(f2x));

    }
}
