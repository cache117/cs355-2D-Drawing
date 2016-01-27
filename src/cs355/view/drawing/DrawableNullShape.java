package cs355.view.drawing;

import cs355.model.drawing.NullShape;
import cs355.model.drawing.Shape;

import java.awt.*;

/**
 * A DrawableShape that represents null.
 */
public class DrawableNullShape extends DrawableShape
{
    @Override
    protected void drawShape(Graphics2D graphics, boolean selected)
    {

    }

    @Override
    protected void calculatePointsFromShape(Shape shape)
    {

    }

    @Override
    public Shape getModelShape()
    {
        return new NullShape();
    }

    @Override
    public void setColor(Color color)
    {

    }
}
