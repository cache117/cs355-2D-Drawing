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
}
