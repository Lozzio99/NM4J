package Ivp;

import static java.lang.Double.NaN;

public abstract class Solver
{
    protected double h;
    protected double tf;
    protected Function f;
    protected double t;

    protected double w = NaN;

    /**
     * Constructor,
     * set starting time t = 0;
     */
    public Solver(){
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
     * f(t,y) -> {  }
     * @param f the function to analyse
     * @return the same solver
     */
    public Solver setFunction(Function f){
        this.f = f;
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
        while(this.t<= this.tf)
        {
            this.step(this.w,this.h);
            this.t += this.h;
        }
    }

    public abstract void step(double w, double h);


    public double getW(){
        return this.w;
    }


}
