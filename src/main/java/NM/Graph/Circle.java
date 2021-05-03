package NM.Graph;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import static NM.Graph.DrawFourier.getTime;
import static java.lang.Math.*;

public class Circle extends Ellipse2D.Double
{
    double drawX, drawY,cx,cy;
    double r,theta;
    static final NM.Graph.Point ORIGIN = new NM.Graph.Point();

    private Ellipse2D.Double c;
    private Line2D.Double l;
    private Bag b;

    public Circle(double x, double y,double r)
    {
        this.cx = ORIGIN.getX()+ x;
        this.cy = ORIGIN.getY()- y;
        this.drawX = cx-r;
        this.drawY = cy-r;
        this.r = r;
        this.b = new Bag();
    }

    //center drawX
    public double x(){
        return cx;
    }
    //center drawY
    public double y(){
        return cy;
    }
    @Override
    public double getX() {
        return  drawX;
    }

    @Override
    public double getY() {
        return   drawY;
    }

    @Override
    public double getWidth() {
        return 2* this.r;
    }

    @Override
    public double getHeight() {
        return 2* this.r;
    }


    public Ellipse2D.Double getTheta()
    {
        double x = x() + (this.r * cos(getTime()));
        double y = y() - (this.r * sin(getTime()));
        this.b.add(new Point(x,y));
        return this.c =  new Ellipse2D.Double(x-5,y-5,10,10);
    }

    public Line2D.Double getLine() {
        return this.l =  new Line2D.Double(this.x(),this.y(),getTheta().getX()+5,getTheta().getY()+5);
    }

    public Bag getB()
    {
        return this.b;
    }



    class Point {
        double x,y;
        Point(double x,double y){
            this.x = x;
            this.y = y;
        }
    }

    class Bag
    {
        Point[] trajectories;
        int insert;
        boolean loop;

        Bag() {
            this.trajectories = new Point[200];
            this.insert = 0;
            this.loop = false;
        }

        void add(Point p) {
            this.trajectories[this.insert] = p;
            this.insert++;
            if (this.insert == this.trajectories.length) {
                this.insert = 0;
            }
        }

        Point[] getPoints() {
            return this.trajectories;
        }

        public int getInsert() {
            return insert;
        }
    }
}
