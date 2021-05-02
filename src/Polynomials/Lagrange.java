package Polynomials;

import Util.functions.fX;

public class Lagrange extends Interpolation
{



    public Lagrange(fX f, double... x)
    {
       super(f,x);
    }



    @Override
    public double polynomial(double x)
    {
        double sum = 0;
        for (int i = 0; i< this.ys.length;i++){
            sum+= this.interpolate(x,i);
        }
        return sum;
    }

    public double findBasis(double x, int basis_I)
    {
        double numProd = 1;
        double denProd = 1;
        for (int i = 0; i< this.xs.length; i++)
        {
            if (i != basis_I)
            {
                numProd*= (x - this.xs[i]);
                denProd*= (this.xs[basis_I] - this.xs[i]);
            }
        }
        return numProd/denProd;
    }

    public double [] interpolate()
    {
        double [] values = new double[this.xs.length];
        for (int i = 0; i< this.xs.length; i++) {
            values[i] = interpolate(this.xs[i],i);
        }
        return values;
    }

    public double interpolate(double x,int I)
    {
        return this.ys[I] * findBasis(x,I) ;
    }

}
