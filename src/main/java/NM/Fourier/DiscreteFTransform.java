package NM.Fourier;

import NM.Util.functions.Fx;

import static java.lang.Math.*;

public class DiscreteFTransform {
    private final Fx<Double> f;
    private Fx<Double> s;
    double[] xs, ys;
    double a0, a1, a2, b1, b2;

    int m;

    public DiscreteFTransform(Fx<Double> f, int m) {
        this.f = f;
        this.xs = new double[(m * 2)];
        this.ys = new double[xs.length];
        this.m = m;
        this.compute();
    }

    private void compute() {
        StringBuilder i_ = new StringBuilder("i : ");
        StringBuilder xi = new StringBuilder("x :");
        StringBuilder yi = new StringBuilder("y :");
        a0 = a1 = a2 = b1 = b2 =0;
        int k = 0;
        for (int i =-m; i<m; i++){
            i_.append(i).append(" , ");
            this.xs[k] = PI*i / m;
            xi.append(this.xs[k]).append(" , ");
            this.ys[k] = this.f.f_x(this.xs[k]);
            yi.append(this.ys[k]).append(" , ");
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
        System.out.println(i_.toString().trim());
        System.out.println(xi.toString().trim());
        System.out.println(yi.toString().trim());

        this.s = (x)-> (a0/2) + (a1*cos(x)) + (b1*sin(x)) + (a2*cos(2*x)) + (b2*sin(2*x)) ;
    }

    public double s(double x){
        return this.s.f_x(x);
    }


    public static void main(String[] args) {
        Fx<Double> f = (x) -> {
            double t = pow(x, 2) / 4;
            return exp(-t);
        };
        DiscreteFTransform fo = new DiscreteFTransform(f, 3);
        System.out.println(fo.s(1));
    }
}
