package cs355.view.drawing.state;

import cs355.GUIFunctions;
import cs355.controller.DrawingController;
import cs355.model.drawing.CS355Drawing;
import cs355.view.DrawingViewer;
import cs355.view.ViewRefresher;
import cs355.view.ViewportParameters;
import cs355.view.drawing.DrawableNullShape;
import cs355.view.drawing.DrawableShape;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by cstaheli on 1/23/2016.
 */
public abstract class DrawingState
{
    private DrawableShape drawableShape;
    private boolean shapeSelected;


    /**
     * Creates a default DrawingState
     */
    DrawingState()
    {
        shapeSelected = false;
        drawableShape = new DrawableNullShape(Color.WHITE);
    }

    DrawingState(DrawingState currentState)
    {
        this();
        this.drawableShape = buildDrawableShape(drawableShape.getColor());
    }

    public void lineButtonHit(DrawingController controller)
    {
        this.stateChanged(controller.getModel());
        controller.setState(new LineState(this));
    }

    public void squareButtonHit(DrawingController controller)
    {
        this.stateChanged(controller.getModel());
        controller.setState(new SquareState(this));
    }

    public void rectangleButtonHit(DrawingController controller)
    {
        this.stateChanged(controller.getModel());
        controller.setState(new RectangularState(this));
    }

    public void circleButtonHit(DrawingController controller)
    {
        this.stateChanged(controller.getModel());
        controller.setState(new CircleState(this));
    }

    public void ellipseButtonHit(DrawingController controller)
    {
        this.stateChanged(controller.getModel());
        controller.setState(new EllipseState(this));
    }

    public void triangleButtonHit(DrawingController controller)
    {
        this.stateChanged(controller.getModel());
        controller.setState(new TriangleState(this));
    }

    public void selectButtonHit(DrawingController controller)
    {
        this.stateChanged(controller.getModel());
        controller.setState(new SelectionState());
    }

    public void deleteShape(CS355Drawing model)
    {

    }

    public void moveShapeForward(CS355Drawing model)
    {

    }

    public void moveShapeBackward(CS355Drawing model)
    {

    }

    public void moveShapeToFront(CS355Drawing model)
    {

    }

    public void moveShapeToBack(CS355Drawing model)
    {

    }



    /**
     * Changes the shape that can be stored in the model after clicking a mouseClicked event.
     *
     * @param point the point relevant to the event.
     * @param model the model to update.
     */
    public abstract void mouseClicked(Point2D.Double point, CS355Drawing model);

    /**
     * Changes the shape that can be stored in the model after clicking a mousePressed event.
     *
     * @param point the point relevant to the event.
     * @param model the model to update.
     */
    public abstract void mousePressed(Point2D.Double point, CS355Drawing model);

    /**
     * Changes the shape that can be stored in the model after clicking a mouseReleased event.
     *
     * @param point the point relevant to the event.
     * @param model the model to update.
     */
    public abstract void mouseReleased(Point2D.Double point, CS355Drawing model);

    /**
     * Changes the shape that can be stored in the model after clicking a mouseDragged event.
     *
     * @param point the point relevant to the event.
     * @param model the model to update.
     */
    public abstract void mouseDragged(Point2D.Double point, CS355Drawing model);

    public Color getColor()
    {
        return drawableShape.getColor();
    }

    public void setColor(Color color, CS355Drawing model)
    {
        drawableShape.setColor(color);
    }

    public DrawableShape getDrawableShape()
    {
        return drawableShape;
    }

    public void setDrawableShape(DrawableShape drawableShape)
    {
        this.drawableShape = drawableShape;
    }

    public boolean isShapeSelected()
    {
        return shapeSelected;
    }

    public void setIsShapeSelected(boolean shapeSelected)
    {
        this.shapeSelected = shapeSelected;
    }

    protected void stateChanged(CS355Drawing model)
    {
    }

    protected abstract DrawableShape buildDrawableShape(Color color);
}
