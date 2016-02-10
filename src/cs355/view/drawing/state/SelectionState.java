package cs355.view.drawing.state;

import cs355.GUIFunctions;
import cs355.model.drawing.*;
import cs355.model.drawing.Shape;
import cs355.view.drawing.*;

import java.awt.geom.Point2D;
import java.util.List;

/**
 * Created by cstaheli on 1/23/2016.
 */
public class SelectionState extends DrawingState
{
    private boolean isHandleSelected;

    public SelectionState()
    {
        super(new DrawableNullShape());
        setIsShapeSelected(false);
        isHandleSelected = false;
        GUIFunctions.printf("Select a shape to modify.");
    }

    @Override
    public void mouseClicked(Point2D.Double point, CS355Drawing model)
    {
        /*
        if (isShapeSelected())
            this.checkForSelectedHandle(point);
        if(!isHandleSelected)
            this.checkForSelectedShape(point, model);
        */
    }

    @Override
    public void mousePressed(Point2D.Double point, CS355Drawing model)
    {
        if (isShapeSelected())
            this.checkForSelectedHandle(point);
        if (!isHandleSelected)
            this.checkForSelectedShape(point, model);
    }

    private void checkForSelectedHandle(Point2D.Double point)
    {
        DrawableShape drawableShape = getDrawableShape();
        //Clone so it doesn't modify the point
        Point2D.Double worldPoint = (Point2D.Double) point.clone();
        Point2D.Double objectPoint = Transform.getObjectPointFromWorldPoint(worldPoint, drawableShape.getRotation(), drawableShape.getCenterPoint());
        if (ShapeUtilities.pointInBoundingCircle(objectPoint, getDrawableShape().getHandleCenterPoint(), DrawableShape.HANDLE_DIAMETER / 2.0))
        {
            isHandleSelected = true;
            //GUIFunctions.printf("Selected handle");
        }
    }

    private void checkForSelectedShape(Point2D.Double point, CS355Drawing model)
    {
        List<Shape> shapes = model.getShapes();
        setIsShapeSelected(false);
        isHandleSelected = false;
        for (Shape shape : shapes)
        {
            if (shape.pointInShape(point, shape.getTolerance()) && !isShapeSelected())
            {
                shape.setSelected(true);
                setDrawableShape(DrawableShapeFactory.createDrawableShape(shape));
                setIsShapeSelected(true);
                updateColorFromShape(shape);
            } else
                shape.setSelected(false);

        }
        model.notifyObservers();
    }

    @Override
    public void mouseReleased(Point2D.Double point, CS355Drawing model)
    {
        if (isShapeSelected())
        {
            if (isHandleSelected)
            {
                //end Rotate shape
                double rotation = Math.atan2(point.y, point.x);
                int shapeIndex = getShapeIndexFromModel(model);
                model.getShape(shapeIndex).setRotation(rotation);
                GUIFunctions.printf("Rotation : %s", rotation);
                isHandleSelected = false;
                model.notifyObservers();
            } else
            {
                //end drag shape
            }
        }
    }

    @Override
    public void mouseDragged(Point2D.Double point, CS355Drawing model)
    {
        if (isShapeSelected())
        {
            if (isHandleSelected)
            {
                //end Rotate shape
                double rotation = Math.atan2(point.y, point.x);
                int shapeIndex = getShapeIndexFromModel(model);
                model.getShape(shapeIndex).setRotation(rotation);
                GUIFunctions.printf("Rotation : %s", rotation);
                model.notifyObservers();
            } else
            {
                //drag shape
            }
        }
    }

    private void updateColorFromShape(Shape shape)
    {
        GUIFunctions.changeSelectedColor(shape.getColor());
        setColor(shape.getColor());
    }

    @Override
    protected void stateChanged(CS355Drawing model)
    {
        List<Shape> shapes = model.getShapes();
        for (Shape shape : shapes)
        {
            shape.setSelected(false);
        }
        isHandleSelected = false;
        setIsShapeSelected(false);
        model.notifyObservers();
    }

    @Override
    public void deleteShape(CS355Drawing model)
    {
        int shapeIndex = getShapeIndexFromModel(model);
        model.deleteShape(shapeIndex);
        stateChanged(model);
    }

    @Override
    public void moveShapeForward(CS355Drawing model)
    {
        int shapeIndex = getShapeIndexFromModel(model);
        model.moveForward(shapeIndex);
    }

    @Override
    public void moveShapeBackward(CS355Drawing model)
    {
        int shapeIndex = getShapeIndexFromModel(model);
        model.moveBackward(shapeIndex);
    }

    @Override
    public void moveShapeToFront(CS355Drawing model)
    {
        int shapeIndex = getShapeIndexFromModel(model);
        model.moveToFront(shapeIndex);
    }

    @Override
    public void moveShapeToBack(CS355Drawing model)
    {
        int shapeIndex = getShapeIndexFromModel(model);
        model.movetoBack(shapeIndex);
    }

    private int getShapeIndexFromModel(CS355Drawing model)
    {
        return ((DrawingModel) model).getShapeIndex(getDrawableShape().getModelShape());
    }
}
