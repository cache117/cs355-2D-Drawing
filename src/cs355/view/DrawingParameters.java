package cs355.view;

import java.awt.*;

/**
 * Created by cstaheli on 2/18/2016.
 */
public class DrawingParameters
{
    public final ViewportParameters viewportParameters;
    public final Graphics2D graphics2D;

    public DrawingParameters(Graphics2D graphics2D, ViewportParameters viewportParameters)
    {
        this.viewportParameters = viewportParameters;
        this.graphics2D = graphics2D;
    }
}
