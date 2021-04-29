package Integration;
import functions.fX;

public class Trapezoid extends Integrator
{
    public Trapezoid(int nodes, fX f, double ... xs){
        super(f,xs);
        this.nodes = nodes;
    }

    @Override
    public double integrate(int a, int b)
    {
       return 0;
    }

    public double step(double a, double b){
        return (b-a) * ((f.f_x(a) + f.f_x(b))/2);
    }
}
