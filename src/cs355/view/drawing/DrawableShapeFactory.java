package cs355.view.drawing;

import cs355.model.drawing.Shape;

import java.lang.reflect.Constructor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A Factory to get the DrawableShape corresponding to a Shape.
 * @see DrawableShape
 * @see Shape
 */
public class DrawableShapeFactory
{
    private static final Logger LOGGER = Logger.getLogger(DrawableShapeFactory.class.getName());

    public static DrawableShape createDrawableShape(Shape shape)
    {
        String className = shape.getClass().getSimpleName();
        try
        {
            Constructor constructor = Class.forName("cs355.view.drawing.Drawable" + className).getConstructor(Shape.class);
            LOGGER.fine("Created DrawableShape");
            return (DrawableShape) constructor.newInstance(shape);
        } catch (Exception e)
        {
            LOGGER.log(Level.SEVERE, "Failed to create DrawableShape", e);
            return new DrawableNullShape();
        }
    }
}
