package NM.Roots;

import NM.Util.functions.Fx;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.NaN;
import static java.lang.StrictMath.floor;
import static java.lang.StrictMath.pow;

public abstract class Root {

    protected List<Double> p_stages;
    protected Fx<Double> f;
    protected double root = NaN;
    protected double epsilon;

    public Root(Fx<Double> f) {
        this.f = f;
        this.p_stages = new ArrayList<>();
    }

    public Root setEpsilon(double eps) {
        this.epsilon = eps;
        return this;
    }

    public abstract double findRoot(double a, double b);

    public abstract double findRoot(double p);

    public List<Double> getP_stages() {
        return p_stages;
    }

    public double getRoot(int dp) {
        if (Double.valueOf(root).equals(NaN))
            throw new IllegalStateException("must first evaluate the root");
        return floor(root * pow(10, dp)) / pow(10, dp);

    }
}
