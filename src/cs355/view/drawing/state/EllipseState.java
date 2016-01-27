package cs355.view.drawing.state;

import cs355.view.drawing.DrawableEllipse;
import cs355.view.drawing.DrawableShape;

import java.awt.*;

/**
 * Created by cstaheli on 1/23/2016.
 */
public class EllipseState extends RectangularState
{
    protected EllipseState(DrawableShape shape)
    {
        super(shape);
    }

    public EllipseState(Color color)
    {
        this(new DrawableEllipse(color));
    }
}
