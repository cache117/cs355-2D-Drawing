package cs355.view.drawing.state;

import cs355.GUIFunctions;
import cs355.model.drawing.*;
import cs355.model.drawing.Shape;
import cs355.view.drawing.*;
import cs355.view.drawing.util.DrawableShapeFactory;
import cs355.view.drawing.util.ShapeUtilities;
import cs355.view.drawing.util.Transform;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

/**
 * Created by cstaheli on 1/23/2016.
 */
public class SelectionState extends DrawingState
{
    private boolean isHandleSelected;
    private Point2D.Double dragStart;
    private int currentlySelectedShapeIndex;

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
        this.checkForSelectedShape(point, model);
    }

    @Override
    public void mousePressed(Point2D.Double point, CS355Drawing model)
    {
        if (isShapeSelected())
            this.checkForSelectedHandle(point);
        if (!isHandleSelected)
            this.checkForSelectedShape(point, model);
    }

    @Override
    public void mouseDragged(Point2D.Double point, CS355Drawing model)
    {
        if (isShapeSelected())
        {
            if (isHandleSelected)
            {
                applyRotation(model, point);
            } else
            {
                applyDrag(model, point);
            }
        }
    }

    @Override
    public void mouseReleased(Point2D.Double point, CS355Drawing model)
    {
        if (isShapeSelected())
        {
            if (isHandleSelected)
            {
                //end Rotate shape
                applyRotation(model, point);
                isHandleSelected = false;
            } else
            {
                applyDrag(model, point);
            }
        }
    }

    private void checkForSelectedHandle(Point2D.Double point)
    {
        DrawableShape drawableShape = getDrawableShape();
        //Clone so it doesn't modify the point
        Point2D.Double worldPoint = (Point2D.Double) point.clone();
        Point2D.Double objectPoint = Transform.getObjectPointFromWorldPoint(worldPoint, drawableShape.getRotation(), drawableShape.getCenterPoint());
        if (ShapeUtilities.pointInBoundingCircle(objectPoint, getDrawableShape().getHandleCenterPoint(), DrawableShape.HANDLE_RADIUS))
        {
            isHandleSelected = true;
        }
    }

    private void checkForSelectedShape(Point2D.Double point, CS355Drawing model)
    {
        List<Shape> shapes = model.getShapes();
        setIsShapeSelected(false);
        isHandleSelected = false;
        for (int i = 0; i < shapes.size(); ++i)
        {
            Shape shape = shapes.get(i);
            if (shape.pointInShape(point) && !isShapeSelected())
            {
                shape.setSelected(true);
                currentlySelectedShapeIndex = i;
                setDrawableShape(DrawableShapeFactory.createDrawableShape(shape));
                setIsShapeSelected(true);
                updateColorFromShape(shape);
                this.dragStart = Transform.getObjectPointFromWorldPoint(point, shape.getRotation(), shape.getCenter());
            } else
                shape.setSelected(false);

        }
        model.notifyObservers();
    }

    @Override
    public void setColor(Color color, CS355Drawing model)
    {
        if (isShapeSelected())
        {
            getCurrentShapeFromModel(model).setColor(color);
            model.notifyObservers();
        }
    }

    private void updateColorFromShape(Shape shape)
    {
        GUIFunctions.changeSelectedColor(shape.getColor());
        super.setColor(shape.getColor(), null);
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
        currentlySelectedShapeIndex = -1;
        model.notifyObservers();
    }

    @Override
    public void deleteShape(CS355Drawing model)
    {
        //TODO this needs to modify the index
        model.deleteShape(currentlySelectedShapeIndex);
        stateChanged(model);
    }

    @Override
    public void moveShapeForward(CS355Drawing model)
    {
        //TODO this needs to modify the index
        model.moveForward(currentlySelectedShapeIndex);
    }

    @Override
    public void moveShapeBackward(CS355Drawing model)
    {
        //TODO this needs to modify the index
        model.moveBackward(currentlySelectedShapeIndex);
    }

    @Override
    public void moveShapeToFront(CS355Drawing model)
    {
        //TODO this needs to modify the index
        model.moveToFront(currentlySelectedShapeIndex);
    }

    @Override
    public void moveShapeToBack(CS355Drawing model)
    {
        //TODO this needs to modify the index
        model.movetoBack(currentlySelectedShapeIndex);
    }

    private Shape getCurrentShapeFromModel(CS355Drawing model)
    {
        return model.getShape(currentlySelectedShapeIndex);
    }

    private double calculateRotation(Point2D.Double objectPoint)
    {
        return Math.atan2(objectPoint.y, objectPoint.x);
    }

    private void applyRotation(CS355Drawing model, Point2D.Double worldPoint)
    {
        Shape shape = getCurrentShapeFromModel(model);
        Point2D.Double objectPoint = Transform.getObjectPointFromWorldPoint(worldPoint, shape.getRotation(), shape.getCenter());
        double rotation = calculateRotation(objectPoint);
        shape.setRotation(rotation);
        model.notifyObservers();
    }

    private void applyDrag(CS355Drawing model, Point2D.Double worldPoint)
    {
        //Get point in object
        //Get the new point's relation to where the center is
        //set center
        double newCenterX = worldPoint.x - dragStart.x;
        double newCenterY = worldPoint.y - dragStart.y;
        Shape shape = getCurrentShapeFromModel(model);
        shape.setCenter(new Point2D.Double(newCenterX, newCenterY));
        model.notifyObservers();
    }
}
