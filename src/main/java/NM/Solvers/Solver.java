package NM.Solvers;

import NM.Util.functions.Fty;

import java.util.List;

import static java.lang.Double.NaN;
import static java.lang.StrictMath.floor;
import static java.lang.StrictMath.pow;

public abstract class Solver
{
    protected double h;
    protected double tf;
    protected static boolean PRINT_STEPS = false;
    protected double t;
    protected  int order = 0;
    protected double w = NaN;
    protected Fty<Double> f;

    /**
     * Constructor,
     * set starting time t = 0;
     */
    public Solver(Fty<Double> f) {
        this.f = f;
        this.t = 0;
    }

    /**
     * y(0) = k;
     * @param y0 the value of y0
     * @return the same solver
     */
    public Solver setInitialValue(double y0){
        this.w = y0;
        return this;
    }

    public Solver setInitialTime(float t0){
        this.t = t0;
        return this;
    }
    /**
     * dy/dt = f(t,y);
     * set the approximation level
     * @param h the stepSize
     * @return the same solver
     */
    public Solver setH(double h){
        this.h = h;
        return this;
    }


    /**
     * The upper bound for integration
     * @param tf the time final
     * @return the same solver
     */
    public Solver setTimeFinal(double tf){
        this.tf = tf;
        return this;
    }

    /**
     * Implements the solver step to
     * calculate the next state of f(t,y)
     */
    public void solve() {
        while((this.t + h )<= (this.tf))
        {
            //System.out.println("t : " + (float)this.t + " -> w : " + this.w);
            this.step(this.w,this.h);
            this.t += this.h;
        }
        if (PRINT_STEPS) System.out.println("t : " + (float) this.t + " -> w : " + this.w);
    }

    public abstract void step(double w, double h);


    public double getW() {
        return this.w;
    }

    public double getW(int dp) {
        //return floor(this.w*dp)/dp;
        return floor(w * pow(10, dp)) / pow(10, dp);
    }


    public Solver setOrder(int i) {
        this.order = i;
        return this;
    }

    public List<Double> getW_t() {
        return null;
    }
}
