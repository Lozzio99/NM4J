package NM.Polynomials;

import NM.Graph.Plot;
import NM.Util.Matrix;
import NM.Util.functions.fX;
import NM.Util.functions.function;
import org.jetbrains.annotations.Contract;

import static java.lang.Math.pow;

public class LinearLeastSquares
{
    double[] xs, ys;
    double XAvg,YAvg,XYAvg,X2Avg;
    public LinearLeastSquares(double [] xs, double [] ys)
    {
        if (xs.length != ys.length) throw new IllegalArgumentException("X,Y must have the same siza");
        XAvg = YAvg = XYAvg = X2Avg = 0;
        this.xs = xs;
        this.ys = ys;
        compute();
    }

    protected void compute()
    {
        double xsum = 0, ysum =0, x2sum =0, xysum= 0;
        for (int i = 0; i< this.xs.length; i++){
            xsum+= this.xs[i];
            ysum+= this.ys[i];
            x2sum+= pow(this.xs[i],2);
            xysum+= (this.xs[i]*this.ys[i]);
        }
        XAvg = xsum / this.xs.length;
        YAvg = ysum / this.xs.length;
        XYAvg= xysum / this.xs.length;
        X2Avg= x2sum / this.xs.length;
    }

    @Contract(pure = true)
    private double a0(){
        return YAvg - a1() * XAvg;
    }
    @Contract(pure = true)
    private double a1(){
        return (XYAvg - (XAvg * YAvg))/(X2Avg - pow(XAvg,2));
    }

    private final fX g = (x) -> a0() + a1()*x;

    public double g(double x){ return g.f_x(x); }

    public double[] g(double[] xs){
        double[] x = new double [xs.length];
        for (int i = 0; i< x.length;i++)
            x[i] = g(xs[i]);
        return x;
    }


    public static class Quadratic extends LinearLeastSquares
    {
        private Matrix a;
        private final fX g = (x) ->a.matrix[0][0]+ a.matrix[1][0]*x + a.matrix[2][0] * pow(x,2);


        public Quadratic(double[] x, double[] y) {
            super(x, y);
        }


        @Override
        protected void compute()
        {
            double xsum = 0, ysum =0, x2sum =0,x3sum = 0,x4sum = 0, xysum= 0,x2ysum = 0 ;
            for (int i = 0; i< this.xs.length; i++)
            {
                xsum+= this.xs[i];
                ysum+= this.ys[i];
                x2sum+= pow(this.xs[i],2);
                x3sum+= pow(this.xs[i],3);
                x4sum+= pow(this.xs[i],4);
                xysum+= (this.xs[i]*this.ys[i]);
                x2ysum+=(pow(this.xs[i],2)*this.ys[i]);
            }
            XAvg = xsum / this.xs.length;
            YAvg = ysum / this.xs.length;
            XYAvg= xysum / this.xs.length;
            double x2YAvg = x2ysum / this.xs.length;
            X2Avg= x2sum / this.xs.length;
            double x3Avg = x3sum / this.xs.length;
            double x4Avg = x4sum / this.xs.length;
            Matrix matrix = new Matrix(new double[][]{{1, XAvg, X2Avg}, {XAvg, X2Avg, x3Avg}, {X2Avg, x3Avg, x4Avg}});
            Matrix r = new Matrix(new double[]{YAvg, XYAvg, x2YAvg});
            this.a = Matrix.multiply(new Matrix(Matrix.invert(matrix.getMatrix())), r);
        }

        @Override
        public double g(double x){
            return this.g.f_x(x);
        }

    }

    public static void main(String[] args) {
        double[] x = new double[]{0,0.2,0.3,0.7,1};
        double[] y = new double[]{0.39,0.56,0.64,0.89,0.99};
        LinearLeastSquares l = new LinearLeastSquares(x,y);
        System.out.println(l.g(0.5));
        LinearLeastSquares l2 = new LinearLeastSquares.Quadratic(x,y);
        System.out.println(l2.g(0.2));

        double[] xs = new double[100],ys = new double[100];
        double k = 0;
        for (int i = 0; i< xs.length; i++){
            xs[i] = k;
            ys[i] = l2.g(k);
            k+=0.01;
        }
        Plot p = new Plot();
        p.scale(400);
        p.translate(-200,200);
        p.plot(x,y).plot2(xs,ys);



    }
}
