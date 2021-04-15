package Ivp;

import Ivp.RungeKutta.Ralston_s2nd;
import Ivp.RungeKutta.Runge_kutta3rd;
import Ivp.RungeKutta.Runge_kutta4th;

import static java.lang.Double.NaN;

public class Adam_Bashforth extends Solver
{

    public double w_1 = NaN,w_2 = NaN,w_3 = NaN;

    @Override
    public void solve()
    {
        switch (order){
            case 2 -> {
                this.w_1 = this.w;
                this.w = Ralston_s2nd.Ralston2ndOrderStep(this.f, this.w, this.h, this.t);
                this.t += h;
            }
            case 3 -> {
                this.w_2 = this.w_1 = this.w;
                this.w_2 = Runge_kutta3rd.RungeKutta3rdStep(this.f, this.w, this.h, this.t);
                this.w_1 = Runge_kutta3rd.RungeKutta3rdStep(this.f,  this.w_2, this.h,this.t+this.h);
                this.w   = Runge_kutta3rd.RungeKutta3rdStep(this.f,  this.w_1, this.h,this.t+ (2*this.h));
                this.t += (this.h*2);
            }
            case 4 -> {
                this.w_3 = this.w_2 = this.w_1 = this.w;
                this.w_3 = Runge_kutta4th.RungeKutta4thStep(this.f, this.t, this.w, this.h);
                this.w_2 = Runge_kutta4th.RungeKutta4thStep(this.f, this.t+this.h, this.w_3, this.h);
                this.w_1 = Runge_kutta4th.RungeKutta4thStep(this.f, this.t+(this.h *2), this.w_2, this.h);
                this.w = Runge_kutta4th.RungeKutta4thStep(this.f, this.t+(this.h * 3), this.w_1, this.h);
                this.t += (this.h * 3);
            }
            default -> throw new RuntimeException("For Adam Bashforth order is necessary to be set, allowed ones are : [2,3,4]");
        }
        super.solve();
    }


    @Override
    public void step(double w, double h)
    {
        switch (order)
        {
            case 2 ->   this.w = w + ((h/2)*(3*this.f.f_y(t,w)-this.f.f_y(t-h, w_1)));

            case 3 ->{
                double t_1 = this.t-h;
                double t_2 = this.t-(2*h);
                this.w = w + h*( (23 * this.f.f_y(t,w))- (16 *this.f.f_y(t_1,w_1)) + (5*this.f.f_y(t_2,w_2)) )/12;
                this.w_1 = this.w;
                this.w_2 = this.w_1;
            }
            case 4 -> {
                double t_1 = this.t-h;
                double t_2 = this.t-(2*h);
                double t_3 = this.t-(3*h);
                this.w = w + (1/24.)*h*(55*f.f_y(this.t,w)- 59*f.f_y(t_1,w_1) + 37*f.f_y(t_2,w_2)- 9*f.f_y(t_3,w_3));
                //shift everything to the next i
                w_1 = w;
                w_2 = w_1;
                w_3 = w_2;
            }
        }

    }
}
