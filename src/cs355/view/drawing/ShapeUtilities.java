package cs355.view.drawing;

import cs355.GUIFunctions;

import java.awt.geom.Point2D;

/**
 * Created by cstaheli on 1/20/2016.
 */
public class ShapeUtilities
{
    public static Point2D.Double calculateSymmetricPoint(Point2D.Double startPoint, Point2D.Double draggedPoint)
    {
        int startX = (int) startPoint.x;
        int startY = (int) startPoint.y;
        int draggedX = (int) draggedPoint.x;
        int draggedY = (int) draggedPoint.y;
        int xDistance = draggedX - startX;
        int yDistance = draggedY - startY;

        Point2D.Double symmetricPoint;
        int comparison = Integer.compare(Math.abs(xDistance), Math.abs(yDistance));

        if (xDistance < 0)
        {
            if (yDistance < 0)
                symmetricPoint = getPositiveSymmetricPoint(comparison, startX, draggedX, xDistance, startY, draggedY, yDistance);
            else
                symmetricPoint = getNegativeSymmetricPoint(comparison, startX, draggedX, xDistance, startY, draggedY, yDistance);
        } else if (xDistance > 0)
        {
            if (yDistance < 0)
                symmetricPoint = getNegativeSymmetricPoint(comparison, startX, draggedX, xDistance, startY, draggedY, yDistance);
            else
                symmetricPoint = getPositiveSymmetricPoint(comparison, startX, draggedX, xDistance, startY, draggedY, yDistance);

        } else
            symmetricPoint = draggedPoint;

        return symmetricPoint;
    }

    private static Point2D.Double getPositiveSymmetricPoint(int comparison, int startX, int draggedX, int xDistance, int startY, int draggedY, int yDistance)
    {
        Point2D.Double symmetricPoint;
        if (comparison < 0)
            symmetricPoint = new Point2D.Double(startX + yDistance, draggedY);
        else
            symmetricPoint = new Point2D.Double(draggedX, startY + xDistance);
        return symmetricPoint;
    }

    private static Point2D.Double getNegativeSymmetricPoint(int comparison, int startX, int draggedX, int xDistance, int startY, int draggedY, int yDistance)
    {
        Point2D.Double symmetricPoint;
        if (comparison < 0)
            symmetricPoint = new Point2D.Double(startX - yDistance, draggedY);
        else
            symmetricPoint = new Point2D.Double(draggedX, startY - xDistance);
        return symmetricPoint;
    }

    private static double getLowerXBoundOfBox(double centerX, double width)
    {
        return centerX - (width / 2.0);
    }

    private static double getUpperXBoundOfBox(double centerX, double width)
    {
        return centerX + (width / 2.0);
    }

    private static double getLowerYBoundOfBox(double centerY, double height)
    {
        return centerY - (height / 2.0);
    }

    private static double getUpperYBoundOfBox(double centerY, double height)
    {
        return centerY + (height / 2.0);
    }

    public static boolean pointInBoundingBox(Point2D.Double point, Point2D.Double center, double width, double height)
    {
        double lowerXBound = getLowerXBoundOfBox(center.x, width);
        double upperXBound = getUpperXBoundOfBox(center.x, width);
        double lowerYBound = getLowerYBoundOfBox(center.y, height);
        double upperYBound = getUpperYBoundOfBox(center.y, height);
        return ((point.x >= lowerXBound && point.x <= upperXBound) && (point.y >= lowerYBound && point.y <= upperYBound));
    }

    public static boolean pointInBoundingCircle(Point2D.Double point, Point2D.Double center, double radius)
    {
        double distance = Point2D.distance(point.x, point.y, center.x, center.y);
        return distance <= radius;
    }

    public static boolean pointInEllipse(Point2D.Double point, Point2D.Double center, double xRadius, double yRadius)
    {
        return (Math.pow(((point.x - center.x) / xRadius), 2) + Math.pow(((point.y - center.y) / yRadius), 2) <= 1);
    }
}