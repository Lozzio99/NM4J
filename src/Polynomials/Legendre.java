package Polynomials;

import Graph.Plot;
import Util.functions.fX;


// TODO: 02/05/2021 Find coefficients by fx
public class Legendre
{
    private double[] c,Ps;
    private double x;
    private fX f = (x)->
    {
        double x_ = 0;
        for (int i = 0; i< this.c.length; i++)
            x_+= c[i]*Ps[i];
        return x_;
    };
    public Legendre(double[] c, double x){
        this.x = x;
        this.c = c;
        this.Ps = new double[5];
        compute();
    }
    public Legendre(double[] c, double x, int level){
       this.x = x;
       this.c = c;
       this.Ps = new double[level];
       fitPs();
   }

    private void compute() {
        this.Ps[0] = 1;
        this.Ps[1] = this.x;
        this.Ps[2] = ((3/2.)* x *Ps[1]*x) - ((Ps[0]*x)/2);
        this.Ps[3] = ((5/3.)* x *Ps[2]*x) - ((Ps[1]*x)*2/3);
        this.Ps[4] = ((7/4.)* x *Ps[3]*x) - ((Ps[2]*x)*3/4);
    }

    private void fitPs(){
        this.Ps[0] = 1;
        this.Ps[1] = x;
        for (int k = 2; k< this.Ps.length; k++){
            this.Ps[k] = ((2-(1./k))*x* this.Ps[k-1]*x)-(k-1)*Ps[k-2]*x;
        }
    }

    public double g(double x) {
        return f.f_x(x);
    }

    public static void main(String[] args) {
        double []c = new double []{ 0.549, -0.296, 0.106, -0.034, 0.010};
        double x = 0.6;
        Legendre l = new Legendre(c,x,15);
        System.out.println(l.g(x));
        Plot p = new Plot();

    }
}
