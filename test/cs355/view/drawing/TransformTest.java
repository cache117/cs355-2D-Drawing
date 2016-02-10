package cs355.view.drawing;

import org.junit.Assert;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.*;

/**
 * Created by cstaheli on 2/8/2016.
 */
public class TransformTest
{
    @Test
    public void testGetWorldPointFromObjectPoint() throws Exception
    {
        /* No transformation */
        Point2D.Double objectPoint = new Point2D.Double(135, 20);
        double rotation = 0.0;
        Point2D.Double center = new Point2D.Double(0, 0);
        Point2D.Double worldPoint = Transform.getWorldPointFromObjectPoint(objectPoint, rotation, center);
        assertEquals(worldPoint, objectPoint);

        /* No rotation, different center */
        rotation = 0.0;
        objectPoint = new Point2D.Double(150, 130);
        center = new Point2D.Double(150, 150);
        worldPoint = Transform.getWorldPointFromObjectPoint(objectPoint, rotation, center);
        Point2D.Double expected = new Point2D.Double(300, 280);
        assertNotEquals(objectPoint, worldPoint);
        assertEquals(expected, worldPoint);

        objectPoint = new Point2D.Double(0, 0);
        worldPoint = Transform.getWorldPointFromObjectPoint(objectPoint, rotation, center);
        assertEquals(center, worldPoint);

        objectPoint = new Point2D.Double(-10, -10);
        worldPoint = Transform.getWorldPointFromObjectPoint(objectPoint, rotation, center);
        expected = new Point2D.Double(140, 140);
        assertEquals(expected, worldPoint);

        /* Rotation doesn't change center point */
        rotation = Math.PI / 2;
        center = new Point2D.Double(100, 100);
        objectPoint = new Point2D.Double(0, 0);
        worldPoint = Transform.getWorldPointFromObjectPoint(objectPoint, rotation, center);
        assertEquals(worldPoint, center);

        //Rotate by 90 degrees
        objectPoint = new Point2D.Double(10, 0);
        worldPoint = Transform.getWorldPointFromObjectPoint(objectPoint, rotation, center);
        expected = new Point2D.Double(100, 110);
        assertEquals(expected, worldPoint);

        rotation = -Math.PI / 2;
        worldPoint = Transform.getWorldPointFromObjectPoint(objectPoint, rotation, center);
        expected = new Point2D.Double(100, 90);
        assertEquals(expected, worldPoint);

        rotation = Math.PI;
        worldPoint = Transform.getWorldPointFromObjectPoint(objectPoint, rotation, center);
        expected = new Point2D.Double(90, 100);
        assertEquals(expected, worldPoint);
    }

    @Test
    public void testGetObjectPointFromWorldPoint() throws Exception
    {

    }
}