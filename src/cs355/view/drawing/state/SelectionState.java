package cs355.view.drawing.state;

import cs355.GUIFunctions;
import cs355.model.drawing.*;
import cs355.model.drawing.Shape;
import cs355.view.drawing.DrawableNullShape;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

/**
 * Created by cstaheli on 1/23/2016.
 */
public class SelectionState extends DrawingState
{
    public SelectionState()
    {
        super(new DrawableNullShape());
        GUIFunctions.printf("Select a shape to modify.");
    }

    @Override
    public void mouseClicked(Point2D.Double point, CS355Drawing model)
    {
        List<Shape> shapes = model.getShapes();
        boolean shapeFound = false;
        for(Shape shape: shapes)
        {
            if (shape.pointInShape(point, shape.getTolerance()) && !shapeFound)
            {
                shape.setSelected(true);
                shapeFound = true;
            }
            else
                shape.setSelected(false);

        }
    }

    @Override
    public void mousePressed(Point2D.Double point, CS355Drawing model)
    {

    }

    @Override
    public void mouseReleased(Point2D.Double point, CS355Drawing model)
    {

    }

    @Override
    public void mouseDragged(Point2D.Double point, CS355Drawing model)
    {

    }
}
