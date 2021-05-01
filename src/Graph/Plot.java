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
        //new Plot().evaluate(Math::exp);
        //new Plot().evaluate((x)-> 80*sin(x*50));
        p.evaluate(-200,0,0,100,200,0);
    }

    private final JFrame frame;
    private fX f;
    private WindowEvent listen;
    private final static Dimension screen = new Dimension(500,500);
    private static final Point ORIGIN = new Point();
    private Point[] p1,p2;
    private Line2D.Double x,y;
    private boolean calculated = false, drawAxis = true;


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
    public Plot evaluate(double... xy){
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

    private void axis() {
        Point px = new Point(-400,0),py = new Point(0,-400), px1 = new Point(400,0), py1 = new Point(0,400);
        this.x = new Line2D.Double(px.x,px.y,px1.x,px1.y);
        this.y = new Line2D.Double(py.x,py.y,py1.x,py1.y);
    }
    private void evaluate()
    {

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
            for (int i = 0; i< this.p1.length-1; i++ ){
                if (p1[i] == null || p1[i+1] == null)
                    continue;
                g.draw(new Line2D.Double(p1[i+1].x, p1[i+1].y, p1[i].x, p1[i].y));
                if( p2[i+1] == null || p2[i] == null )
                    continue;
                g.draw(new Line2D.Double(p2[i+1].x, p2[i+1].y, p2[i].x, p2[i].y));
            }
        }
    }
    static class Point
    {
        double x, y;
        Point(double x, double y)
        {
            double f = screen.width;
            this.x = (ORIGIN.x  + x);
            this.x = Math.min(Math.max(this.x,0), f);
            this.y = ORIGIN.y  - y;
            this.y = Math.min(Math.max(this.y,0),f);
        }

        Point(){
            this.x = screen.width/2.;
            this.y = screen.height/2.;
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
