package Exercises;

import Graph.Plot;
import Polynomials.DividedDifferences;

public class Assignment2
{
    public static void main(String[] args)
    {
        double [] xs = new double[]{ 2.5,2.0,3.0,1.5,3.5,1.,4.,.5,4.5,0.,5. };
        double [] ys = new double[]{ 0.68,0.66,0.9,0.79,1.31,1.02,1.77,1.30,1.87,1.50,1.33};
        double [] y_s = new double[]{ 0.7,0.74,0.94,.79,1.34,1.05,1.78,1.33,1.87,1.49,1.31};
        new DividedDifferences(xs,ys);
        Plot p = new Plot();
        p.scale(90);
        p.translate(-240,100);
        //p.plot(xs,ys);
        p.fit(xs,ys);
       // p.plot2(xs,y_s);
    }
}
