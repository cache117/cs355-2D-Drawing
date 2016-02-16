package cs355.model.drawing;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * This is the base class for all of your shapes.
 * Make sure they all extend this class.
 */
public abstract class Shape
{
    protected static final double NO_ROTATION = 0.0;
    // The color of this shape.
    protected Color color;

    // The center of this shape.
    protected Point2D.Double center, oldCenter;

    // The rotation of this shape.
    protected double rotation, oldRotation;

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
        rotation = NO_ROTATION;
        this.selected = false;
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
        this.oldRotation = this.rotation;
        this.rotation = rotation;
    }

    /**
     * Used to test for whether the user clicked inside a shape or not.
     *
     * @param worldPoint = the point to test whether it's in the shape or not.
     * @return true if pt is in the shape, false otherwise.
     */
    public abstract boolean pointInShape(Point2D.Double worldPoint);

    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }

    public boolean isSelected()
    {
        return selected;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Shape shape = (Shape) o;
//        if (!rotationSimilar(shape.rotation))
//            return false;
//        if (!centerSimilar(shape.center))
//            return false;
        if (!color.equals(shape.color))
            return false;
        return true;
    }

    private boolean rotationSimilar(double rotation)
    {
        return (this.rotation == rotation) || (this.oldRotation == rotation);
    }

    private boolean centerSimilar(Point2D.Double center)
    {
        return (this.center.equals(center)) || (this.oldCenter.equals(center));
    }

    @Override
    public int hashCode()
    {
        int result;
        result = color.hashCode();
        result = 31 * result + center.hashCode();
        result = 31 * result + (selected ? 1 : 0);
        return result;
    }
}
