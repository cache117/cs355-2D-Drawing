package cs355.view.drawing;

import cs355.GUIFunctions;
import cs355.model.drawing.*;
import cs355.model.drawing.Shape;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * An objects that represents a triangle. This takes care of all of the drawing and manipulation of triangles on the screen.
 */
public class DrawableTriangle extends DrawableShape
{
    private Point2D.Double middlePoint, relativeStartPoint, relativeMiddlePoint, relativeEndPoint;

    public DrawableTriangle(Shape triangle)
    {
        super(triangle);
    }

    public DrawableTriangle(Color color)
    {
        super(color);
        GUIFunctions.printf("Click for each vertex.");
    }

    @Override
    public void drawShape(Graphics2D graphics)
    {
        graphics.fillPolygon(getXPoints(), getYPoints(), getExpectedPoints());
    }

    @Override
    protected void calculatePointsFromShape(Shape shape)
    {
        Triangle triangle = (Triangle) shape;
        setStartPoint(triangle.getA());
        setMiddlePoint(triangle.getB());
        setEndPoint(triangle.getC());
    }

    @Override
    protected int getExpectedPoints()
    {
        return 3;
    }

    @Override
    public Shape getModelShape()
    {
        return new Triangle(getColor(), getCenterPoint(), getStartPoint(), getMiddlePoint(), getEndPoint());
    }

    @Override
    public void drawOutline(Graphics2D graphics2D)
    {

    }

    public void addPoint(Point2D.Double point, CS355Drawing model)
    {
        switch (getNumberOfActualPoints())
        {
            case 0:
                setStartPoint(point);
                setMiddlePoint(point);
                setEndPoint(point);
                setNumberOfActualPoints(1);

                model.addShape(getModelShape());
                GUIFunctions.printf("Click to set second vertex.");
                break;
            case 1:
                setMiddlePoint(point);
                setNumberOfActualPoints(2);

                ((DrawingModel) model).setShape(0, getModelShape());
                GUIFunctions.printf("Click to set third vertex.");
                break;
            case 2:
                setEndPoint(point);
                setNumberOfActualPoints(3);

                ((DrawingModel) model).setShape(0, getModelShape());
                this.clearPoints();
                GUIFunctions.printf("Click to set first vertex.");
                break;
            default:
                assert false;
        }
    }


    private Point2D.Double getMiddlePoint()
    {
        return middlePoint;
    }

    private void setMiddlePoint(Point2D.Double middlePoint)
    {
        this.middlePoint = middlePoint;
    }

    private int[] getXPoints()
    {
        int[] xPoints = new int[getExpectedPoints()];
        xPoints[0] = (int) getStartPoint().x;
        xPoints[1] = (int) getMiddlePoint().x;
        xPoints[2] = (int) getEndPoint().x;
        return xPoints;
    }

    private int[] getYPoints()
    {
        int[] yPoints = new int[getExpectedPoints()];
        yPoints[0] = (int) getStartPoint().y;
        yPoints[1] = (int) getMiddlePoint().y;
        yPoints[2] = (int) getEndPoint().y;
        return yPoints;
    }
}
