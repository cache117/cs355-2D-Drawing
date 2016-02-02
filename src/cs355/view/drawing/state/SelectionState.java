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
    private boolean shapeSelected;
    public SelectionState()
    {
        super(new DrawableNullShape());
        shapeSelected = false;
        GUIFunctions.printf("Select a shape to modify.");
    }

    @Override
    public void mouseClicked(Point2D.Double point, CS355Drawing model)
    {
        List<Shape> shapes = model.getShapes();
        boolean shapeSelected = false;
        for(Shape shape: shapes)
        {
            if (shape.pointInShape(point, shape.getTolerance()) && !shapeSelected)
            {
                shape.setSelected(true);
                shapeSelected = true;
                GUIFunctions.printf("Shape Selected");
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
