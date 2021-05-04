package NM.Integration;

import NM.Util.functions.fX;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class AdaptiveSimpsons extends Simpsons{

    double epsilon = 0.01;

    public AdaptiveSimpsons(fX f, double ... xs) {
        super(xs.length-1,f,xs);
    }

    public AdaptiveSimpsons(int n, fX f, double...xs){
        super(n,f,xs);
    }

    public double nRule(double a, double b){
        double t1 = step(a,b);
        double mid = (b+a)/2;
        double t2 = step(a,mid) + step(mid,b);
        if (abs(t2-t1)>((mid-a)/(b-a))*epsilon)
            return nRule(a,mid) + nRule(mid,b);
        return t2;
    }
    @Override
    public double step(double p, double q){
        return ((q-p)/12) * (f(p)+ 4*f((3*p+q)/4) + 2*f((p+q)/2) + 4*f((p+3*q)/4)+ f(q));
    }

    public static void main(String[] args) {
        fX f = (x)-> 1/(1+pow(x,2));
        AdaptiveSimpsons a = new AdaptiveSimpsons(f, 0,2);
        System.out.println(a.nRule(0,2.));
    }

}
