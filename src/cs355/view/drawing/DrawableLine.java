package cs355.view.drawing;

import cs355.GUIFunctions;
import cs355.model.drawing.CS355Drawing;
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
    public void drawShape(Graphics2D graphics, boolean selected)
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
}
