package NM.Polynomials;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LinearLeastSquaresTest {

    LinearLeastSquares linear, quadratic , cubic;
    double [] xs =new double[]{ 0,0.25,0.5,0.75,1};
    double [] ys = new double[]{0.876, 1.060, 1.525, 1.993, 2.594};



    @Test
    void values() {
        linear = new LinearLeastSquares(xs, ys);
        quadratic = new LinearLeastSquares.Quadratic(xs, ys);
        cubic = new LinearLeastSquares.Cubic(xs, ys);


        //System.out.println(linear.g(0.4));
        //System.out.println(quadratic.g(0.4));
        //System.out.println(cubic.g(0.4));


        //System.out.println();
    }

    @Test
    @DisplayName("Compute")
    void Compute() {


    }
}