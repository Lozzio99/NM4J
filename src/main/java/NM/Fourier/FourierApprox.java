package NM.Fourier;

import NM.Integration.AdaptiveSimpsons;
import NM.Util.functions.fX;

import static java.lang.Math.*;

public class FourierApprox
{
    private fX f, s;
    private int n;
    private double []a,b;
    private AdaptiveSimpsons integrator ;
    public FourierApprox(fX f, int n){
        this.f = f;
        this.n = n;
        this.a = new double [n+1];
        this.b = new double[n+1];
        this.integrator = new AdaptiveSimpsons(f);
        this.compute();
    }

    private void compute() {
        this.a[0] =(1/PI) * this.integrator.integrate(-PI,PI);
        for (int i = 1; i< this.a.length; i++){
            final int I = i;
            fX f1 = (x)-> f.f_x(x) * cos(I * x);
            fX f2 = (x)-> f.f_x(x) * sin(I * x);
            this.integrator.setF(f1);
            this.a[i] =(1/PI) * this.integrator.integrate(-PI,PI);
            this.integrator.setF(f2);
            this.b[i] =(1/PI) * this.integrator.integrate(-PI,PI);
        }
        s = (x)-> {
            double sum = this.a[0];
            int k = 1;
            for (int i = 1;i< this.a.length; i++){
                sum+= this.a[i]*cos(k*x);
                sum+= this.b[i]*sin(k*x);
                k++;
            }
            return sum;
        };

    }

    public double s(double x){
        return this.s.f_x(x);
    }

    public static void main(String[] args) {
        fX f =( x)-> sin(2*x) /( 3 + sin(x)+ 2*cos(x));
        System.out.println(new FourierApprox(f,5).s(10));
        System.out.println(f.f_x(10));

    }
}
