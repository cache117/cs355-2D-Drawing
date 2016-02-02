package cs355.view.drawing;


import java.awt.*;
import java.awt.geom.Point2D;

import cs355.model.drawing.CS355Drawing;
import cs355.model.drawing.DrawingModel;
import cs355.model.drawing.Shape;

/**
 * Drawable shapes represent the shapes in the model. The respective subclasses take care of the drawing/manipulation of the corresponding shape.
 */
public abstract class DrawableShape
{
    /* Every shape (except for Triangle) uses two points to draw, independent of how they are drawn */
    private Point2D.Double startPoint, endPoint, centerPoint;
    private Color color;
    private int numberOfActualPoints;
    private Shape shape;

    public DrawableShape()
    {
        numberOfActualPoints = 0;
    }

    public DrawableShape(Shape shape)
    {
        this();
        this.shape = shape;
        this.color = shape.getColor();
        this.centerPoint = shape.getCenter();
        this.calculatePointsFromShape(shape);
        this.setNumberOfActualPoints(getExpectedPoints());
    }

    public DrawableShape(Color color)
    {
        this();
        this.color = color;
    }

    public void draw(Graphics2D graphics) throws InvalidPointsException
    {
        graphics.setColor(getColor());
        this.drawShape(graphics);
    }

    protected abstract void drawShape(Graphics2D graphics);

    /**
     * Sets the beginning and end points from the given shape.
     *
     * @param shape the shape to determine the points of.
     */
    protected abstract void calculatePointsFromShape(Shape shape);

    /**
     * The number of points that must be in the DrawableShape before drawing.
     *
     * @return the number of points that must be in the DrawableShape before drawing
     */
    protected int getExpectedPoints()
    {
        return 2;
    }

    /**
     * Clears the points that are in the shape, including the number of actual points.
     */
    public void clearPoints()
    {
        startPoint = null;
        endPoint = null;
        numberOfActualPoints = 0;
    }

    /**
     * Gets the model that represents this DrawableShape.
     *
     * @return the model that represents this DrawableShape.
     */
    public abstract Shape getModelShape();

    protected Point2D.Double getStartPoint()
    {
        return startPoint;
    }

    public void setStartPoint(Point2D.Double startPoint)
    {
        this.startPoint = startPoint;
    }

    protected Point2D.Double getEndPoint()
    {
        return endPoint;
    }

    public void setEndPoint(Point2D.Double endPoint)
    {
        this.endPoint = endPoint;
    }

    protected Point2D.Double getCenterPoint()
    {
        return centerPoint;
    }

    protected void setCenterPoint(Point2D.Double centerPoint)
    {
        this.centerPoint = centerPoint;
    }

    public int getNumberOfActualPoints()
    {
        return numberOfActualPoints;
    }

    public void setNumberOfActualPoints(int number)
    {
        numberOfActualPoints = number;
    }

    protected void incrementNumberOfActualPoints()
    {
        numberOfActualPoints++;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public void updateEndPoint(Point2D.Double point, CS355Drawing model)
    {
        setEndPoint(point);
        ((DrawingModel) model).setShape(0, this.getModelShape());
    }

    protected Shape getShape()
    {
        return shape;
    }

    public abstract void drawOutline(Graphics2D graphics2D);
}
