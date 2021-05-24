package NM.Util.Exercises;

import NM.Integration.Midpoint;
import NM.Integration.Simpsons;
import NM.Integration.Trapezoid;
import NM.Util.functions.Fx;

import static java.lang.Math.log;

public class Lecture1
{
    public static void main(String[] args) {

        Fx<Double> f = (x) -> x * log(x);
        double[] xs = new double[]{1, 1.5, 2, 2.5, 3};
        Midpoint m = new Midpoint(f, xs);
        Simpsons s = new Simpsons(f, xs);
        Trapezoid t = new Trapezoid(f, xs);
        System.out.println(m.integrate());
        System.out.println(s.integrate());
        System.out.println(t.integrate());
    }

}
