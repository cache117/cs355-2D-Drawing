package cs355.view;

import cs355.GUIFunctions;
import cs355.controller.DrawingController;
import cs355.model.drawing.*;
import cs355.model.drawing.Shape;
import cs355.view.drawing.DrawableNullShape;
import cs355.view.drawing.DrawableShape;
import cs355.view.drawing.util.DrawableShapeFactory;

import java.awt.*;
import java.util.*;
import java.util.List;
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
    private ViewportParameters viewportParameters;
    // private List<Shape> specificUpdatedShapes;

    public DrawingViewer()
    {
        model = new DrawingModel();
    }

    /* begin ViewRefresher methods */
    @Override
    public void refreshView(Graphics2D graphics2D)
    {
        //Draw selection handles last
        List<Shape> shapes = model.getShapesReversed();

        DrawableShape selectedShape = new DrawableNullShape();
        DrawingParameters drawingParameters = new DrawingParameters(graphics2D, viewportParameters);
        for (Shape shape : shapes)
        {
            DrawableShape drawableShape = DrawableShapeFactory.createDrawableShape(shape);
            if (shape.isSelected())
            {
                selectedShape = drawableShape;
            }
            drawableShape.draw(drawingParameters);
        }
        selectedShape.drawOutline(drawingParameters);
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

    public void setViewportParameters(ViewportParameters viewportParameters)
    {
        this.viewportParameters = viewportParameters;
    }
}
