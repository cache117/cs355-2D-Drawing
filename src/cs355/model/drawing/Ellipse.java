package cs355.model.drawing;

import cs355.view.drawing.ShapeUtilities;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Add your ellipse code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Ellipse extends Shape
{

    // The width of this shape.
    private double width;

    // The height of this shape.
    private double height;

    /**
     * Basic constructor that sets all fields.
     *
     * @param color  the color for the new shape.
     * @param center the center of the new shape.
     * @param width  the width of the new shape.
     * @param height the height of the new shape.
     */
    public Ellipse(Color color, Point2D.Double center, double width, double height)
    {

        // Initialize the superclass.
        super(color, center);

        // Set fields.
        this.width = width;
        this.height = height;
    }

    /**
     * Getter for this shape's width.
     *
     * @return this shape's width as a double.
     */
    public double getWidth()
    {
        return width;
    }

    /**
     * Setter for this shape's width.
     *
     * @param width the new width.
     */
    public void setWidth(double width)
    {
        this.width = width;
    }

    /**
     * Getter for this shape's height.
     *
     * @return this shape's height as a double.
     */
    public double getHeight()
    {
        return height;
    }

    /**
     * Setter for this shape's height.
     *
     * @param height the new height.
     */
    public void setHeight(double height)
    {
        this.height = height;
    }

    /**
     * Add your code to do an intersection test here. You shouldn't need the tolerance.
     *
     * @param worldPoint = the point to test against.
     * @param tolerance  = the allowable tolerance.
     * @return true if pt is in the shape, false otherwise.
     */
    @Override
    public boolean pointInShape(Point2D.Double worldPoint, double tolerance)
    {
        if (rotation == 0)
        {
            if (ShapeUtilities.pointInBoundingBox(worldPoint, getCenter(), width, height))
            {
                if (ShapeUtilities.pointInBoundingCircle(worldPoint, getCenter(), getLargerRadius()))
                    return ShapeUtilities.pointInEllipse(worldPoint, getCenter(), width / 2, height / 2);
                else
                    return false;
            } else
                return false;
        } else
        {
            if (ShapeUtilities.pointInBoundingCircle(worldPoint, getCenter(), getLargerRadius()))
            {
                Point2D.Double objectPoint = getObjectCoordinatePoint(worldPoint);
                return ShapeUtilities.pointInEllipse(objectPoint, new Point2D.Double(0, 0), width / 2, height / 2);
            } else
                return false;
        }
    }

    /**
     * Gets the larger of the two radii
     *
     * @return the larger of the two radii
     */
    private double getLargerRadius()
    {
        return (height < width) ? width : height;
    }

}
