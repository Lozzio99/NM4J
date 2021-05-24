package NM.Polynomials;

import NM.Util.functions.Fx;

public class SimpleLagrange
{

    Fx<Double> f;

    public double x1,x2,x0;
    public double y1,y2,y0;

    SimpleLagrange(double x0, double x1, double x2)
    {
        super();
        this.x0 = x0;
        this.x1 = x1;
        this.x2 = x2;
        y0 = f.f_x(x0);
        y1 = f.f_x(x1);
        y2 = f.f_x(x2);
    }

    public double interpolate(double x)
    {
        //double px = y0*l0
        return y0*l0(x) + y1*l1(x) + y2*l2(x);
    }

    public double l0(double x)
    {
        return ((x-x1)*(x-x2))/((x0-x1)*(x0-x2));
    }
    public double l1(double x){
        return ((x-x0)*(x-x2))/((x1-x0)*(x1-x2));
    }
    public double l2(double x){
        return ((x-x0)*(x-x1))/((x2-x0)*(x2-x1));
    }

}
