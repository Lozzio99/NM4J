package NM.Util.Exercises;

import NM.Fourier.FourierApprox;
import NM.Graph.Plot;
import NM.Polynomials.Interpolation;
import NM.Util.functions.Fx;

import javax.swing.*;

import static java.lang.Math.PI;
import static java.lang.StrictMath.cos;
import static java.lang.StrictMath.sin;

public class Assignment3
{
    public static void main(String[] args) {
        Fx<Double> f = x -> cos(2 * x) / (3 + (2 * sin(x)));
        double a = -PI, b = -a;
        FourierApprox fourier1 = new FourierApprox(f, 1), fourier2 = new FourierApprox(f, 2);
        FourierApprox fourier6 = new FourierApprox(f, 6), fourier3 = new FourierApprox(f, 3);
        FourierApprox fourier10 = new FourierApprox(f, 10), fourier120 = new FourierApprox(f, 120);
        Plot p = new Plot(), p2 = new Plot(), p3 = new Plot();
        ((JFrame) p.getParent().getParent().getParent().getParent()).setTitle("Fourier 1,2");
        p.getParent().getParent().getParent().getParent().setLocation(0, 200);
        ((JFrame) p2.getParent().getParent().getParent().getParent()).setTitle("Fourier 3,6");
        p2.getParent().getParent().getParent().getParent().setLocation(520, 200);
        ((JFrame) p3.getParent().getParent().getParent().getParent()).setTitle("Fourier 10,120");
        p3.getParent().getParent().getParent().getParent().setLocation(1040, 200);
        double[] xs = Interpolation.linX(-10 * PI, 10 * PI, 700);
        double[] ys = new double[700],
                ys2 = new double[700],
                ys3 = new double[700],
                ys6 = new double[700],
                ys10 = new double[700],
                ys120 = new double[700];
        for (int i = 0; i < ys.length; i++) {
            ys[i] = fourier1.s(xs[i]);
            ys2[i] = fourier2.s(xs[i]);
            ys3[i] = fourier3.s(xs[i]);
            ys6[i] = fourier6.s(xs[i]);
            ys10[i] = fourier10.s(xs[i]);
            ys120[i] = fourier120.s(xs[i]);
        }
        p.scale(10);
        p.plot(xs, ys);
        p.plot2(xs,ys2);
        p2.plot(xs,ys3);
        p2.plot2(xs,ys6);
        p3.plot(xs,ys10);
        p3.plot2(xs,ys120);

    }
}
