package NM.Solvers;

public class Heun_s3rd extends Solver
{

    @Override
    public void step(double w, double h) {
        double k1 = h * this.f.f_y(t,w);
        double k2 = h * this.f.f_y(t + (h/3),w+ (k1/3));
        double k3 = h * this.f.f_y(t + (h/3*2),w + (k2/3*2));
        this.w = w + (k1 + (3*k3))/4;
    }
}
