package NM.Fourier;

import NM.Integration.AdaptiveSimpsons;
import NM.Polynomials.Interpolation;
import NM.Util.functions.fX;

import static java.lang.StrictMath.*;


public class FourierApprox
{
    private fX f;
    protected fX s;
    private int n;
    protected double []a,b;
    private AdaptiveSimpsons integrator ;

    public FourierApprox(){}
    public FourierApprox(fX f, int n){
        this.f = f;
        this.n = n;
        this.a = new double [n+1];
        this.b = new double[n+1];
        this.integrator = new AdaptiveSimpsons(f);
        {
            double [] xs = Interpolation.linX(-10,10,30);
            boolean even = true,odd = true;
            for (double x : xs){
                if (f.f_x(-x) != f.f_x(x)) { even = false; }
                if (f.f_x(-x) != -f.f_x(x)) odd = false;
            }
            if (even)
                this.computeEvenFunction();
            else if (odd)
                this.computeOddFunction();
            else
                this.compute();
        }
    }

    /**
     * Here f is an even function
     */
    private void compute() {
        this.a[0] =(1/PI) * this.integrator.nRule(-PI,PI);
        for (int i = 1; i< this.a.length; i++){
            final int I = i;
            fX f1 = (x)-> f.f_x(x) * cos(I * x);
            fX f2 = (x)-> f.f_x(x) * sin(I * x);
            this.integrator.setF(f1);
            this.a[i] =(1/PI) * this.integrator.nRule(-PI,PI);
            this.integrator.setF(f2);
            this.b[i] =(1/PI) * this.integrator.nRule(-PI,PI);
        }
        this.makeS();
    }

    protected void makeS() {
        this.s = (x)-> {
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

    public fX getS() {
        return s;
    }

    private void computeOddFunction(){
        for (int i = 0; i< this.a.length; i++){
            this.a[i] = 0;
            int k = i;
            this.integrator.setF(x -> this.f.f_x(x)* sin(k *x));
            this.b[i] = (2/PI) *this.integrator.nRule(0,PI);
        }
        this.makeS();
    }



    private void computeEvenFunction(){

        for (int i = 0; i< this.a.length; i++){
            int k = i;
            this.integrator.setF(x -> this.f.f_x(x)* cos(k *x));
            this.a[i] = (2/PI) *this.integrator.nRule(0,PI);
            this.b[i] = 0;
        }
        this.makeS();
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
