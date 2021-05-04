package NM.Util.Exercises;

import NM.Integration.Integrator;
import NM.Integration.Midpoint;
import NM.Integration.Simpsons;
import NM.Integration.Trapezoid;
import NM.Solvers.Solver;
import NM.Util.functions.fX;

import static java.lang.Math.exp;
import static java.lang.Math.pow;

public class Exercise4_7c
{
    public static void main(String[] args) {
        fX f = (x)-> exp((-pow(x,2))/2);
        Integrator integrator = null;
        int n = 8;
        double a = 0,b = 1, i = 0;
        integrator = new Midpoint(n,f,a,b);
        i = integrator.nRule(a,b);
        System.out.println(" MIDPOINT : " + i);
        System.out.println("/-----------/");
        integrator = new Trapezoid(n,f,a,b);
        i = integrator.nRule(a,b);
        System.out.println(" TRAPEZOID : "+ i);
        System.out.println("/-----------/");
        integrator = new Simpsons(n,f,a,b);
        i = integrator.nRule(a,b);
        System.out.println(" SIMPSONS : "+i);
    }
}
