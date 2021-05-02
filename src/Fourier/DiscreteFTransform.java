package Fourier;

import Util.functions.fX;

import static java.lang.Math.*;

public class DiscreteFTransform
{
    private fX f,s;
    double[] xs,ys;
    double a0, a1, a2, b1, b2;

    int m;
    public DiscreteFTransform(fX f, int m){
        this.f = f;
        this.xs = new double[(m*2)];
        this.ys = new double[xs.length];
        this.m = m;
        this.compute();
    }

    private void compute() {
        a0 = a1 = a2 = b1 = b2 =0;
        int k = 0;
        for (int i =-m; i<m; i++){
            this.xs[k] = PI*i / m;
            this.ys[k] = this.f.f_x(this.xs[k]);
            a0 += this.ys[k];
            a1 += this.ys[k]*cos(this.xs[k]);
            a2 +=this.ys[k]*cos(2*this.xs[k]);
            b1 += this.ys[k]*sin(this.xs[k]);
            b2 +=this.ys[k]*sin(2*this.xs[k]);
            k++;
        }
        a0 /=m;
        a1 /=m;
        a2 /=m;
        b1 /=m;
        b2 /=m;
        this.s = (x)-> (a0/2) + (a1*cos(x)) + (b1*sin(x)) + (a2*cos(2*x)) + (b2*sin(2*x)) ;
    }

    public double s(double x){
        return this.s.f_x(x);
    }


    public static void main(String[] args) {
        fX f = (x)-> {
            double t = pow(x,2)/4;
            return exp(-t);
        };
        DiscreteFTransform fo = new DiscreteFTransform(f,3);
        System.out.println(fo.s(1));
    }
}
