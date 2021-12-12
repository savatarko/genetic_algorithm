package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Point {
    List<Integer> pointc = new ArrayList<>();
    public static int d;
    public static int max;

    public Point() {
        Random random = new Random();
        int size = d;
        while(size>0)
        {
            pointc.add(random.nextInt(max));
            size--;
        }
    }
    public Point(int x)
    {
        int size = d;
        while(size>0)
        {
            pointc.add(x);
            size--;
        }
    }

    public Point(int x,int y)
    {
        pointc.add(x);
        pointc.add(y);
    }
    public List<Integer> getPointc() {
        return pointc;
    }

    public static double calculatedistance(Point p1, Point p2)
    {
        double result = 0;
        for(int i = 0; i<p1.getPointc().size(); i++)
        {
            result+=Math.pow(p1.getPointc().get(i)-p2.getPointc().get(i),2);
        }
        return Math.sqrt(result);
    }

    @Override
    public String toString() {
        String output = "(";
        for(var i : pointc)
        {
            output = output.concat(i + ",");
        }
        output = output.substring(0,output.length() - 1);
        output = output.concat(")");
        return output;
    }

    /*
    @Override
    public boolean equals(Object o) {
        for(int i = 0;i<this.pointc.size();i++)
        {
            if(pointc.get(i).intValue() != this.pointc.get(i).intValue())
                return false;
        }
        return true;
    }

     */

    @Override
    public int hashCode() {
        return Objects.hash(pointc);
    }

    public static boolean isDummy(Point point)
    {
        for(var i : point.pointc)
        {
            if(i == -1)
                return true;
        }
        return false;
    }
}
