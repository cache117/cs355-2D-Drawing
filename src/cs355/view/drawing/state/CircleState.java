package cs355.view.drawing.state;

import cs355.view.drawing.DrawableCircle;

import java.awt.*;

/**
 * Created by cstaheli on 1/23/2016.
 */
public class CircleState extends EllipseState
{
    public CircleState(Color color)
    {
        super(new DrawableCircle(color));
    }
}
