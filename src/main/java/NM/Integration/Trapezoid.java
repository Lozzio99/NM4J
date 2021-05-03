package NM.Integration;
import NM.Util.functions.fX;

public class Trapezoid extends Integrator
{
    public Trapezoid(int nodes, fX f, double ... xs){
        super(f,xs);
        this.nodes = xs.length-1;
    }

    public Trapezoid(fX f, double... xs){
        super(f,xs);
        nodes = xs.length-1;
    }

    @Override
    public double integrate(int a, int b)
    {
       return 0;
    }

    public double integrate() {
        double integral = 0;
        for (int i = 0; i< nodes; i++) {
            integral+= step(xs[i],xs[i+1]);
            System.out.println(xs[i] + " - > " + f.f_x(xs[i]));
        }
        System.out.println(xs[xs.length-1] + " - > " + f.f_x(xs[xs.length-1]));

        return integral;
    }

    public double step(double a, double b){
        return (b-a) * ((f.f_x(a) + f.f_x(b))/2);
    }
}
