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
    public void drawShape(Graphics2D graphics, boolean selected)
    {
        Point2D.Double upperLeftPoint = calculateUpperLeftPoint();
        graphics.fillOval((int) upperLeftPoint.x, (int) upperLeftPoint.y, (int) getWidth(), (int) getHeight());
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
        int x = (int) (center.x + (width / 2));
        int y = (int) (center.y + (height / 2));
        return new Point2D.Double(x, y);
    }

    @Override
    public Shape getModelShape()
    {
        return new Ellipse(getColor(), calculateCenterFromUpperLeft(), getWidth(), getHeight());
    }
}
