package Solvers.Adaptive;

import Solvers.Solver;

public class Bogacki_Shampine extends Solver
{

    public double epsilon;

    public Bogacki_Shampine()
    {
        super();

    }

    public Solver setEpsilon(double epsilon){
        this.epsilon = epsilon;
        return this;
    }

    @Override
    public void step(double w, double h)
    {
        System.out.println("t : " + (float)this.t + " -> w : " + this.w);

        double k1 = h * this.f.f_y(this.t, w);
        double k2 = h * this.f.f_y(this.t+(h/2),w + (k1/2));
        double k3 = h * this.f.f_y(this.t + ((3*h)/4), w + ((3*k2/4)));
        double firstK = (2*k1)/9 + (3*k2)/9 + (4*k3)/9;
        double k4 = h * this.f.f_y(this.t+h, w+firstK);
        double secondK = ((7/24.)*k1) + ((6/24.)*k2) + ((8/24.)*k3) + ((3/24.)*k4);


        if (isAcceptableStep(firstK,secondK)){
            this.w = w+firstK;
            this.t += h;
            System.out.println("t : " + (float)this.t + " -> w : " + this.w);
        }else {
            double q = findQ(firstK,secondK,h);
            System.out.println("new h " + q*h);
            this.step(w,q*h);
        }

    }
    public boolean isAcceptableStep( double firstK, double secondK){
        return Math.abs(secondK-firstK)<this.epsilon;
    }
    public double findQ(double firstK, double secondK, double h){
        return Math.sqrt(     ((this.epsilon*h)/2)/ (  Math.abs(firstK-secondK)  )      ) ;
    }
}
