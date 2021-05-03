package NM.Solvers.Adaptive;

import NM.Util.functions.fYT;
import NM.Solvers.RungeKutta.Runge_kutta4th;
import NM.Solvers.Solver;

public class Main
{
    public static void main(String[] args) {

        fYT function = (t, y)->  ((Math.exp(t))*Math.sqrt(3- Math.pow(y,2))) ;


        Solver s = new Bogacki_Shampine().setEpsilon(0.001).setFunction(function).setH(1).setInitialValue(1).setInitialTime(0).setTimeFinal(1);
        s.step(s.getW(),0.5);
        System.out.println(s.getW());
        //i was doing step once
        s.step(s.getW(),0.2);
        System.out.println("Bogacki ::::");
        System.out.println(s.getW());


        Solver rk = new Runge_kutta4th().setFunction(function).setH(0.0001).setInitialTime(0).setInitialValue(1).setTimeFinal(0.094598755);
        rk.solve();
        System.out.println("Runge kutta ::::");
        System.out.println(rk.getW());


    }
}
