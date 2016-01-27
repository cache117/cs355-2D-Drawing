package cs355.view.drawing;

import cs355.model.drawing.Circle;
import cs355.model.drawing.Shape;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * An objects that represents a circle. This takes care of all of the drawing and manipulation of circles on the screen.
 */
public class DrawableCircle extends DrawableEllipse
{
    public DrawableCircle(Shape circle)
    {
        super(circle);
    }

    public DrawableCircle(Color color)
    {
        super(color);
    }

    @Override
    public Shape getModelShape()
    {
        return new Circle(getColor(), calculateCenterFromUpperLeft(), getHeight() / 2);
    }

    @Override
    public void setEndPoint(Point2D.Double endPoint)
    {
        Point2D.Double symmetricEndPoint = ShapeUtilities.calculateSymmetricPoint(getStartPoint(), endPoint);
        super.setEndPoint(symmetricEndPoint);
    }

    @Override
    protected void calculatePointsFromShape(Shape shape)
    {
        Circle circle = (Circle) getShape();
        double diameter = 2.0 * circle.getRadius();
        setStartPoint(calculateUpperLeftFromCenter(circle.getCenter(), diameter, diameter));
        setEndPoint(calculateLowerRightFromCenter(circle.getCenter(), diameter, diameter));
    }
}
