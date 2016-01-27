package cs355.view.drawing.state;

import cs355.controller.DrawingController;
import cs355.model.drawing.CS355Drawing;
import cs355.view.drawing.DrawableShape;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by cstaheli on 1/23/2016.
 */
public abstract class DrawingState
{
    private DrawableShape drawableShape;

    public DrawingState(DrawableShape shape)
    {
        this.drawableShape = shape;
    }
    public void lineButtonHit(DrawingController controller)
    {
        controller.setState(new LineState(drawableShape.getColor()));
    }

    public void squareButtonHit(DrawingController controller)
    {
        controller.setState(new SquareState(drawableShape.getColor()));
    }

    public void rectangleButtonHit(DrawingController controller)
    {
        controller.setState(new RectangularState(drawableShape.getColor()));
    }

    public void circleButtonHit(DrawingController controller)
    {
        controller.setState(new CircleState(drawableShape.getColor()));
    }

    public void ellipseButtonHit(DrawingController controller)
    {
        controller.setState(new EllipseState(drawableShape.getColor()));
    }

    public void triangleButtonHit(DrawingController controller)
    {
        controller.setState(new TriangleState(drawableShape.getColor()));
    }

    public void selectButtonHit(DrawingController controller)
    {
        controller.setState(new SelectionState());
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

    public void setColor(Color color)
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
}
