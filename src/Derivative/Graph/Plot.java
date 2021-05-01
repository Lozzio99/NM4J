package Derivative.Graph;

import Derivative.FirstDeriv.ThreePointForward;
import functions.fX;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class Plot extends Canvas
{

    private final JFrame frame;
    private final fX f;
    private WindowEvent listen;
    private final static Dimension screen = new Dimension(800,800);
    private static final Point ORIGIN = new Point();
    private List<Point> p;

    public Plot(fX f)
    {
        this.f = f;
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
        this.frame.add(this);
        this.frame.setVisible(true);
        this.evaluate();
        this.start();
    }

    private void evaluate()
    {
        this.p = new ArrayList<>();
        int x_ = screen.width/2;
        for (int i = -x_; i< x_; i++){
            p.add(new Point(i,f.f_x(i)));
        }
    }

    private void start() {
        Timer t = new Timer(100,(e)->render());
        t.start();
    }

    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bs.getDrawGraphics();
        Graphics2D g = (Graphics2D)graphics;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, screen.width, screen.height);
        g.setColor(Color.BLACK);
        draw(g);
        g.dispose();
        bs.show();
    }

    private void draw(Graphics2D g)
    {
        for (int i =0;i< this.p.size()-1; i++ ){
            g.draw(new Line2D.Double(p.get(i+1).x,p.get(i+1).y,p.get(i).x,p.get(i).y));
        }
    }

    public static void main(String[] args) {
        //Plot p = new Plot(Math::exp);
        Plot p = new Plot((x)-> 25*sin(0.00101*pow(x,2)));
    }


    static class Point
    {
        double x, y;
        Point(double x, double y)
        {
            this.x = ORIGIN.x  + x;
            this.y = ORIGIN.y  - y;
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
