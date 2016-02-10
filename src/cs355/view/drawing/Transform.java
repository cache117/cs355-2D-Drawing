package cs355.view.drawing;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Created by cstaheli on 2/5/2016.
 */
public class Transform
{
    public static void applyTransformationToGraphics(Graphics2D graphics, double rotation, Point2D.Double center)
    {
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.translate(center.x, center.y);
        affineTransform.rotate(rotation);
        graphics.setTransform(affineTransform);
    }

    public static Point2D.Double getWorldPointFromObjectPoint(Point2D.Double objectPoint, double rotation, Point2D.Double center)
    {
        AffineTransform affineTransform = new AffineTransform();
        //Translate, rotate
        Point2D.Double worldPoint = new Point2D.Double();
        affineTransform.translate(center.x, center.y);
        affineTransform.rotate(rotation);
        worldPoint = (Point2D.Double) affineTransform.transform(objectPoint, worldPoint);
        return worldPoint;
    }

    public static Point2D.Double getObjectPointFromWorldPoint(Point2D.Double worldPoint, double rotation, Point2D.Double center)
    {
        AffineTransform affineTransform = new AffineTransform();
        Point2D.Double objectPoint = new Point2D.Double();
        //rotate, translate
        affineTransform.rotate(-rotation);
        affineTransform.translate(-center.x, -center.y);
        objectPoint = (Point2D.Double) affineTransform.transform(worldPoint, objectPoint);
        return objectPoint;
    }
}
