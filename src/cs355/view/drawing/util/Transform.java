package cs355.view.drawing.util;

import cs355.view.DrawingParameters;
import cs355.view.ObjectParameters;
import cs355.view.ViewportParameters;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Takes care of all point Transformations that are necessary related to Object, World, and View.
 */
public class Transform
{
    /**
     * Compiles the information contained in the DrawingParameters and ObjectParameters and sets the transform of drawingParameters to be the related transform. Used for drawing.
     *
     * @param drawingParameters the information about the drawing and the view.
     * @param objectParameters  the information about the object being drawn.
     */
    public static void applyTransformationToGraphics(DrawingParameters drawingParameters, ObjectParameters objectParameters)
    {
        AffineTransform affineTransform = buildObjectToViewTransformation(objectParameters, drawingParameters.viewportParameters);
        drawingParameters.graphics2D.setTransform(affineTransform);
    }

    /**
     * Gets the world point from the object point based on the transformation properties of the object.
     *
     * @param objectPoint      the object point to convert.
     * @param objectParameters the transformation information about the object.
     * @return the world point associated with the object point.
     */
    public static Point2D.Double getWorldPointFromObjectPoint(Point2D.Double objectPoint, ObjectParameters objectParameters)
    {
        AffineTransform affineTransform = buildObjectToWorldTransformation(objectParameters);
        return applyTransformationToPoint(affineTransform, objectPoint);
    }

    /**
     * Gets the object point from the world point based on the transformation properties of the object.
     *
     * @param worldPoint       the world point to convert.
     * @param objectParameters the transformation information about the object.
     * @return the object point associated with the world point.
     */
    public static Point2D.Double getObjectPointFromWorldPoint(Point2D.Double worldPoint, ObjectParameters objectParameters)
    {
        AffineTransform affineTransform = buildWorldToObjectTransformation(objectParameters);
        return applyTransformationToPoint(affineTransform, worldPoint);
    }

    /**
     * Gets the object point from the view point based on the transformation properties of the object and viewport.
     *
     * @param viewPoint          the world point to convert.
     * @param objectParameters   the transformation information about the object.
     * @param viewportParameters the transformation information about the viewport.
     * @return the object point associated with the view point.
     */
    public static Point2D.Double getObjectPointFromViewPoint(Point2D.Double viewPoint, ObjectParameters objectParameters, ViewportParameters viewportParameters)
    {
        AffineTransform affineTransform = buildViewToObjectTransformation(objectParameters, viewportParameters);
        return applyTransformationToPoint(affineTransform, viewPoint);
    }

    /**
     * Gets the view point from the object point based on the transformation properties of the object and viewport.
     *
     * @param objectPoint        the world point to convert.
     * @param objectParameters   the transformation information about the object.
     * @param viewportParameters the transformation information about the viewport.
     * @return the view point associated with the object point.
     */
    public static Point2D.Double getViewPointFromObjectPoint(Point2D.Double objectPoint, ObjectParameters objectParameters, ViewportParameters viewportParameters)
    {
        AffineTransform affineTransform = buildObjectToViewTransformation(objectParameters, viewportParameters);
        return applyTransformationToPoint(affineTransform, objectPoint);
    }

    /**
     * Gets the world point from the view point based on the transformation properties of the viewport.
     *
     * @param viewPoint          the world point to convert.
     * @param viewportParameters the transformation information about the viewport.
     * @return the world point associated with the view point.
     */
    public static Point2D.Double getWorldPointFromViewPoint(Point2D.Double viewPoint, ViewportParameters viewportParameters)
    {
        AffineTransform affineTransform = buildViewToWorldTransformation(viewportParameters);
        return applyTransformationToPoint(affineTransform, viewPoint);
    }

    /**
     * Gets the view point from the world point based on the transformation properties of the viewport.
     *
     * @param worldPoint         the world point to convert.
     * @param viewportParameters the transformation information about the viewport.
     * @return the view point associated with the world point.
     */
    public static Point2D.Double getViewPointFromWorldPoint(Point2D.Double worldPoint, ViewportParameters viewportParameters)
    {
        AffineTransform affineTransform = buildWorldToViewTransformation(viewportParameters);
        return applyTransformationToPoint(affineTransform, worldPoint);
    }

    private static AffineTransform buildObjectToWorldTransformation(ObjectParameters objectParameters)
    {
        AffineTransform affineTransform = buildTranslationTransform(objectParameters.center.x, objectParameters.center.y);
        affineTransform.concatenate(buildRotationTransform(objectParameters.rotation));
        return affineTransform;
    }

    private static AffineTransform buildWorldToObjectTransformation(ObjectParameters objectParameters)
    {
        AffineTransform affineTransform = buildRotationTransform(-objectParameters.rotation);
        affineTransform.concatenate(buildTranslationTransform(-objectParameters.center.x, -objectParameters.center.y));
        return affineTransform;
    }

    private static AffineTransform buildWorldToViewTransformation(ViewportParameters viewportParameters)
    {
        AffineTransform affineTransform = buildScalingTransform(viewportParameters.scalingFactor);
        affineTransform.concatenate(buildTranslationTransform(-viewportParameters.upperLeft.x, -viewportParameters.upperLeft.y));
        return affineTransform;
    }

    private static AffineTransform buildViewToWorldTransformation(ViewportParameters viewportParameters)
    {
        AffineTransform affineTransform = buildTranslationTransform(viewportParameters.upperLeft.x, viewportParameters.upperLeft.y);
        affineTransform.concatenate(buildScalingTransform(1.0 / viewportParameters.scalingFactor));
        return affineTransform;
    }

    private static AffineTransform buildObjectToViewTransformation(ObjectParameters objectParameters, ViewportParameters viewportParameters)
    {
        //TODO these might need to be swapped.
        AffineTransform affineTransform = buildWorldToViewTransformation(viewportParameters);
        affineTransform.concatenate(buildObjectToWorldTransformation(objectParameters));
        return affineTransform;
    }

    private static AffineTransform buildViewToObjectTransformation(ObjectParameters objectParameters, ViewportParameters viewportParameters)
    {
        //TODO these might need to be swapped.
        AffineTransform affineTransform = buildWorldToObjectTransformation(objectParameters);
        affineTransform.concatenate(buildViewToWorldTransformation(viewportParameters));
        return affineTransform;
    }

    private static AffineTransform buildRotationTransform(double rotation)
    {
        return new AffineTransform(Math.cos(rotation), Math.sin(rotation), -Math.sin(rotation), Math.cos(rotation), 0.0, 0.0);
    }

    private static AffineTransform buildTranslationTransform(double x, double y)
    {
        //return AffineTransform.getTranslateInstance(x, y);
        return new AffineTransform(1.0, 0.0, 0.0, 1.0, x, y);
    }

    private static AffineTransform buildScalingTransform(double scaling)
    {
        //return AffineTransform.getScaleInstance(x, y);
        return new AffineTransform(scaling, 0.0, 0.0, scaling, 0.0, 0.0);
    }

    private static Point2D.Double applyTransformationToPoint(AffineTransform affineTransform, Point2D.Double point)
    {
        return (Point2D.Double) affineTransform.transform(point, new Point2D.Double());
    }
}
