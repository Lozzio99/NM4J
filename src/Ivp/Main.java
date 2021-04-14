package Ivp;

public class Main
{
    static Function f = (t, y) -> (1/Math.exp(t)) - (y*y) ;
    static double initialValue = 0;
    static double dt = 0.01;
    static double tf = 1;


    public static void main(String[] args) {

        Solver v = new Adam_Bashforth()
                .setInitialValue(initialValue)
                .setH(dt)
                .setFunction(f)
                .setTimeFinal(tf);

        v.solve();
        System.out.println(v.getW());
    }

}
