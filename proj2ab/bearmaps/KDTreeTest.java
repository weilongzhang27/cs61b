
package bearmaps;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {

    public KDTree createKDtree(){
        Point p1=new Point(2,3);
        Point p2=new Point(4,2);
        Point p3=new Point(4,5);
        Point p4=new Point(3,3);
        Point p5=new Point(1,5);
        Point p6=new Point(4,4);

        KDTree kd=new KDTree(List.of(p1,p2,p3,p4,p5,p6));
        return kd;
    }

    @Test
    public void testNearestDemoSlides(){
        KDTree kd=createKDtree();
        Point actual = kd.nearest(0,7);
        Point expected = new Point(1,5);
        assertEquals(expected,actual);
    }


    @Test
    public void testNaivePointSet() {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);
        NaivePointSet temp = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = temp.nearest(3.0, 4.0); // returns p2
        assertEquals(3.3, ret.getX(), 0.0001);
        assertEquals(4.4, ret.getY(), 0.0001);
    }

    @Test
    public void testBasicRandom() {
        HashSet<Double> X = new HashSet<>();
        HashSet<Double> Y = new HashSet<>();
        List<Point> temp = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            double x = StdRandom.uniform(-400000.0, 400000);
            double y = StdRandom.uniform(-400000.0, 400000);
            if (!X.contains(x) && !Y.contains(y)) {
                X.add(x);
                Y.add(y);
                temp.add(new Point(x, y));
            }
        }
        KDTree test = new KDTree(temp);
        NaivePointSet test2 = new NaivePointSet(temp);
        for (int i = 0; i < 10000; i++) {
            double x2 = StdRandom.uniform(-4000000, 4000000);
            double y2 = StdRandom.uniform(-4000000, 4000000);
            Point result1 = test.nearest(x2, y2);
            Point result2 = test2.nearest(x2, y2);
            assertEquals(result1.getX(), result2.getX(), 0.000001);
            assertEquals(result1.getY(), result2.getY(), 0.000001);
        }
    }

    /**
     * NaivePointSet: Total time elapsed: 2.284 seconds.
     * KDTree: Total time elapsed: 0.019 seconds.
     */
    @Test
    public void testTime() {
        ArrayList<Point> temp3 = new ArrayList<>();
        HashSet<Double> X = new HashSet<>();
        HashSet<Double> Y = new HashSet<>();
        List<Point> temp = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            double x = StdRandom.uniform(-300000.0, 300000);
            double y = StdRandom.uniform(-300000.0, 300000);
            if (!X.contains(x) && !Y.contains(y)) {
                X.add(x);
                Y.add(y);
                temp.add(new Point(x, y));
            }
        }
        NaivePointSet test1 = new NaivePointSet(temp);
        KDTree test2 = new KDTree(temp);
        for (int i = 0; i < 10000; i++) {
            double x2 = StdRandom.uniform(-300000, 300000);
            double y2 = StdRandom.uniform(-300000, 300000);
            temp3.add(new Point(x2, y2));
        }
        Stopwatch sw = new Stopwatch();
        for (int i = 0; i < 10000; i++) {
            Point temp2 = temp3.get(i);
            test1.nearest(temp2.getX(), temp2.getY());
        }
        System.out.println("Total time elapsed: " + sw.elapsedTime() + " seconds.");

        sw = new Stopwatch();
        for (int i = 0; i < 10000; i++) {
            Point temp2 = temp3.get(i);
            test2.nearest(temp2.getX(), temp2.getY());
        }
        System.out.println("Total time elapsed: " + sw.elapsedTime() + " seconds.");
    }

}
