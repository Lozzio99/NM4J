package NM.Polynomials;

import NM.Util.functions.fX;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.*;

class LegendreTest {

    static double accuracy = 4e-4;
    static Legendre legendre;

    @BeforeEach
    void setUp() {
        fX f = (x)-> 1/(x+2);
        legendre = new Legendre(f,-1,1);
        legendre.setX(0.6);
    }

    @Test
    @DisplayName("GetC")
    void GetC() {
        assertAll(
                ()-> assertTrue(testAccuracy(legendre.getC()[0],0.549)),
                ()-> assertTrue(testAccuracy(legendre.getC()[1],-0.2958)),
                ()-> assertTrue(testAccuracy(legendre.getC()[2],0.1059)),
                ()-> assertTrue(testAccuracy(legendre.getC()[3], -0.0341)),
                ()-> assertTrue(testAccuracy(legendre.getC()[4],0.0104)));
    }



    @Test
    @DisplayName("GetP")
    void GetP() {
        assertAll(
                ()-> assertTrue(testAccuracy(legendre.getP()[0].f_x(legendre.getX()),1.0)),
                ()-> assertTrue(testAccuracy(legendre.getP()[1].f_x(legendre.getX()), legendre.getX())),
                ()-> assertTrue(testAccuracy(legendre.getP()[2].f_x(legendre.getX()),0.040)),
                ()-> assertTrue(testAccuracy(legendre.getP()[3].f_x(legendre.getX()),-0.360)),
                ()-> assertTrue(testAccuracy(legendre.getP()[4].f_x(legendre.getX()),-0.408)));
    }

    @Test
    @DisplayName("G")
    void G() {
        assertTrue(testAccuracy( legendre.g(legendre.getX()),0.384));
    }




    //Ive done this in the way around what a mess
    public boolean testAccuracy( double actual, double expected){
        return abs(actual-expected)<accuracy;
    }
}