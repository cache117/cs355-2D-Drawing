package cs355.view.drawing;

import cs355.GUIFunctions;
import cs355.model.drawing.*;
import cs355.model.drawing.Rectangle;
import cs355.model.drawing.Shape;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * An objects that represents a rectangle. This takes care of all of the drawing and manipulation of rectangles on the screen.
 */
public class DrawableRectangle extends DrawableShape
{
    public DrawableRectangle(Shape rectangle)
    {
        super(rectangle);
    }

    protected DrawableRectangle()
    {
        super();
    }

    public DrawableRectangle(Color color)
    {
        super(color);
        GUIFunctions.printf("Click and drag from corner to corner.");
    }

    @Override
    public void drawShape(Graphics2D graphics, boolean selected)
    {
        Point2D.Double upperLeft = calculateUpperLeftPoint();
        graphics.fillRect((int) upperLeft.getX(), (int) upperLeft.getY(), (int) getWidth(), (int) getHeight());
        if (selected)
            graphics.drawRect((int) upperLeft.getX() - 1, (int) upperLeft.getY() - 1, (int) getWidth() + 1, (int) getHeight() + 1);
    }

    protected Point2D.Double calculateUpperLeftPoint()
    {
        Point2D.Double startPoint = getStartPoint();
        Point2D.Double endPoint = getEndPoint();
        int startX = (int) startPoint.x;
        int startY = (int) startPoint.y;
        int endX = (int) endPoint.x;
        int endY = (int) endPoint.y;

        if (startX > endX)
        {
            if (startY >= endY)
                return endPoint;
            else
                return new Point2D.Double(endX, startY);
        } else if (startX == endX)
        {
            if (startY > endY)
                return endPoint;
            else
                return startPoint;
        } else
        {
            if (startY > endY)
                return new Point2D.Double(startX, endY);
            else
                return startPoint;
        }
    }

    protected Point2D.Double calculateUpperLeftFromCenter(Point2D.Double center, double width, double height)
    {
        int x = (int) (center.x - (width / 2));
        int y = (int) (center.y - (height / 2));
        return new Point2D.Double(x, y);
    }

    protected Point2D.Double calculateLowerRightFromUpperLeft(Point2D.Double upperLeft, double width, double height)
    {
        int lowerRightX = (int) (upperLeft.x + width);
        int lowerRightY = (int) (upperLeft.y + height);
        return new Point2D.Double(lowerRightX, lowerRightY);
    }

    protected Point2D.Double calculateCenterFromUpperLeft()
    {
        Point2D.Double upperLeft = calculateUpperLeftPoint();
        int x = (int) (upperLeft.x + getWidth() / 2);
        int y = (int) (upperLeft.y + getHeight() / 2);
        return new Point2D.Double(x, y);
    }

    protected double getWidth()
    {
        Point2D.Double startPoint = getStartPoint();
        double startX = startPoint.x;
        double startY = startPoint.y;
        double endX = getEndPoint().x;

        return Math.abs(Point2D.distance(startX, startY, endX, startY));
    }

    protected double getHeight()
    {
        Point2D.Double startPoint = getStartPoint();
        double startX = startPoint.x;
        double startY = startPoint.y;
        double endY = getEndPoint().y;

        return Math.abs(Point2D.distance(startX, startY, startX, endY));
    }

    @Override
    protected void calculatePointsFromShape(Shape shape)
    {
        Rectangle rectangle = (Rectangle) shape;
        double width = rectangle.getWidth();
        double height = rectangle.getHeight();
        Point2D.Double upperLeft = calculateUpperLeftFromCenter(getCenterPoint(), width, height);
        setStartPoint(upperLeft);
        setEndPoint(calculateLowerRightFromUpperLeft(upperLeft, width, height));
    }

    @Override
    public Shape getModelShape()
    {
        return new Rectangle(getColor(), calculateCenterFromUpperLeft(), getWidth(), getHeight());
    }
}
