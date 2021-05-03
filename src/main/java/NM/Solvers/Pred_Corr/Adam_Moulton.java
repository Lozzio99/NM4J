package NM.Solvers.Pred_Corr;

import NM.Util.functions.fYT;
import NM.Solvers.RungeKutta.Runge_kutta3rd;
import NM.Solvers.Solver;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.NaN;

public class Adam_Moulton extends Solver
{

    public double w_1 = NaN, w1 = NaN , w2 = NaN;
    List<Double> w_t;

    public Adam_Moulton(){
        this.w_t = new ArrayList<>();
    }
    @Override
    public void solve()
    {

        System.out.print("t : " + this.t + " -> w : " + this.w);
        System.out.println(" f(w) :"+this.f.f_y(this.t,this.w));
        this.w1 = Runge_kutta3rd.RungeKutta3rdStep(this.f,this.w,this.h,this.t);
        this.w_t.add(this.w1);
        System.out.print("t : " + (float) (this.t+this.h) + " -> w : " + this.w1);
        System.out.println(" f(w) :"+this.f.f_y((this.t+h),this.w1));

        while((this.t+(2*this.h)) <= this.tf)
        {
            this.w2 = Adam_Bashforth.AdamBashforth2ndStep(this.f,this.w1,this.w,this.t+this.h,this.t,this.h);
            System.out.print("t : " + (float) (this.t+(2*h)) + " -> (predict) w : " + this.w2);
            System.out.println(" f(w) :"+this.f.f_y(this.t+(2*h),this.w2));
            this.w2 = AdamMoultoun2ndStep(this.f,this.w2,this.w1,this.w,this.t+this.h,this.h);
            System.out.println(" -> (correct) w: " + this.w2 + " f(w) :"+this.f.f_y(this.t+(2*h),this.w2));
            this.w = this.w1;
            this.w1 = this.w2;
            this.t+= this.h;
        }
        this.w_t.add(this.w1);

    }


    @Override
    public void step(double w, double h)
    {
        this.w = w+ h*( (5* this.f.f_y(t+h, w1)) + (8*this.f.f_y(t,w))- this.f.f_y(t-h,w_1))/12;
        this.w_1 = this.w;
    }

    public static double AdamMoultoun2ndStep (fYT f, double  w1, double w, double w_1, double t, double h )
    {
        double ft1w1 = f.f_y(t+h, w1);
        //System.out.println(" f(t1,w1) : "+ ft1w1);
        double ftw = f.f_y(t,w);
        //System.out.println(" f(t ,w ) : "+ ftw);
        double ft_1w_1 = f.f_y(t-h,w_1);
        double vnext = w+ h*( (5* ft1w1 ) + (8* ftw)- ft_1w_1)/12;
        System.out.println("\nCorrector : ");
        System.out.println(" f(t-1,w-1): "+ ft_1w_1);
        System.out.println(" §w (i+1) = w + 1/12 h * ( 5f(t1,w1) + 8f(t,w) - f(t-1,w-1) )");
        System.out.println(" 5f(t1,w1) = " + (5* ft1w1 ));
        System.out.println(" 8f(t,w)   = "+ (8* ftw));
        System.out.print(" §w (i+1) : ");
        System.out.println(vnext);
        return vnext;
    }

    public List<Double> getW_t() {
        return w_t;
    }
}
