package cs355.view.drawing.util;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

/**
 * Created by cstaheli on 2/5/2016.
 */
public class Transform
{
    public static void applyTransformationToGraphics(Graphics2D graphics, double rotation, Point2D.Double center)
    {
        AffineTransform affineTransform = objectToWorld(rotation, center);
        graphics.setTransform(affineTransform);
    }

    private static AffineTransform worldToObject(double rotation, Point2D.Double center)
    {
        AffineTransform affineTransform = objectToWorld(rotation, center);
        try
        {
            affineTransform.invert();
        } catch (NoninvertibleTransformException e)
        {
            e.printStackTrace();
        }
        return affineTransform;
    }

    private static AffineTransform objectToWorld(double rotation, Point2D.Double center)
    {
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.translate(center.x, center.y);
        affineTransform.rotate(rotation);
        return affineTransform;
    }

    public static Point2D.Double getWorldPointFromObjectPoint(Point2D.Double objectPoint, double rotation, Point2D.Double center)
    {
        AffineTransform affineTransform = objectToWorld(rotation, center);
        Point2D.Double worldPoint = new Point2D.Double();
        worldPoint = (Point2D.Double) affineTransform.transform(objectPoint, worldPoint);
        return worldPoint;
    }

    public static Point2D.Double getObjectPointFromWorldPoint(Point2D.Double worldPoint, double rotation, Point2D.Double center)
    {
        AffineTransform affineTransform = worldToObject(rotation, center);
        Point2D.Double objectPoint = new Point2D.Double();
        objectPoint = (Point2D.Double) affineTransform.transform(worldPoint, objectPoint);
        return objectPoint;
    }

    public static Point2D.Double getObjectPointFromViewPoint(Point2D.Double viewPoint, double scaling, Point2D.Double viewportUpperLeft, double rotation, Point2D.Double center)
    {
        Point2D.Double worldPoint = getWorldPointFromViewPoint(viewPoint, scaling, viewportUpperLeft);
        return getObjectPointFromWorldPoint(worldPoint, rotation, center);
    }

    public static Point2D.Double getWorldPointFromViewPoint(Point2D.Double viewPoint, double scaling, Point2D.Double viewportUpperLeft)
    {
        return viewPoint;
    }

    public static Point2D.Double getViewPointFromWorldPoint(Point2D.Double worldPoint, double scaling, Point2D.Double viewportUpperLeft)
    {
        return worldPoint;
    }
}
