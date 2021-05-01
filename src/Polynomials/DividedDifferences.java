package Polynomials;

import Graph.Plot;

import java.util.Arrays;

import static java.lang.Double.NaN;

public class DividedDifferences
{
    double xs[];
    double ys[];
    double fx[][];
    public DividedDifferences (double []xs, double [] ys)
    {
        this.xs = xs;
        this.ys = ys;
    }
    public DividedDifferences(double... x_y)
    {
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

    public double [][] getTable()
    {
        this.fx = new double[this.xs.length][this.xs.length];
        for (double [] c : fx)
            Arrays.fill(c,NaN);
        this.fx[0] = this.ys;
        for (int i = 1; i<this.xs.length; i++){
            int left = i-1;
            for (int k = 0; k< this.xs.length-i; k++){
                this.fx[i][k] = (this.fx[i-1][k+1] - this.fx[i-1][k])/(this.xs[i+k]-this.xs[k]);
            }
        }
        return this.fx;
    }


    public double interpolate(double x){
        double res = this.fx[0][0];
        for (int i = 1; i< this.xs.length; i++){

        }
        return res;
    }

    public void printTable()
    {
        this.fx = this.getTable();
        for (int i = 0; i< this.fx[0].length; i++){
            System.out.print(this.xs[i] + " | ");
            for (int k = 0; k<this.fx.length; k++){
                if (this.fx[k][i]>=0)
                    System.out.print(" ");
                System.out.print((float) this.fx[k][i]  + " | ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        //new DividedDifferences(1,5,3,1,-2,-4,4,9.5).printTable();
        new DividedDifferences(1,0.3,-4,1.3,0,-2.3).printTable();
       // new DividedDifferences(2.5,0.68,2.0,0.66,3.0,0.9,1.5,0.79,3.5,1.31,1,1.02,4.0,1.77,0.5,1.3,4.5,1.87,0,1.5,5,1.33).printTable();

    }

}
