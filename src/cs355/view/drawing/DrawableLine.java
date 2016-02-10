package cs355.view.drawing;

import cs355.GUIFunctions;
import cs355.model.drawing.Line;
import cs355.model.drawing.Shape;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * An objects that represents a line. This takes care of all of the drawing and manipulation of lines on the screen.
 */
public class DrawableLine extends DrawableShape
{
    public DrawableLine(Shape line)
    {
        super(line);
    }

    public DrawableLine(Color color)
    {
        super(color);
        GUIFunctions.printf("Click and drag from start of line to finish.");
    }

    @Override
    public void drawShape(Graphics2D graphics)
    {
        Point2D.Double start = getStartPoint();
        Point2D.Double end = getEndPoint();
        graphics.drawLine((int) start.x, (int) start.y, (int) end.x, (int) end.y);
    }

    @Override
    protected void calculatePointsFromShape(Shape shape)
    {
        Line line = (Line) shape;
        setStartPoint(getCenterPoint());
        setEndPoint(line.getEnd());
        setColor(shape.getColor());
    }

    @Override
    public Shape getModelShape()
    {
        Point2D.Double start = getStartPoint();
        Point2D.Double end = getEndPoint();
        return new Line(getColor(), start, end);
    }

    @Override
    protected void drawShapeHandle(Graphics2D graphics2D)
    {

    }

    @Override
    public void drawShapeOutline(Graphics2D graphics2D)
    {

    }

    @Override
    protected void applyTransformationToGraphics(Graphics2D graphics2D)
    {

    }

    @Override
    public Point2D.Double getHandleCenterPoint()
    {
        return new Point2D.Double(0,0);
    }

    public Point2D.Double getNonCenterHandleCenterPoint()
    {
        return new Point2D.Double(0,0);
    }
}
