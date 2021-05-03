package NM.Roots;

public class Newton extends Root
{

    @Override
    public double findRoot(double a,double b)
    {
        return 0;
    }

    @Override
    public double findRoot(double p) {
        double p1 = p- (f.f_x(p)/ Derivative.derive(f,p));
        this.p_stages.add(p1);
        if (Math.abs(p1-p) >epsilon)
            return findRoot(p1);
        return p1;
    }
}
