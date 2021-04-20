import Ivp.Function;
import Ivp.Solvers.Pred_Corr.Adam_Moultoun;
import Ivp.Solvers.Solver;
import Error.Error;
import java.util.List;

import static java.lang.Double.NaN;

public class Main
{

/*
    //slides
    static Function f = (t,y)->  ( 1/Math.exp(t) ) -Math.pow(y,2) ;


    assignment 1
    static Function f = (t, y) -> {
        t = (float) t;
        return Math.sin(t) +y - Math.pow(y,3) ;
    };
*/


    /* hc4
     */
    static Function f = (t,y)->{
        t = (float) t;
        return Math.pow(y+t,2)-1 ;
    };




    //hc4
    static double initialValue = 2/3.;

    static float initialTime = 0;
    static double dt = 0.5;
    static double tf = 1.;

    public static void main(String[] args) {



        Roots.Function originalF = (t)-> (2/(3-(2*t)))-t;


        System.out.print("Bashforth-Moulton Predictor-corrector 2nd");
        System.out.println("    h :: "+ dt + "\n");
        Solver v = new Adam_Moultoun()
                .setInitialValue(initialValue)
                .setTimeFinal(tf)
                .setH(dt)
                .setFunction(f)
                .setInitialTime(initialTime);
        v.solve();
        List<Double> w05 = v.getW_t();

        System.out.println(" step 0.5 -> ");
        error(originalF, w05, 0.5,1);
        dt = 0.25;


        System.out.print("Bashforth-Moulton Predictor-corrector 2nd");
        System.out.println("    h :: "+ dt + " \n");
        v = new Adam_Moultoun()
                .setInitialValue(initialValue)
                .setTimeFinal(tf)
                .setH(dt)
                .setFunction(f)
                .setInitialTime(initialTime);
        v.solve();
        List<Double> w025 = v.getW_t();

        System.out.println(" step 0.25 -> ");
        error(originalF, w025, 0.55,1);


        /*

         EX assignment 1


        Solver v = new Adam_Bashforth()
                .setOrder(3)
                .setInitialValue(initialValue)
                .setInitialTime(initialTime)
                .setH(dt)
                .setFunction(f)
                .setTimeFinal(tf);
        v.solve();


        double w = v.getW();
        System.out.print("abs error t6\t");
        System.out.println(Error.absoluteError(-0.659969302,w));
        System.out.print("rel error t6\t");
        System.out.println(Error.relativeError(-0.659969302,w));


        v = new Adam_Bashforth()
                .setOrder(3)
                .setInitialValue(initialValue)
                .setInitialTime(initialTime)
                .setH(dt/2)
                .setFunction(f)
                .setTimeFinal(tf);
        v.solve();


        w = v.getW();
        System.out.print("abs error t6\t");
        System.out.println(Error.absoluteError(-0.659969302,w));
        System.out.print("rel error t6\t");
        System.out.println(Error.relativeError(-0.659969302,w));


        tf = 5.2;
        v = new Adam_Bashforth()
                .setOrder(3)
                .setInitialValue(initialValue)
                .setInitialTime(initialTime)
                .setH(dt/2)
                .setFunction(f)
                .setTimeFinal(tf);
        v.solve();
        w = v.getW();
        v.step(w,dt);
        double w2 = v.getW();
        System.out.println(w);
        System.out.println(w2);



        double root = Secant.findSpecialRoot(f,5.2,5.25,w,w2);
        System.out.println(root);
        System.out.println( " 5.2 ->  +h = " + (root - 5.2 ) + "  == "+ root);
*/




/*
        System.out.println("h :: "+dt);
        System.out.println("Ralston 2nd order : ");
        Solver v = new Ralston_s2nd()
                .setInitialValue(initialValue)
                .setInitialTime(initialTime)
                .setH(dt)
                .setFunction(f)
                .setTimeFinal(tf);
        v.solve();


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

        System.out.println("RK 4th order : ");
        v = new Runge_kutta4th()
                .setInitialValue(initialValue)
                .setInitialTime(initialTime)
                .setH(dt)
                .setFunction(f)
                .setTimeFinal(tf);
        v.solve();



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

    private static void error(Roots.Function originalF, List<Double> w05, double exp1,double exp2)
    {
        System.out.println(w05 +" (first and last step) ");
        System.out.println(" expected -> "+ exp1);
        System.out.println("Relative error : " + Error.relativeError(exp1,w05.get(0)));
        System.out.println("Absolute Error : " + Error.absoluteError(exp1,w05.get(0)));
        System.out.println(" step 1.0 -> "+ exp2);
        System.out.println("Relative error : " + Error.relativeError(exp2,w05.get(1)));
        System.out.println("Absolute Error : " + Error.absoluteError(exp2,w05.get(1)));
    }

}
