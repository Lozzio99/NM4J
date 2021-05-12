package NM.Fourier;

import java.util.Arrays;

import static java.lang.StrictMath.*;

public class FourierData extends FourierApprox
{
    double [] xs,ys;
    int n;

    public FourierData(double [] xs, double[] ys) {
        this.n = 3; //degree=??
        this.xs = xs;
        this.ys = ys;
        this.a = new double[ this.n];
        this.b = new double[ this.n];
        this.compute();
    }
    private void compute(){
        double s = sum(this.ys);
        this.a[0] =( 1/3.) * s;
        for (int i = 1; i< this.a.length; i++){
            this.a[i] = (1/3.)* sum(xs,ys,false,i);
            this.b[i] = (1/3.)* sum(xs,ys,true,i);
        }

        this.makeS();
    }

    private double sum(double [] ys){
        double sum = 0;
        for (double y : ys){
            sum+= y;
        }
        return sum;
    }

    private double sum(double[]xs, double[] ys, boolean sine, int k){
        double sum = 0;
        for (int i = 0;i< ys.length; i++) {
            if (sine){
                sum += ys[i] * sin(k*xs[i]);
            }else{
                sum += ys[i] * cos(k*xs[i]);
            }
        }
        return sum;
    }

    @Override
    public void makeS(){
        this.s = x->{
            double sum = a[0]/2;
            for (int i = 1; i< this.a.length; i++){
                sum += ((a[i]*cos(i*x))  + (b[i]*sin(i*x)));
            }
            return sum;
        };
    }


    public static void main(String[] args) {
        double [] x = new double []{ -PI,-2*PI/3, -PI/3,   0 , PI/3, 2*PI/3,  PI},
                  y = new double[]{  .54,    .37,   .69, 1.87, 2.69,   1.44, .54};
        FourierData four = new FourierData(x,y);

        for (int i = 0;i<y.length; i++){
            System.out.println(y[i]);
            System.out.println(four.s(x[i]));
        }
    }

}
