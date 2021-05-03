package NM.Graph;

import java.awt.*;

import static NM.Graph.Plot.ORIGIN;
import static NM.Graph.Plot.screen;

public class Point
{
    double x, y;
    static double scale = 1.05;
    double xOff , yOff;
    private static Dimension screen;
    Point(double x, double y)
    {
        this.x = ORIGIN.getX()  + (x * scale);
        this.y = ORIGIN.getY()  - (y * scale);
    }

    Point()
    {
        this.x = screen.width/2. + xOff;
        this.y = screen.height/2. - yOff;
    }

    public double getX() {
        double x =  (this.x + (xOff))  ;
        return ((x > screen.width? screen.width: x)<0 ? 0 : x);
    }

    public static void setScreen(Dimension s) {
        screen = s;
    }

    public double getY() {
        double y = (this.y + (yOff))  ;
        return ((y > screen.width? screen.width: y)<0 ? 0 : y);
    }

    @Override
    public String toString() {
        return "{" +
                "drawX=" + x +
                ",drawY=" + y +
                '}';
    }
}
