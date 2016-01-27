package cs355.view.drawing.state;

import cs355.view.drawing.DrawableSquare;

import java.awt.*;

/**
 * Created by cstaheli on 1/23/2016.
 */
public class SquareState extends RectangularState
{
    public SquareState(Color color)
    {
        super(new DrawableSquare(color));
    }
}
