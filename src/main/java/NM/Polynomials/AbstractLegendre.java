package NM.Polynomials;

import NM.Integration.Simpsons;
import NM.Util.functions.fX;

import static java.lang.StrictMath.pow;


public abstract class AbstractLegendre
{
    protected fX[] p;
    protected double [] c;
    protected fX f,g;
    protected double x;
    protected double a,b;
    protected Simpsons integrator ;

    public AbstractLegendre(){}
    public AbstractLegendre(fX f, double a, double b)
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



    public void print() {
        StringBuilder s = new StringBuilder();
        s.append("P : [ ");
        for (int i = 0; i< this.p.length-1; i++){
            s.append(p[i].f_x(this.x)).append(" , ");
        }
        s.append(p[p.length-1].f_x(this.x)).append(" ]\n");
        s.append("C : [ ");
        for (int i = 0; i< this.c.length-1; i++){
            String cs = String.valueOf(c[i]).substring(0,7);
            s.append(cs).append(" , ");
        }
        String cs = String.valueOf(c[c.length-1]).substring(0,7);
        s.append(cs).append(" ]");
        System.out.println(s.toString().trim());
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





    public static void main(String[] args) {
        fX f = StrictMath::exp;



        for (int i = 1; i<= 5; i++){
            System.out.println("degree "+i+" : ");
            Legendre l2 = new Legendre(f,-1,1,i);
            l2.print();
            StringBuilder s = new StringBuilder();
            s.append("p(x) =");
            for (int k = 0; k<= i; k++)
            {
                String cs = String.valueOf(l2.c[k]).substring(0,7);
                s.append(" ").append(cs).append(" *P").append(k).append("x");
                if (k!= i)
                    s.append(" +");
            }

            System.out.println(s.toString().trim());
            System.out.println("square error : integral[-1,1]{ (fx-px)^2 }");
            fX g = l2.g();
            l2.integrator.setF(x-> pow(f.f_x(x) - g.f_x(x),2));

            System.out.println(l2.integrator.integrate());
            System.out.println();
        }

    }

}
