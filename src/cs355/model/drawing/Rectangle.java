package cs355.model.drawing;

import cs355.view.drawing.ShapeUtilities;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Add your rectangle code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Rectangle extends Shape
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
    public Rectangle(Color color, Point2D.Double center, double width, double height)
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
     * Add your code to do an intersection test
     * here. You shouldn't need the tolerance.
     *
     * @param worldPoint = the point to test against.
     * @param tolerance  = the allowable tolerance.
     * @return true if pt is in the shape, false otherwise.
     */
    @Override
    public boolean pointInShape(Point2D.Double worldPoint, double tolerance)
    {
        if (getRotation() == 0)
            return ShapeUtilities.pointInBoundingBox(worldPoint, getCenter(), getWidth(), getHeight());
        else
        {
            /*//Expanded bounding box
            if (!ShapeUtilities.pointInBoundingBox(worldPoint, getCenter(), getWidth(), getHeight()))
            {
                return false;
            } else
            {*/
            //Check in more depth
            return ShapeUtilities.pointInBoundingBox(getObjectCoordinatePoint(worldPoint), new Point2D.Double(0, 0), getWidth(), getHeight());
            //}
        }
    }

}
