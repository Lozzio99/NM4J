package Roots;

import java.util.ArrayList;
import java.util.List;

public abstract class Root
{

    List<Double> p_stages;
    Function f;
    double epsilon;

    public Root(){
        this.p_stages = new ArrayList<>();
    }

    public Root setEpsilon(double eps){
        this.epsilon = eps;
        return this;
    }

    public Root setFunction(Function f){
        this.f = f;
        return this;
    }

    public abstract double findRoot(double a, double b);

    public abstract double findRoot(double p);

    public List<Double> getP_stages() {
        return p_stages;
    }
}
