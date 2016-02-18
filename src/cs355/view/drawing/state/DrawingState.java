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
    private Point2D.Double viewportUpperLeft;
    private double scalingFactor;

    private int hBarPosition;
    private int vBarPosition;
    private static final int VIEW_SIZE = 512, WORLD_SIZE = 2048;

    protected DrawingState()
    {
        shapeSelected = false;
        scalingFactor = 1.0;
        drawableShape = new DrawableNullShape(Color.WHITE);
        viewportUpperLeft = new Point2D.Double(0,0);
    }

    public DrawingState(DrawingState currentState)
    {
        this();
        this.drawableShape = buildDrawableShape(drawableShape.getColor());
        this.viewportUpperLeft = currentState.viewportUpperLeft;
        this.scalingFactor = currentState.scalingFactor;
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

    public void zoomInButtonHit(ViewRefresher view)
    {
        if (scalingFactor < 4.0)
            scalingFactor *= 2.0;
        Point2D.Double oldUpperLeft = (Point2D.Double) viewportUpperLeft.clone();
        viewportUpperLeft = new Point2D.Double(oldUpperLeft.x + (VIEW_SIZE /scalingFactor), oldUpperLeft.y + (VIEW_SIZE /scalingFactor));
        updateViewport(view);
        doZoom();
    }

    public void zoomOutButtonHit(ViewRefresher view)
    {
        if (scalingFactor > .25)
            scalingFactor /= 2.0;
        Point2D.Double oldUpperLeft = (Point2D.Double) viewportUpperLeft.clone();
        viewportUpperLeft = new Point2D.Double(oldUpperLeft.x - (VIEW_SIZE /scalingFactor), oldUpperLeft.y - (VIEW_SIZE /scalingFactor));
        updateViewport(view);
        doZoom();
    }

    public void hScrollbarChanged(int value, ViewRefresher view)
    {
        viewportUpperLeft.x = value;
        updateViewport(view);
    }

    public void vScrollbarChanged(int value, ViewRefresher view)
    {
        viewportUpperLeft.y = value;
        updateViewport(view);
    }

    private void updateViewport(ViewRefresher view)
    {
        ((DrawingViewer) view).setViewportParameters(new ViewportParameters(viewportUpperLeft, scalingFactor));
    }

    private void doZoom()
    {
        GUIFunctions.setZoomText(scalingFactor);
        int hBarSize = (int) (VIEW_SIZE / scalingFactor);
        GUIFunctions.setHScrollBarKnob(hBarSize);
        GUIFunctions.setHScrollBarPosit((int) viewportUpperLeft.x);

        int vBarSize = (int) (VIEW_SIZE / scalingFactor);
        GUIFunctions.setVScrollBarKnob(vBarSize);
        GUIFunctions.setVScrollBarPosit((int) viewportUpperLeft.y);

        GUIFunctions.refresh();
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

    public void setScalingFactor(double scalingFactor)
    {
        this.scalingFactor = scalingFactor;
    }

    public void setViewportUpperLeft(Point2D.Double viewportUpperLeft)
    {
        this.viewportUpperLeft = viewportUpperLeft;
    }

    public abstract DrawableShape buildDrawableShape(Color color);

    public int getHBarPosition()
    {
        return hBarPosition;
    }

    public void setHBarPosition(int hBarPosition)
    {
        this.hBarPosition = hBarPosition;

    }

    public int getVBarPosition()
    {
        return vBarPosition;
    }

    public void setVBarPosition(int vBarPosition)
    {
        this.vBarPosition = vBarPosition;
    }
}
