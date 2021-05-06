package NM.Polynomials;

import NM.Graph.Plot;
import NM.Util.Matrix;
import NM.Util.functions.fX;
import NM.Util.functions.function;
import org.jetbrains.annotations.Contract;
import org.junit.jupiter.api.Test;

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
        private final fX g = (x) ->{
            System.out.println(a.matrix[0][0] + " + " + a.matrix[1][0] + "x + "+ a.matrix[2][0]+ " x^2" );
            return a.matrix[0][0]+ a.matrix[1][0]*x + a.matrix[2][0] * pow(x,2);
        };

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
            Matrix matrix = new Matrix(new double[][]{
                    {1    , XAvg , X2Avg},//x3
                    {XAvg , X2Avg, x3Avg},//x4
                    {X2Avg, x3Avg, x4Avg} //x5
            });      //x3    x4     x5     x6


            System.out.println("Quadratic");
            matrix.printMatrix();
            Matrix r = new Matrix(new double[]{YAvg, XYAvg, x2YAvg});
            r.printMatrix();
            this.a = Matrix.multiply(new Matrix(Matrix.invert(matrix.getMatrix())), r);
        }

        @Override
        public double g(double x){
            return this.g.f_x(x);
        }

    }

    public static class Cubic extends LinearLeastSquares{

        private Matrix a;
        private final fX g = (x) ->{
            System.out.println(a.matrix[0][0] + " + " + a.matrix[1][0] + "x + "+ a.matrix[2][0]+ " x^2 + "+a.matrix[3][0] + " x^3" );
            return a.matrix[0][0]+ a.matrix[1][0]*x + a.matrix[2][0] * pow(x,2) + a.matrix[3][0] * pow(x,3);
        };
        public Cubic(double[] xs, double[] ys) {
            super(xs, ys);
        }
        @Override
        public double g(double x){
            return this.g.f_x(x);
        }


        @Override
        protected void compute()
        {
            double xsum = 0, ysum =0, x2sum =0,x3sum = 0,x4sum = 0,x5sum = 0,x6sum = 0, xysum= 0,x2ysum = 0, x3ysum = 0 ;
            for (int i = 0; i< this.xs.length; i++)
            {
                xsum+= this.xs[i];
                ysum+= this.ys[i];
                x2sum+= pow(this.xs[i],2);
                x3sum+= pow(this.xs[i],3);
                x4sum+= pow(this.xs[i],4);
                x5sum+= pow(this.xs[i],5);
                x6sum+= pow(this.xs[i],6);
                xysum+= (this.xs[i]*this.ys[i]);
                x2ysum+=(pow(this.xs[i],2)*this.ys[i]);
                x3ysum+=(pow(this.xs[i],3)*this.ys[i]);
            }
            XAvg = xsum / this.xs.length;
            YAvg = ysum / this.xs.length;
            XYAvg= xysum / this.xs.length;
            double x2YAvg = x2ysum / this.xs.length;
            double x3YAvg = x3ysum / this.xs.length;
            X2Avg= x2sum / this.xs.length;
            double x3Avg = x3sum / this.xs.length;
            double x4Avg = x4sum / this.xs.length;
            double x5Avg = x5sum / this.xs.length;
            double x6Avg = x6sum / this.xs.length;
            Matrix matrix = new Matrix(new double[][]{
                    {1    , XAvg , X2Avg  , x3Avg},//x3
                    {XAvg , X2Avg, x3Avg  , x4Avg},//x4
                    {X2Avg, x3Avg, x4Avg  , x5Avg}, //x5
                    {x3Avg, x4Avg, x5Avg  , x6Avg }
            });      //x3    x4     x5     x6
            System.out.println("Cubic ");
            matrix.printMatrix();
            Matrix r = new Matrix(new double[]{YAvg, XYAvg, x2YAvg, x3YAvg});  //x3YAvg
            this.a = Matrix.multiply(new Matrix(Matrix.invert(matrix.getMatrix())), r);
        }
    }





    public static void main(String[] args) {

        /*double[] x = new double[]{0,0.2,0.3,0.7,1};
        double[] y = new double[]{0.39,0.56,0.64,0.89,0.99};
        LinearLeastSquares l = new LinearLeastSquares(x,y);
        System.out.println(l.g(0.2));
        LinearLeastSquares l2 = new LinearLeastSquares.Quadratic(x,y);
        System.out.println(l2.g(0.2));
        LinearLeastSquares l3 = new LinearLeastSquares.Cubic(x,y);
        System.out.println(l3.g(0.2));

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
         */


        LinearLeastSquares linear, quadratic , cubic;
        double [] xs =new double[]{ 0,0.25,0.5,0.75,1};
        double [] ys = new double[]{0.876, 1.060, 1.525, 1.993, 2.594};

        linear = new LinearLeastSquares(xs,ys);
        quadratic = new LinearLeastSquares.Quadratic(xs,ys);
        cubic = new LinearLeastSquares.Cubic(xs,ys);

        System.out.println(linear.g(0.4));
        System.out.println(quadratic.g(0.4));
        System.out.println(cubic.g(0.4));
        System.out.println();


        //Plot p = new Plot().plotF(cubic.g,xs);

        xs =new double[]{ 1,1.1,1.3,1.5,1.9,2.1};
        ys = new double[]{ 1.33,1.45,1.7,1.94,2.43,2.67};

        linear = new LinearLeastSquares(xs,ys);
        quadratic = new LinearLeastSquares.Quadratic(xs,ys);
        cubic = new LinearLeastSquares.Cubic(xs,ys);

        //p.plotF2(cubic.g,xs);

        System.out.println(linear.g(2));
        System.out.println(quadratic.g(2));
        System.out.println(cubic.g(2));

        System.out.println();









    }
}
