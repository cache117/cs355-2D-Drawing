package cs355.view.drawing.util;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

/**
 * Takes care of all point Transformations that are necessary related to Object, World, and View.
 */
public class Transform
{
    public static void applyTransformationToGraphics(Graphics2D graphics, double rotation, Point2D.Double center)
    {
        AffineTransform affineTransform = buildObjectToWorldTransformation(rotation, center);
        graphics.setTransform(affineTransform);
    }

    public static Point2D.Double getWorldPointFromObjectPoint(Point2D.Double objectPoint, double rotation, Point2D.Double center)
    {
        AffineTransform affineTransform = buildObjectToWorldTransformation(rotation, center);
        return applyTransformationToPoint(affineTransform, objectPoint);
    }

    public static Point2D.Double getObjectPointFromWorldPoint(Point2D.Double worldPoint, double rotation, Point2D.Double center)
    {
        AffineTransform affineTransform = buildWorldToObjectTransformation(rotation, center);
        return applyTransformationToPoint(affineTransform, worldPoint);
    }

    public static Point2D.Double getObjectPointFromViewPoint(Point2D.Double viewPoint, double scaling, Point2D.Double viewportUpperLeft, double rotation, Point2D.Double center)
    {
        AffineTransform affineTransform = buildViewToObjectTransformation(rotation, center, scaling, viewportUpperLeft);
        return applyTransformationToPoint(affineTransform, viewPoint);
    }

    public static Point2D.Double getViewPointFromObjectPoint(Point2D.Double objectPoint, double scaling, Point2D.Double viewportUpperLeft, double rotation, Point2D.Double center)
    {
        AffineTransform affineTransform = buildObjectToViewTransformation(rotation, center, scaling, viewportUpperLeft);
        return applyTransformationToPoint(affineTransform, objectPoint);
    }

    public static Point2D.Double getWorldPointFromViewPoint(Point2D.Double viewPoint, double scaling, Point2D.Double viewportUpperLeft)
    {
        AffineTransform affineTransform = buildViewToWorldTransformation(scaling, viewportUpperLeft);
        return applyTransformationToPoint(affineTransform, viewPoint);
    }

    public static Point2D.Double getViewPointFromWorldPoint(Point2D.Double worldPoint, double scaling, Point2D.Double viewportUpperLeft)
    {
        AffineTransform affineTransform = buildWorldToViewTransformation(scaling, viewportUpperLeft);
        return applyTransformationToPoint(affineTransform, worldPoint);
    }

    private static AffineTransform buildObjectToWorldTransformation(double rotation, Point2D.Double center)
    {
        AffineTransform affineTransform = buildTranslationTransform(center.x, center.y);
        affineTransform.concatenate(buildRotationTransform(rotation));
        return affineTransform;
    }

    private static AffineTransform buildWorldToObjectTransformation(double rotation, Point2D.Double center)
    {
        AffineTransform affineTransform = buildObjectToWorldTransformation(rotation, center);
        try
        {
            affineTransform.invert();
        } catch (NoninvertibleTransformException e)
        {
            e.printStackTrace();
        }
        return affineTransform;
    }

    private static AffineTransform buildWorldToViewTransformation(double scaling, Point2D.Double viewportUpperLeft)
    {
        AffineTransform affineTransform = buildScalingTransform(scaling, scaling);
        affineTransform.concatenate(buildTranslationTransform(viewportUpperLeft.x, viewportUpperLeft.y));
        return affineTransform;
    }

    private static AffineTransform buildViewToWorldTransformation(double scaling, Point2D.Double viewportUpperLeft)
    {
        AffineTransform affineTransform = buildWorldToViewTransformation(scaling, viewportUpperLeft);
        try
        {
            affineTransform.invert();
        } catch (NoninvertibleTransformException e)
        {
            e.printStackTrace();
        }
        return affineTransform;
    }

    private static AffineTransform buildObjectToViewTransformation(double rotation, Point2D.Double center, double scaling, Point2D.Double viewportUpperLeft)
    {
        AffineTransform affineTransform = buildObjectToWorldTransformation(rotation, center);
        affineTransform.concatenate(buildWorldToViewTransformation(scaling, viewportUpperLeft));
        return affineTransform;
    }

    private static AffineTransform buildViewToObjectTransformation(double rotation, Point2D.Double center, double scaling, Point2D.Double viewportUpperLeft)
    {
        AffineTransform affineTransform = buildObjectToViewTransformation(rotation, center, scaling, viewportUpperLeft);
        try
        {
            affineTransform.invert();
        } catch (NoninvertibleTransformException e)
        {
            e.printStackTrace();
        }
        return affineTransform;
    }

    private static AffineTransform buildRotationTransform(double rotation)
    {
        //return AffineTransform.getRotateInstance(rotation);
        return new AffineTransform(Math.cos(rotation), Math.sin(rotation), -Math.sin(rotation), Math.cos(rotation), 0.0, 0.0);
    }

    private static AffineTransform buildTranslationTransform(double x, double y)
    {
        //return AffineTransform.getTranslateInstance(x, y);
        return new AffineTransform(1.0, 0.0, 0.0, 1.0, x, y);
    }

    private static AffineTransform buildScalingTransform(double x, double y)
    {
        //return AffineTransform.getScaleInstance(x, y);
        return new AffineTransform(x, 0.0, 0.0, y, 0.0, 0.0);
    }

    private static Point2D.Double applyTransformationToPoint(AffineTransform affineTransform, Point2D.Double point)
    {
        return (Point2D.Double) affineTransform.transform(point, new Point2D.Double());
    }
}
