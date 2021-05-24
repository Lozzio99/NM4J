package NM.Polynomials;

import NM.Integration.Simpsons;
import NM.Util.functions.Fx;

import java.util.Arrays;

public class Main
{
    public static void main(String[] args) {

        Fx<Double> f = (x) -> Math.exp(-x) / (1 + x);
        Lagrange l2 = new Lagrange(f, 0, 0.5, 1);
        System.out.println(Arrays.toString(l2.interpolate()));
        Simpsons s = new Simpsons(l2);
        System.out.println(s.step(0,1));



    }

}
