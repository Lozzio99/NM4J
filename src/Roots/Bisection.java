package Roots;

public class Bisection extends Root
{

    @Override
    public double findRoot(double a , double b){

        while((b-a)/2>epsilon)
        {
            double c = (a+b)/2;
            this.p_stages.add(c);
            if (Math.signum(f.f_x(c))== Math.signum(f.f_x(a)))
                a = c;
            else
                b = c;
        }
        return (a+b)/2;
    }

    @Override
    public double findRoot(double p) {
        return 0;
    }


}
