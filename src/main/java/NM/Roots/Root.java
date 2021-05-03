package NM.Roots;

import NM.Util.functions.fX;

import java.util.ArrayList;
import java.util.List;

public abstract class Root
{

    protected List<Double> p_stages;
    protected fX f;
    protected double epsilon;

    public Root(){
        this.p_stages = new ArrayList<>();
    }

    public Root setEpsilon(double eps){
        this.epsilon = eps;
        return this;
    }

    public Root setFunction(fX f){
        this.f = f;
        return this;
    }

    public abstract double findRoot(double a, double b);

    public abstract double findRoot(double p);

    public List<Double> getP_stages() {
        return p_stages;
    }
}
