package cs355.model.drawing;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * This is the base class for all of your shapes.
 * Make sure they all extend this class.
 */
public abstract class Shape
{
    // The color of this shape.
    protected Color color;

    // The center of this shape.
    protected Point2D.Double center;

    // The rotation of this shape.
    protected double rotation;

    private boolean selected;

    /**
     * Basic constructor that sets fields.
     * It initializes rotation to 0.
     *
     * @param color  the color for the new shape.
     * @param center the center point of the new shape.
     */
    public Shape(Color color, Point2D.Double center)
    {
        this.color = color;
        this.center = center;
        rotation = 0.0;
    }

    /**
     * Getter for this shape's color.
     *
     * @return the color of this shape.
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * Setter for this shape's color
     *
     * @param color the new color for the shape.
     */
    public void setColor(Color color)
    {
        this.color = color;
    }

    /**
     * Getter for this shape's center.
     *
     * @return this shape's center as a Java point.
     */
    public Point2D.Double getCenter()
    {
        return center;
    }

    /**
     * Setter for this shape's center.
     *
     * @param center the new center as a Java point.
     */
    public void setCenter(Point2D.Double center)
    {
        this.center = center;
    }

    /**
     * Getter for this shape's rotation.
     *
     * @return the rotation as a double.
     */
    public double getRotation()
    {
        return rotation;
    }

    /**
     * Setter for this shape's rotation.
     *
     * @param rotation the new rotation.
     */
    public void setRotation(double rotation)
    {
        this.rotation = rotation;
    }

    /**
     * Used to test for whether the user clicked inside a shape or not.
     *
     * @param worldPoint        = the point to test whether it's in the shape or not.
     * @param tolerance = the tolerance for testing. Mostly used for lines.
     * @return true if pt is in the shape, false otherwise.
     */
    public abstract boolean pointInShape(Point2D.Double worldPoint, double tolerance);

    public double getTolerance()
    {
        return 0.0;
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }

    public boolean isSelected()
    {
        return selected;
    }

    protected Point2D.Double getObjectCoordinatePoint(Point2D.Double worldCoordinatePoint)
    {
        AffineTransform worldToObject = new AffineTransform();
        worldToObject.rotate(rotation, center.x, center.y);
        Point2D.Double objectPoint = new Point2D.Double();
        return (Point2D.Double) worldToObject.transform(worldCoordinatePoint, objectPoint);
    }
}