package Integration;

import Util.functions.fX;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class AdaptiveTrapezoid extends Trapezoid
{
    fX f;
    double epsilon = 0.001;


    public AdaptiveTrapezoid(fX f,double... xs){
        super(0,f,xs);
        this.f = f;
    }

    public double integrate(double a,double b){
        double t1 = step(a,b);
        double mid = (b+a)/2;
        double t2 = step(a,mid) + step(mid,b);
        if (abs(t2-t1)>epsilon)
            return integrate(a,mid) + integrate(mid,b);
        return t2;
    }

    public static void main(String[] args) {
        fX f = (x)-> 1/(3+pow(x,4));
        AdaptiveTrapezoid a = new AdaptiveTrapezoid(f, 0,2);
        System.out.println(a.integrate(0,2.));
    }
}
