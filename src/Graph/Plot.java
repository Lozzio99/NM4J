package Graph;

import Derivative.FirstDeriv.ThreePointForward;
import functions.fX;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.*;

public class Plot extends Canvas
{
    public static void main(String[] args) {
        Plot p = new Plot();
        p.scale(1);
        //new Plot().evaluate(Math::exp);
        p.evaluate((x)-> 80*sin(x*50));
        //p.evaluate(-200,0,0,100,200,0);
    }

    private final JFrame frame;
    private fX f;
    private WindowEvent listen;
    private final static Dimension screen = new Dimension(500,500);
    private static final Point ORIGIN = new Point();
    private Point[] p1,p2;
    private Line2D.Double x,y;
    private boolean calculated = false, drawAxis = true, plot2 = false;


    public Plot()
    {
        this.frame = new JFrame();
        this.frame.setSize(screen);
        this.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                listen = new WindowEvent(frame,201);
                Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(listen);
                System.exit(0);
            }
        });
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);
        this.frame.add(this);
        this.frame.setVisible(true);
        if (drawAxis)
            this.axis();
        this.start();
    }

    public Plot evaluate(fX f)
    {
        calculated = false;
        this.f = f;
        this.evaluate();
        return this;
    }

    /**
     * Requires sorted pairs of x and y 's
     */
    public Plot evaluate(double... xy)
    {
        calculated = false;
        if (xy.length %2 != 0)
            throw new IllegalArgumentException("Please fill x,y - x,y - x,y...");
        this.p1 = new Point[xy.length/2];
        this.p2 = new Point[xy.length-this.p1.length];
        int k = 0;
        for (int i = 0; i< this.p1.length; i++) {
            this.p1[i] = new Point(xy[k],xy[k+1]);
            k+= 2;
        }
        for (int i = 0; k < this.p2.length; i++) {
            this.p2[i] = new Point(xy[k],xy[k+1]);
            k+= 2;
        }
        calculated = true;
        return this;
    }

    public Plot plot(double[] xs, double[] ys)
    {
        calculated = false;
        if (xs.length!= ys.length)
            throw new IllegalArgumentException("x and y must have the same size");
        if (xs.length>1000)
            throw new UnsupportedOperationException("need to implement multiple points arrays");
        double[]x,y;
        x = Arrays.copyOf(xs,xs.length);
        y = Arrays.copyOf(ys,ys.length);
        sortingForDrawing(x,y);
        this.p1 = new Point[xs.length];
        for (int i = 0; i< this.p1.length; i++){
            this.p1[i] = new Point(x[i],y[i]);
        }
        calculated = true;
        return this;
    }

    public Plot plot2(double[]xs, double[] ys)
    {
        calculated = false;
        plot2 = true;
        double[]x,y;
        x = Arrays.copyOf(xs,xs.length);
        y = Arrays.copyOf(ys,ys.length);
        sortingForDrawing(x,y);
        this.p2 = new Point[xs.length];
        for (int i = 0; i< this.p1.length; i++){
            this.p2[i] = new Point(x[i],y[i]);
        }
        calculated = true;
        return this;
    }

    /**
     * sorts the arrays by reference
     * @param xs the arrays which will give the sorting order
     * @param ys the xs index - related values
     */
    public static void sortingForDrawing(double xs[], double []ys)
    {
        int n = xs.length;
        for (int j = 1; j < n; j++) {
            double key = xs[j],y_key = ys[j];
            int i = j-1;
            while ( (i > -1) && ( xs [i] > key ) ) {
                xs [i+1] = xs [i];
                ys [i+1] = ys [i];
                i--;
            }
            xs[i+1] = key;
            ys[i+1] = y_key;
        }
    }

    private void axis()
    {
        Point px = new Point(-400,0),py = new Point(0,-400), px1 = new Point(400,0), py1 = new Point(0,400);
        this.x = new Line2D.Double(px.getX(),px.getY(),px1.getX(),px1.getY());
        this.y = new Line2D.Double(py.getX(),py.getY(),py1.getX(),py1.getY());
    }
    private void evaluate()
    {
        calculated = false;
        int x_ = screen.width*2;
        this.p1 = new Point[x_];
        this.p2 = new Point[x_];
        double k = -x_/2.;
        for (int i = 0; i< x_; i++) {
            p1[i] = new Point(k,f.f_x(k));
            k+= 0.5;
        }
        for (int i = 0; i< x_; i++) {
            p2[i] = new Point(k,f.f_x(k));
            k+= 0.5;
        }
        calculated = true;
    }

    private void start()
    {
        Timer t = new Timer(100,(e) -> render());
        t.start();
    }

    public void translate(double x, double y)
    {
        ORIGIN.xOff+= x;
        ORIGIN.yOff+= y;
        if (drawAxis)
            this.axis();
    }
    public void scale(double scale)
    {
        Point.scale += scale ;
    }
    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bs.getDrawGraphics();
        Graphics2D g = (Graphics2D)graphics;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, screen.width, screen.height);
        draw(g);
        g.dispose();
        bs.show();
    }

    private void draw(Graphics2D g)
    {
        if (drawAxis) {
            g.setColor(Color.RED);
            g.draw(this.x);
            g.draw(this.y);
        }

        if (calculated) {
            g.setColor(Color.BLACK);
            for (int i = 0; i< this.p1.length-1; i++ )
            {
                if (p1[i] == null || p1[i+1] == null)
                    continue;
                g.draw(new Line2D.Double(p1[i+1].getX(), p1[i+1].getY(), p1[i].getX(), p1[i].getY()));
            }

            if (p2 == null)
                return;
            if (plot2)
                g.setColor(Color.BLUE);
            for (int i = 0; i< this.p2.length-1; i++ )
            {
                if (p2[i + 1] == null || p2[i] == null)
                    continue;
                g.draw(new Line2D.Double(p2[i + 1].getX(), p2[i + 1].getY(), p2[i].getX(), p2[i].getY()));
            }
        }
    }
    static class Point
    {
        double x, y;
        static double scale = 1.05;
        double xOff , yOff;

        Point(double x, double y)
        {
            double f = screen.width;
            this.x = ORIGIN.getX()  + (x * scale);
            this.y = ORIGIN.getY()  - (y * scale);
        }

        Point(){
            this.x = screen.width/2. + xOff;
            this.y = screen.height/2. - yOff;
        }

        public double getX() {
            double x =  (this.x + (xOff))  ;
            return ((x > screen.width? screen.width: x)<0 ? 0 : x);
        }

        public double getY() {
            double y = (this.y + (yOff))  ;
            return ((y > screen.width? screen.width: y)<0 ? 0 : y);
        }

        @Override
        public String toString() {
            return "{" +
                    "x=" + x +
                    ",y=" + y +
                    '}';
        }
    }

}
