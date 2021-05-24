package NM.Util.Exercises;

import NM.Roots.Bisection;
import NM.Roots.NewtonRaphson;
import NM.Roots.Root;
import NM.Roots.Secant;
import NM.Util.functions.Fx;

public class ExerciseH8
{
    public static void main(String[] args) {
        Fx<Double> f = (x) -> x * x - 5;
        test(f,2,3);


        f = (x)-> (x*x*x) - 3 *(x*x) -7;
        test(f,1,4);


        f = (x)-> Math.exp(x)- 3*x- 4;
        test(f,0,4);


        f = (x)-> Math.cos(x)- Math.sqrt(x);
        test(f,0,1);
    }

    public static void test(Fx<Double> f, double p1, double p2) {
        testBisection(f, p1, p2);
        System.out.println("--------------------------");
        testNewton(f, p1, p2);
        System.out.println("--------------------------");
        testSecant(f, p1, p2);
        System.out.println("--------------------------");
    }

    public static void testBisection(Fx<Double> f, double p1, double p2) {
        Root b = new Bisection(f).setEpsilon(0.1);
        b.findRoot(p1, p2);
        System.out.println("Bisection 10^-1");
        System.out.println(b.getP_stages());
        b = new Bisection(f).setEpsilon(0.001);
        b.findRoot(p1, p2);
        System.out.println("Bisection 10^-3");
        System.out.println(b.getP_stages());
    }

    public static void testSecant(Fx<Double> f, double p1, double p2) {
        Root s = new Secant(f).setEpsilon(0.1);
        s.findRoot(p1, p2);
        System.out.println("Secant 10^-1");
        System.out.println(s.getP_stages());
        s = new Bisection(f).setEpsilon(0.001);
        s.findRoot(p1, p2);
        System.out.println("Secant 10^-3");
        System.out.println(s.getP_stages());
    }

    public static void testNewton(Fx<Double> f, double p1, double p2) {
        Root n = new NewtonRaphson(f).setEpsilon(0.1);
        n.findRoot((p2 - p1) / 2);
        System.out.println("Newton 10^-1");
        System.out.println(n.getP_stages());
        n = new NewtonRaphson(f).setEpsilon(0.001);
        n.findRoot((p2 - p1) / 2);
        System.out.println("Newton 10^-3");
        System.out.println(n.getP_stages());

    }
}
