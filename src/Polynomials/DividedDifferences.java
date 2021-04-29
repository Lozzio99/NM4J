package Polynomials;

import java.util.Arrays;

public class DividedDifferences
{
    double xs[];
    double ys[];
    double fx[][];
    public DividedDifferences(double... x_y){
       if (x_y.length%2!=0)
           throw new RuntimeException("fill in : x,y - x,y - ...");
       this.xs = new double[x_y.length/2];
       this.ys = new double[x_y.length/2];
       int k = 0;
       for (int i = 0; i< x_y.length; i+= 2)
       {
           this.xs[k] = x_y[i];
           this.ys[k] = x_y[i+1];
           k++;
       }
    }

    public double [] getCoefficients()
    {
        this.fx = new double[this.xs.length][this.xs.length];
        this.fx[0] = this.ys;
        int k = this.fx.length-1;

        return this.fx[0];
    }


    /*   k
            t(i,k)
     i



     */
    public double interpolate(double x){
        double res = this.fx[0][0];
        for (int i = 1; i< this.xs.length; i++){

        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new DividedDifferences(1,5,3,1,-2,-4,4,9.5).getCoefficients()));
    }

}
