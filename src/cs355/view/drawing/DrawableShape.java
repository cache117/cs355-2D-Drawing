package cs355.view.drawing;


import java.awt.*;
import java.awt.geom.Point2D;

import cs355.model.drawing.CS355Drawing;
import cs355.model.drawing.DrawingModel;
import cs355.model.drawing.Shape;
import cs355.view.drawing.util.Transform;

/**
 * Drawable shapes represent the shapes in the model. The respective subclasses take care of the drawing/manipulation of the corresponding shape.
 */
public abstract class DrawableShape
{
    public static final int HANDLE_DIAMETER = 16, HANDLE_DISTANCE_FROM_OUTLINE = 20, HANDLE_RADIUS = (HANDLE_DIAMETER / 2), HANDLE_CENTER_DISTANCE_FROM_OUTLINE = HANDLE_DISTANCE_FROM_OUTLINE - HANDLE_RADIUS;
    /* Every shape (except for Triangle) uses two points to draw, independent of how they are drawn */
    private Point2D.Double startPoint, endPoint, centerPoint;
    private Color color;
    private int numberOfActualPoints;
    private double rotation;

    public DrawableShape()
    {
        numberOfActualPoints = 0;
    }

    public DrawableShape(Shape shape)
    {
        this(shape.getColor(), shape.getCenter(), shape.getRotation());
        this.calculatePointsFromShape(shape);
        this.setNumberOfActualPoints(getExpectedPoints());
    }

    public DrawableShape(Color color)
    {
        this();
        this.color = color;
    }

    private DrawableShape(Color color, Point2D.Double centerPoint, double rotation)
    {
        this(color);
        this.centerPoint = centerPoint;
        this.rotation = rotation;
    }

    public void draw(Graphics2D graphics2D)
    {
        graphics2D.setColor(getColor());
        this.applyTransformationToGraphics(graphics2D);
        this.drawShape(graphics2D);
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

    public Point2D.Double getCenterPoint()
    {
        return centerPoint;
    }

    public void setCenterPoint(Point2D.Double centerPoint)
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

    public double getRotation()
    {
        return rotation;
    }

    public void setRotation(double rotation)
    {
        this.rotation = rotation;
    }

    public void updateEndPoint(Point2D.Double point, CS355Drawing model)
    {
        setEndPoint(point);
        ((DrawingModel) model).setShape(0, this.getModelShape());
    }

    public void drawOutline(Graphics2D graphics2D)
    {
        graphics2D.setColor(Color.RED);
        this.applyTransformationToGraphics(graphics2D);
        this.drawShapeOutline(graphics2D);
        this.drawShapeHandle(graphics2D);
    }

    protected void drawShapeHandle(Graphics2D graphics2D)
    {
        Point2D.Double handleCenter = getHandleCenterPoint();
        graphics2D.drawOval((int) handleCenter.x - HANDLE_RADIUS, (int) handleCenter.y - HANDLE_RADIUS, HANDLE_DIAMETER, HANDLE_DIAMETER);
    }

    protected abstract void drawShapeOutline(Graphics2D graphics2D);

    protected void applyTransformationToGraphics(Graphics2D graphics2D)
    {
        Transform.applyTransformationToGraphics(graphics2D, rotation, centerPoint);
    }

    public abstract Point2D.Double getHandleCenterPoint();
}
