package cs355.view.drawing;

import cs355.model.drawing.Ellipse;
import cs355.model.drawing.Shape;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * An objects that represents an ellipse. This takes care of all of the drawing and manipulation of ellipses on the screen.
 */
public class DrawableEllipse extends DrawableRectangle
{
    private Ellipse ellipse;

    protected DrawableEllipse()
    {
        super();
    }

    public DrawableEllipse(Shape ellipse)
    {
        super(ellipse);
    }

    public DrawableEllipse(Color color)
    {
        super(color);
    }

    @Override
    public void drawShape(Graphics2D graphics)
    {
        //Point2D.Double upperLeftPoint = calculateUpperLeftPoint();
        graphics.fillOval((int) -getWidth() / 2, (int) -getHeight() / 2, (int) getWidth(), (int) getHeight());
    }

    @Override
    protected void calculatePointsFromShape(Shape shape)
    {
        Ellipse ellipse = (Ellipse) shape;
        setStartPoint(calculateUpperLeftFromCenter(ellipse.getCenter(), ellipse.getWidth(), ellipse.getHeight()));
        setEndPoint(calculateLowerRightFromCenter(ellipse.getCenter(), ellipse.getWidth(), ellipse.getHeight()));
    }

    protected Point2D.Double calculateLowerRightFromCenter(Point2D.Double center, double width, double height)
    {
        double x = (center.x + (width / 2.0));
        double y = (center.y + (height / 2.0));
        return new Point2D.Double(x, y);
    }

    @Override
    public Shape getModelShape()
    {
        Ellipse ellipse = new Ellipse(getColor(), calculateCenterFromUpperLeft(), getWidth(), getHeight());
        if (getRotation() != 0.0)
            ellipse.setRotation(getRotation());
        return ellipse;
    }
}
