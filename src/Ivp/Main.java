package Ivp;

import Ivp.RungeKutta.Ralston_s2nd;
import Ivp.RungeKutta.Runge_kutta3rd;
import Ivp.RungeKutta.Runge_kutta4th;

public class Main
{
    static Function f = (t, y) -> ((y+t)*(y+t))-1 ;
    //static Function f = (t,y)-> (y/t) -2;

    static double initialValue = 2/3.;

    static float initialTime = 0;
    static double dt = 0.25;
    static double tf = 1;


    public static void main(String[] args) {

        System.out.println("h :: "+dt);
        System.out.println("Ralston 2nd order : ");
        Solver v = new Ralston_s2nd()
                .setInitialValue(initialValue)
                .setInitialTime(initialTime)
                .setH(dt)
                .setFunction(f)
                .setTimeFinal(tf);
        v.solve();

        /*
        System.out.println("Adam BashForth 2nd: ");
        v = new Adam_Bashforth()
                .setInitialValue(initialValue)
                .setInitialTime(initialTime)
                .setH(dt)
                .setFunction(f)
                .setTimeFinal(tf)
                .setOrder(2);
        v.solve();
        System.out.println("Adam BashForth 3rd: ");
        v = new Adam_Bashforth()
                .setInitialValue(initialValue)
                .setInitialTime(initialTime)
                .setH(dt)
                .setFunction(f)
                .setTimeFinal(tf)
                .setOrder(3);
        v.solve();
        System.out.println("Adam BashForth 4th: ");
        v = new Adam_Bashforth()
                .setInitialValue(initialValue)
                .setInitialTime(initialTime)
                .setH(dt)
                .setFunction(f)
                .setTimeFinal(tf)
                .setOrder(4);
        v.solve();
        System.out.println("RK 3rd order : ");
        v = new Runge_kutta3rd()
                .setInitialValue(initialValue)
                .setInitialTime(initialTime)
                .setH(dt)
                .setFunction(f)
                .setTimeFinal(tf);
        v.solve();



         */
        System.out.println("RK 4th order : ");
        v = new Runge_kutta4th()
                .setInitialValue(initialValue)
                .setInitialTime(initialTime)
                .setH(dt)
                .setFunction(f)
                .setTimeFinal(tf);
        v.solve();


        /*
        System.out.println("Euler : ");
        v = new EulerSolver()
                .setInitialValue(initialValue)
                .setInitialTime(initialTime)
                .setH(dt)
                .setFunction(f)
                .setTimeFinal(tf);
        v.solve();
        System.out.println("Trapezoid Euler : ");
        v = new TrapezoidEuler()
                .setInitialValue(initialValue)
                .setInitialTime(initialTime)
                .setH(dt)
                .setFunction(f)
                .setTimeFinal(tf);
        v.solve();
        System.out.println("MidPoint Euler : ");
        v = new MidPointEuler()
                .setInitialValue(initialValue)
                .setInitialTime(initialTime)
                .setH(dt)
                .setFunction(f)
                .setTimeFinal(tf);
        v.solve();
         */

    }

}
