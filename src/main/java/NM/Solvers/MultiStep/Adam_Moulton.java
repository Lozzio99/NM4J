package NM.Solvers.MultiStep;

import NM.Solvers.RungeKutta.Runge_kutta3rd;
import NM.Solvers.RungeKutta.Runge_kutta4th;
import NM.Solvers.Solver;
import NM.Util.functions.Fty;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.NaN;

public class Adam_Moulton extends Solver {

    public double w_1 = NaN, w1 = NaN, w2 = NaN, w3 = NaN;
    List<Double> w_t;

    public Adam_Moulton(Fty<Double> f) {
        super(f);
        this.w_t = new ArrayList<>();
    }

    public static double AdamMoultoun2ndStep(Fty<Double> f, double w1, double w, double w_1, double t, double h) {
        double ft1w1 = f.f(t + h, w1);
        //System.out.println(" f(t1,w1) : "+ ft1w1);
        double ftw = f.f(t, w);
        //System.out.println(" f(t ,w ) : "+ ftw);
        double ft_1w_1 = f.f(t - h, w_1);
        double vnext = w + h * ((5 * ft1w1) + (8 * ftw) - ft_1w_1) / 12;
        if (PRINT_STEPS) {
            System.out.println("\nCorrector : ");
            System.out.println(" f(t-1,w-1): " + ft_1w_1);
            System.out.println(" §w (i+1) = w + 1/12 h * ( 5f(t1,w1) + 8f(t,w) - f(t-1,w-1) )");
            System.out.println(" 5f(t1,w1) = " + (5 * ft1w1));
            System.out.println(" 8f(t,w)   = " + (8 * ftw));
            System.out.print(" §w (i+1) : ");
            System.out.println(vnext);
        }
        return vnext;
    }

    public static double AdamMoultoun3rdStep(Fty<Double> f, double w1, double w, double w_1, double w_2, double t, double h) {
        double t1 = t - h, t2 = t1 - h;
        double k = (5 * f.f(t1, w_1)) + f.f(t2, w_2);
        double k1 = (9 * f.f(t + h, w1)) + (19 * f.f(t, w));
        return w + ((h / 24) * (k1 - k));
    }

    @Override
    public void solve() {

        if (PRINT_STEPS) {
            System.out.print("t : " + this.t + " -> w : " + this.w);
            System.out.println(" f(w) :" + this.f.f(this.t, this.w));
        }

        this.w1 = Runge_kutta3rd.RungeKutta3rdStep(this.f, this.w, this.h, this.t);
        this.w_t.add(this.w1);
        if (PRINT_STEPS) {
            System.out.print("t : " + (float) (this.t + this.h) + " -> w : " + this.w1);
            System.out.println(" f(w) :" + this.f.f((this.t + h), this.w1));
        }
        while ((this.t + (this.order * this.h)) <= this.tf) {
            switch (this.order) {
                case 2 -> {
                    this.w2 = Adam_Bashforth.AdamBashforth2ndStep(this.f, this.w1, this.w, this.t + this.h, this.t, this.h);
                    if (PRINT_STEPS) {
                        System.out.print("t : " + (float) (this.t + (2 * h)) + " -> (predict) w : " + this.w2);
                        System.out.println(" f(w) :" + this.f.f(this.t + (2 * h), this.w2));
                    }
                    this.w2 = AdamMoultoun2ndStep(this.f, this.w2, this.w1, this.w, this.t + this.h, this.h);
                    if (PRINT_STEPS)
                        System.out.println(" -> (correct) w: " + this.w2 + " f(w) :" + this.f.f(this.t + (2 * h), this.w2));
                    this.w = this.w1;
                    this.w1 = this.w2;
                    this.t += this.h;
                }
                case 3 -> {
                    this.w2 = Runge_kutta4th.RungeKutta4thStep(f, t + h, w1, h);
                    this.w3 = Adam_Bashforth.AdamBashforth3rdStep(f, w2, w1, w, t, h);
                    this.w3 = AdamMoultoun3rdStep(f, w3, w2, w1, w, t, h);
                    this.w = this.w1;
                    if (PRINT_STEPS) System.out.println("t : " + (t + (3 * h)) + " w -> " + w3);
                    this.w1 = this.w2;
                    this.w2 = this.w3;
                    this.t += h;
                }
            }
        }
        this.w_t.add(this.w1);
        if (PRINT_STEPS) {
            System.out.println("t : " + (t + h) + " w -> " + w1);
            System.out.println("t : " + (t + h + h) + " w -> " + w3);
        }
        this.w = this.order == 0 ? this.w : (this.order == 2 ? this.w2 : this.w3);

    }

    @Override
    public void step(double w, double h) {
        this.w = w + h * ((5 * this.f.f(t + h, w1)) + (8 * this.f.f(t, w)) - this.f.f(t - h, w_1)) / 12;
        this.w_1 = this.w;
    }


    public List<Double> getW_t() {
        return w_t;
    }
}
