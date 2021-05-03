package NM.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferStrategy;

public class DrawFourier extends Canvas
{
    private final JFrame frame;
    final static Dimension screen = new Dimension(800,800);
    private WindowEvent listen;
    private Circle circle;
    private boolean init = false;
    private static double time = 0;

    public static void main(String[] args) {
        new DrawFourier();
    }

    public DrawFourier(){
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
        ImageIcon img = new ImageIcon(this.getClass().getClassLoader().getResource("death.png").getFile());
        this.frame.setIconImage(img.getImage());
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);
        this.frame.add(this);
        this.frame.setVisible(true);
        this.init();
        this.start();
    }

    private void init() {
        Point.setScreen(screen);
        this.circle = new Circle(0,0,100);
    }

    private void start() {
        Timer t = new Timer(10,(e) -> {
            render();
            time+= 0.01;
        });
        t.start();
    }

    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            init = false;
            this.createBufferStrategy(3);
            return;
        }
        Graphics graphics = bs.getDrawGraphics();
        Graphics2D g = (Graphics2D)graphics;
        if (!init)
            createHints(g);
        g.setColor(new Color(153, 75, 245, 190));
        g.fill3DRect(0, 0, screen.width, screen.height,true);
        draw(g);
        g.dispose();
        bs.show();
    }

    private void createHints(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
        g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,RenderingHints.VALUE_COLOR_RENDER_SPEED);
        g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);
        g.setRenderingHint(RenderingHints.KEY_RESOLUTION_VARIANT,RenderingHints.VALUE_RESOLUTION_VARIANT_SIZE_FIT);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_PURE);
        g.setRenderingHint(RenderingHints.KEY_DITHERING,RenderingHints.VALUE_DITHER_ENABLE);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);




        init = true;
    }

    private void draw(Graphics2D g)
    {
        g.setColor(new Color(255, 255, 255));
        g.draw(this.circle);
        g.draw(this.circle.getTheta());
        g.draw(this.circle.getLine());
        for (int i = this.circle.getB().insert ; i< 199; i++ )
        {
            if (this.circle.getB().getPoints()[i]!=null&& this.circle.getB().getPoints()[i+1]!= null)
                g.draw(new Line2D.Double(i,this.circle.getB().getPoints()[i].y,
                        i,this.circle.getB().getPoints()[i+1].y));
        }
    }


    public static double getTime() {
        return time;
    }
}
