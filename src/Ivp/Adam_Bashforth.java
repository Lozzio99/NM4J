package Ivp;

import Ivp.RungeKutta.Runge_kutta4th;

import static java.lang.Double.NaN;

public class Adam_Bashforth extends Solver
{

    public static int order = 4;
    public double w_1 = NaN,w_2 = NaN,w_3 = NaN;
    @Override
    public void solve(){


        switch (order){
            case 2 -> this.w = Ralston_s.Ralston2ndOrderStep(this.f, this.w, this.h, this.t);
            case 3 -> System.out.println("todo");
            case 4 -> this.w = Runge_kutta4th.RungeKutta4thStep(this.f, this.t, this.w, this.h);
        }
        w_1 = w;
        while(this.t<this.tf)
        {
            this.step(this.w,this.h);
            System.out.println(this.w);
            this.t+= this.h;
        }

    }


    @Override
    public void step(double w, double h) {
        switch (order){
            case 2 ->{
                this.w = w + ((h/2)*(3*this.f.f_y(t,w)-this.f.f_y(t-h, w_1)));
                this.w_1 = w;
            }
            case 3 ->{

            }
            case 4 -> {
                double t_1 = this.t-h;
                double t_2 = this.t-(2*h);
                double t_3 = this.t-(3*h);
                this.w = w + (1/24.)*h*(55*f.f_y(this.t,w)- 59*f.f_y(t_1,w_1) + 37*f.f_y(t_2,w_2)- 9*f.f_y(t_3,w_3));
                w_1 = w;
                w_2 = w_1;
                w_3 = w_2;
            }
        }

    }
}
