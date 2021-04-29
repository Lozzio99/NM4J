package Integration;
import functions.fX;

public class Trapezoid extends Integrator
{
    public Trapezoid(int nodes, fX f, double ... xs){
        super(f,xs);
        this.nodes = nodes;
    }

    @Override
    public double integrate(int a, int b) {
        double h =((double)(b-a))/this.nodes;
        double x0 = this.f.f_x(a), xN = this.f.f_x(b), xi = a;
        double xk[] = new double[this.nodes-1];
        for (int i = 0; i< xk.length; i++) {
            xi += h;
            xk[i] = xi;
        }
        double integral = 0;

        return 0;
    }
}
