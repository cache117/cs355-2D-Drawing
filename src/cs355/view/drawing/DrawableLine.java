package cs355.view.drawing;

import cs355.GUIFunctions;
import cs355.model.drawing.Line;
import cs355.model.drawing.Shape;
import cs355.view.DrawingParameters;
import cs355.view.ObjectParameters;
import cs355.view.drawing.util.Transform;

import java.awt.*;
import java.awt.geom.AffineTransform;
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
    public void drawShape(DrawingParameters drawingParameters)
    {
        Point2D.Double start = getStartPoint();
        Point2D.Double end = getEndPoint();
        drawingParameters.graphics2D.drawLine((int) start.x, (int) start.y, (int) end.x, (int) end.y);
    }

    @Override
    protected void calculatePointsFromShape(Shape shape)
    {
        Line line = (Line) shape;
        setStartPoint(getCenterPoint());
        setEndPoint(Transform.getWorldPointFromObjectPoint(line.getEnd(), new ObjectParameters(getCenterPoint(), getRotation())));
    }

    @Override
    public Shape getModelShape()
    {
        Point2D.Double start = getStartPoint();
        Point2D.Double end = Transform.getObjectPointFromWorldPoint(getEndPoint(), new ObjectParameters(getCenterPoint(), getRotation()));
        return new Line(getColor(), start, end);
    }

    @Override
    protected void drawShapeHandle(Graphics2D graphics2D)
    {
        super.drawShapeHandle(graphics2D);
        Point2D.Double endHandle = getEndHandleCenterPoint();
        graphics2D.drawOval((int) endHandle.x, (int) endHandle.y, HANDLE_DIAMETER, HANDLE_DIAMETER);
    }

    @Override
    public void drawShapeOutline(DrawingParameters drawingParameters)
    {

    }

    @Override
    protected void applyTransformationToGraphics(DrawingParameters drawingParameters)
    {
        AffineTransform affineTransform = new AffineTransform();
        drawingParameters.graphics2D.setTransform(affineTransform);
    }

    @Override
    public Point2D.Double getHandleCenterPoint()
    {
        return new Point2D.Double(0,0);
    }

    private Point2D.Double getEndHandleCenterPoint()
    {
        return Transform.getObjectPointFromWorldPoint(getEndPoint(), new ObjectParameters(getCenterPoint(), getRotation()));
    }
}
