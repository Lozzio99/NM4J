package NM.Integration;

import NM.Util.functions.Fx;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class AdaptiveTrapezoid extends Trapezoid
{
    double epsilon = 0.001;

    public AdaptiveTrapezoid(Fx<?> f, double... xs) {
        super(xs.length - 1, f, xs);
    }

    public AdaptiveTrapezoid(int n, Fx<?> f, double... xs) {
        super(n, f, xs);
    }

    public double nRule(double a, double b){
        double t1 = step(a,b);
        double mid = (b+a)/2;
        double t2 = step(a,mid) + step(mid,b);
        if (abs(t2-t1)>epsilon)
            return nRule(a,mid) + nRule(mid,b);
        return t2;
    }

    public static void main(String[] args) {
        Fx<Double> f = (x) -> 1 / (3 + pow(x, 4));
        AdaptiveTrapezoid a = new AdaptiveTrapezoid(f, 0, 2);
        System.out.println(a.nRule(0,2.));
    }
}
