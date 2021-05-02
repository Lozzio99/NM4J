package Integration;

import Util.functions.fX;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class AdaptiveSimpsons extends Simpsons{

    double epsilon = 0.01;

    public AdaptiveSimpsons(fX f, double ... xs) {
        super(f,xs);
    }

    public double integrate(double a,double b){
        double t1 = step(a,b);
        double mid = (b+a)/2;
        double t2 = step(a,mid) + step(mid,b);
        if (abs(t2-t1)>((mid-a)/(b-a))*epsilon)
            return integrate(a,mid) + integrate(mid,b);
        return t2;
    }
    @Override
    public double step(double p, double q){
        return ((q-p)/12) * (f(p)+ 4*f((3*p+q)/4) + 2*f((p+q)/2) + 4*f((p+3*q)/4)+ f(q));
    }

    public double f(double x){
        return f.f_x(x);
    }

    public static void main(String[] args) {
        fX f = (x)-> 1/(1+pow(x,2));
        AdaptiveSimpsons a = new AdaptiveSimpsons(f, 0,2);
        System.out.println(a.integrate(0,2.));
    }

}
