package cs355.view;

import cs355.GUIFunctions;
import cs355.controller.DrawingController;
import cs355.model.drawing.*;
import cs355.model.drawing.Shape;
import cs355.view.drawing.DrawableNullShape;
import cs355.view.drawing.DrawableShape;
import cs355.view.drawing.DrawableShapeFactory;
import cs355.view.drawing.InvalidPointsException;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The View in the MVC model. Takes care of all the drawing when the Model changes.
 *
 * @see Observer
 * @see Observable
 * @see DrawingModel
 * @see DrawingController
 */
public class DrawingViewer implements ViewRefresher
{
    private static final Logger LOGGER = Logger.getLogger(DrawingViewer.class.getName());

    private DrawingModel model;
    // private List<Shape> specificUpdatedShapes;

    public DrawingViewer()
    {
        model = new DrawingModel();
    }

    /* begin ViewRefresher methods */
    @Override
    public void refreshView(Graphics2D graphics2D)
    {
        //Draw selection handles lastx
        List<Shape> shapes = model.getShapesReversed();

        DrawableShape drawableShape = new DrawableNullShape();
        for (Shape shape : shapes)
        {
            drawableShape = DrawableShapeFactory.createDrawableShape(shape);
            try
            {
                drawableShape.draw(graphics2D);
            } catch (InvalidPointsException e)
            {
                LOGGER.log(Level.SEVERE, "Failed to create Drawable Shape for " + shape.toString(), e);
            }
        }

        //draw on graphics2D
        //refresh the view with graphics2D
    }

    /* end ViewRefresher methods */

    /* begin Observer methods */
    @Override
    public void update(Observable o, Object specificShapes)
    {
        model = (DrawingModel) o;
        GUIFunctions.refresh();
    }
    /* end Observer methods */


}
