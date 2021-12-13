package src;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;

public class Diagram extends JFrame {
    JPanel main = new JPanel();
    MyPanel jPanel = new MyPanel();
    private List<Point> points = new ArrayList<>();
    private List<Point> bestpoints = new ArrayList<>();
    BestPanel bestPanel = new BestPanel();

    public Diagram(List<Point> points) throws HeadlessException {
        this.points = points;
        bestpoints = points;
        initialize();
    }
    private void initialize()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BoxLayout boxLayout = new BoxLayout(main, 0);
        main.setLayout(boxLayout);
        main.add(jPanel);
        main.add(bestPanel);
        this.add(main);
        repaint();
        this.setSize(new Dimension(1000,600));
        this.setMinimumSize(new Dimension(300,300));
        jPanel.setVisible(true);
    }

    public void setPoints(List<Point> points) {
        this.points = points;
        repaint();
    }

    public void setBestpoints(List<Point> bestpoints) {
        this.bestpoints = bestpoints;
    }

    class MyPanel extends JPanel{
        private Shape shape;
        public void paint(Graphics g)
        {
            g.setColor(Color.BLACK);
            shape = new GeneralPath();
            ((GeneralPath)shape).moveTo(points.get(0).getPointc().get(0),points.get(1).getPointc().get(1));
            for(var i : points)
            {
                //if(points.get(0)!=i)
                ((GeneralPath)shape).lineTo(i.getPointc().get(0), i.getPointc().get(1));
                //((Graphics2D)g).drawOval(i.getPointc().get(0), i.getPointc().get(1), 5,5);
                ((Graphics2D)g).fillOval(i.getPointc().get(0) - 3, i.getPointc().get(1) -3 , 6,6);
            }
            ((GeneralPath)shape).closePath();
            ((Graphics2D)g).draw(shape);
            //g.fillRect(0,0,50,50);
        }
    }
    class BestPanel extends JPanel{
        private Shape shape;
        public void paint(Graphics g)
        {
            g.setColor(Color.BLACK);
            shape = new GeneralPath();
            ((GeneralPath)shape).moveTo(bestpoints.get(0).getPointc().get(0),bestpoints.get(1).getPointc().get(1));
            for(var i : bestpoints)
            {
                //if(bestpoints.get(0)!=i)
                ((GeneralPath)shape).lineTo(i.getPointc().get(0), i.getPointc().get(1));
                //((Graphics2D)g).drawOval(i.getPointc().get(0), i.getPointc().get(1), 6,6);
                ((Graphics2D)g).fillOval(i.getPointc().get(0)-3, i.getPointc().get(1)-3, 6,6);
            }
            ((GeneralPath)shape).closePath();
            ((Graphics2D)g).draw(shape);
            //g.fillRect(0,0,50,50);
        }
    }
}
