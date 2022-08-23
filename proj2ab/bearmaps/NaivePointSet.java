package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet {
    private List<Point> points;

    public NaivePointSet(List<Point> points){
        this.points=points;
    }

    @Override
    public Point nearest(double x, double y) {
        if(points.isEmpty()){return null;}
        Point goal=new Point(x,y);
        Point nearestPoint=points.get(0);
        for(Point p:points){
            if(Double.compare(Point.distance(goal,p),Point.distance(goal,nearestPoint))<0){
                nearestPoint=p;
            }
        }
        return nearestPoint;
    }


}