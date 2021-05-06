package NM.Polynomials;

import NM.Graph.Plot;
import NM.Integration.Simpsons;
import NM.Util.functions.fX;
import NM.Util.functions.function;

import static java.lang.Math.exp;
import static java.lang.Math.pow;


public class Legendre
{
    private fX[] p;
    private double [] c;
    private fX f;
    private double x;
    private double a,b;
    private Simpsons integrator ;

    public Legendre( fX f, double a, double b)
    {
        this.f = f;
        this.a = a;
        this.b = b;
        integrator= new Simpsons(20,this.f, a,b);
        fitPs();
        fitCs();
    }

    private void fitCs()
    {
        this.c = new double [5];
        c[0] =  .5 * integrator.integrate();
        integrator.setF((x)-> f.f_x(x) * p[1].f_x(x) );
        c[1] = 1.5 * integrator.integrate();
        integrator.setF((x)-> f.f_x(x) * p[2].f_x(x));
        c[2] = 2.5 * integrator.integrate();
        integrator.setF((x)-> f.f_x(x) * p[3].f_x(x));
        c[3] = 3.5 * integrator.integrate();
        integrator.setF((x)-> f.f_x(x) * p[4].f_x(x));
        c[4] =  4.5 * integrator.integrate();
    }


    private void fitPs()
    {
        this.p = new fX[5];
        p[0] = (x)->1;
        p[1] = (x)->x;
        p[2] = (x)->(3/2.)*x * p[1].f_x(x)- (1/2.)* p[0].f_x(x);
        p[3] = (x)->(5/3.)*x * p[2].f_x(x)- (2/3.)* p[1].f_x(x);
        p[4] = (x)->(7/4.)*x * p[3].f_x(x)- (3/4.)* p[2].f_x(x);
    }

    private void print() {
        System.out.print(" P : [");
        for (fX p : this.p){
            System.out.print(p.f_x(this.x) + " , ");
        }
        System.out.println(" ]");
        System.out.print(" C : [");
        for (double c : this.c){
            System.out.print(c + " , ");
        }
        System.out.println(" ]");
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public double[] getC() {
        return c;
    }

    public fX[] getP() {
        return p;
    }

    public double g(double x) {
        double sum = 0;
        //System.out.println();
        for (int i =0; i< 5; i++){
            sum+= this.c[i] * this.p[i].f_x(x);
            //System.out.println(this.c[i] * this.p[i].f_x(x));
        }
        //System.out.println("sum ->");
        return sum;
    }

    public double [] g(double []x ){
        double [] g = new double [x.length];
        for (int i = 0; i< g.length; i++)
            g[i] = g(x[i]);
        return g;
    }


    public static void main(String[] args) {

        fX f = (x)-> pow(x,3);
        fX f2 = Math::exp;
        fX f3 = (x)->1;
        Legendre l = new Legendre(f3,-1,1);

        l.print();
        System.out.println(l.g(1));


    }

}
