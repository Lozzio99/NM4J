package NM.Util.Exercises;

import NM.Fourier.FourierApprox;
import NM.Integration.AdaptiveSimpsons;
import NM.Util.functions.fX;

import static java.lang.StrictMath.*;

public class Exercise5_14_16
{
    public static void main(String[] args) {
        fX f = x-> x*sin(x);
        FourierApprox fourier1 = new FourierApprox(f,1);
        FourierApprox fourier2 = new FourierApprox(f,2);
        FourierApprox fourier3 = new FourierApprox(f,3);

        double sq1, sq2, sq3;
        double mean1,mean2,mean3;
        fX f1= fourier1.getS();
        fX f2 = fourier2.getS();
        fX f3= fourier3.getS();

        AdaptiveSimpsons integrator1 = new AdaptiveSimpsons(x-> pow(f.f_x(x)-f1.f_x(x),2));
        AdaptiveSimpsons integrator2 = new AdaptiveSimpsons(x-> pow(f.f_x(x)-f2.f_x(x),2));
        AdaptiveSimpsons integrator3 = new AdaptiveSimpsons(x-> pow(f.f_x(x)-f3.f_x(x),2));


        sq1 = integrator1.nRule(-PI,PI);
        mean1 = sqrt(sq1/ (2*PI));
        sq2 = integrator2.nRule(-PI,PI);
        mean2 = sqrt(sq2/ (2*PI));
        sq3 = integrator3.nRule(-PI,PI);
        mean3 = sqrt(sq3/ (2*PI));

        System.out.println(sq1);
        System.out.println(mean1);
        System.out.println();
        System.out.println(sq2);
        System.out.println(mean2);
        System.out.println();
        System.out.println(sq3);
        System.out.println(mean3);


    }
}
