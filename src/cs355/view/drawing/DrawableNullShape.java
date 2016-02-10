package cs355.view.drawing;

import cs355.model.drawing.NullShape;
import cs355.model.drawing.Shape;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * A DrawableShape that represents null.
 */
public class DrawableNullShape extends DrawableShape
{
    @Override
    protected void drawShape(Graphics2D graphics)
    {

    }

    @Override
    protected void calculatePointsFromShape(Shape shape)
    {

    }

    @Override
    public Shape getModelShape()
    {
        return new NullShape();
    }

    @Override
    public void drawShapeOutline(Graphics2D graphics2D)
    {

    }

    @Override
    public Point2D.Double getHandleCenterPoint()
    {
        return new Point2D.Double(0,0);
    }

    @Override
    public void drawOutline(Graphics2D graphics2D)
    {

    }

    @Override
    protected void drawShapeHandle(Graphics2D graphics2D)
    {

    }
}
