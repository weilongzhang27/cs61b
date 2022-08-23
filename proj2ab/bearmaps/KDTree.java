package bearmaps;

import java.util.List;

public class KDTree implements PointSet {
    private Node root;

    private class Node {
        private Point point;
        private Node leftChild=null;//also refer to the downChild
        private Node rightChild=null;//also refer to the upChild
        private boolean direction; //True : vertical, False : horizontal


        Node(Point point, boolean direction) {
            this.point=point;
            this.direction = direction;
        }

    }


    public KDTree(List<Point> points){
        root=new Node(points.get(0),true);
        for (int i = 1; i < points.size(); i++) {
            add(points.get(i),root);
        }
    }

    private void add(Point p, Node n){
        if(comparePoints(p,n.point,n.direction)<0){
            if(n.leftChild==null){
                n.leftChild=new Node(p,!n.direction);
            }else{
                add(p,n.leftChild);
            }

        }else{
            if(n.rightChild==null){
                n.rightChild=new Node(p,!n.direction);
            }else{
                add(p,n.rightChild);
            }
        }

    }

    private int comparePoints(Point a, Point b, boolean orientation){
        if(orientation){
            return Double.compare(a.getX(),b.getX());
        }else{
            return Double.compare(a.getY(),b.getY());
        }
    }

    @Override
    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        Node result=nearestHelper(root,goal,root);
        return result.point;
    }

    private Node nearestHelper(Node n, Point goal, Node best){
        if(n==null){
            return best;
        }
        if(Double.compare(Point.distance(goal,n.point),Point.distance(goal,best.point))<0){
            best=n;
        }
        Node goodSide;
        Node badSide;
        if(comparePoints(goal,n.point,n.direction)<0){
            goodSide=n.leftChild;
            badSide=n.rightChild;
        }else{
            goodSide=n.rightChild;
            badSide=n.leftChild;
        }
        best=nearestHelper(goodSide,goal,best);

        if(n.direction){
            double tempDistance=Math.pow(Math.abs(n.point.getX()-goal.getX()),2);
            if(Double.compare(tempDistance,Point.distance(goal,best.point))<0){
                best=nearestHelper(badSide,goal,best);
            }
        }else{
            double tempDistance=Math.pow(Math.abs(n.point.getY()-goal.getY()),2);
            if(Double.compare(tempDistance,Point.distance(goal,best.point))<0){
                best=nearestHelper(badSide,goal,best);
            }
        }

        return best;

    }


}
