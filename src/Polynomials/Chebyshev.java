package Polynomials;

import functions.fX;

import java.util.Arrays;

// TODO: 02/05/2021 Find coefficients by fx
public class Chebyshev
{
    private double[] c, Ts;
    private double x;
    private fX f = (x)->
    {
        double x_ = 0;
        for (int i = 0; i< this.c.length; i++)
            x_+= c[i]* Ts[i];
        return x_;
    };

    public Chebyshev(double[] c, double x, int level){
        this.x = x;
        this.c = c;
        this.Ts = new double[level];
        fitTs();
    }

    private void fitTs(){
        this.Ts[0] = 1;
        this.Ts[1] = x;
        for (int k = 1; k< this.Ts.length-1; k++){
            this.Ts[k+1] = (2*x*Ts[k]) - (Ts[k-1]);
        }
        System.out.println(Arrays.toString(Ts));
    }

    public double g(double x) {
        return f.f_x(x);
    }

    public static void main(String[] args) {
        double [] c = new double[]{ 0.5774, -0.3094, 0.0829, -0.0222, 0.0060};
        System.out.println(new Chebyshev(c,0.2,5).g(0.2));
    }

}
