package cs355.controller;

import cs355.GUIFunctions;
import cs355.model.drawing.*;
import cs355.view.ViewRefresher;
import cs355.view.drawing.*;
import cs355.view.drawing.state.DrawingState;
import cs355.view.drawing.state.InitialState;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.*;

/**
 * Created by cstaheli on 1/7/2016.
 */
public class DrawingController implements CS355Controller, MouseListener, MouseMotionListener
{
    private ViewRefresher view;
    private CS355Drawing model;
    private DrawingState state;

    public DrawingController()
    {
        model = new DrawingModel();
        state = new InitialState();
    }

    /* begin CS355Controller methods */
    @Override
    public void colorButtonHit(Color c)
    {
        GUIFunctions.changeSelectedColor(c);
        state.setColor(c);
    }

    @Override
    public void lineButtonHit()
    {
        state.lineButtonHit(this);
    }

    @Override
    public void squareButtonHit()
    {
        state.squareButtonHit(this);
    }

    @Override
    public void rectangleButtonHit()
    {
        state.rectangleButtonHit(this);
    }

    @Override
    public void circleButtonHit()
    {
        state.circleButtonHit(this);
    }

    @Override
    public void ellipseButtonHit()
    {
        state.ellipseButtonHit(this);
    }

    @Override
    public void triangleButtonHit()
    {
        state.triangleButtonHit(this);
    }

    @Override
    public void selectButtonHit()
    {
        state.selectButtonHit(this);
    }

    @Override
    public void zoomInButtonHit()
    {

    }

    @Override
    public void zoomOutButtonHit()
    {

    }

    @Override
    public void hScrollbarChanged(int value)
    {

    }

    @Override
    public void vScrollbarChanged(int value)
    {

    }

    @Override
    public void openScene(File file)
    {

    }

    @Override
    public void toggle3DModelDisplay()
    {

    }

    @Override
    public void keyPressed(Iterator<Integer> iterator)
    {

    }

    @Override
    public void openImage(File file)
    {

    }

    @Override
    public void saveImage(File file)
    {

    }

    @Override
    public void toggleBackgroundDisplay()
    {

    }

    @Override
    public void saveDrawing(File file)
    {
        model.save(file);
    }

    @Override
    public void openDrawing(File file)
    {
        model.open(file);
        GUIFunctions.refresh();
    }

    @Override
    public void doDeleteShape()
    {

    }

    @Override
    public void doEdgeDetection()
    {

    }

    @Override
    public void doSharpen()
    {

    }

    @Override
    public void doMedianBlur()
    {

    }

    @Override
    public void doUniformBlur()
    {

    }

    @Override
    public void doGrayscale()
    {

    }

    @Override
    public void doChangeContrast(int contrastAmountNum)
    {

    }

    @Override
    public void doChangeBrightness(int brightnessAmountNum)
    {

    }

    @Override
    public void doMoveForward()
    {

    }

    @Override
    public void doMoveBackward()
    {

    }

    @Override
    public void doSendToFront()
    {

    }

    @Override
    public void doSendtoBack()
    {

    }

    /* end CS355Controller methods */

    /* begin MouseListener methods */
    @Override
    public void mouseClicked(MouseEvent e)
    {
        Point2D.Double point = new Point2D.Double(e.getX(), e.getY());
        //GUIFunctions.printf("Mouse Clicked: %s", point.toString());
        state.mouseClicked(point, model);
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        Point2D.Double point = new Point2D.Double(e.getX(), e.getY());
        //GUIFunctions.printf("Mouse Pressed: %s", point.toString());
        state.mousePressed(point, model);
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        Point2D.Double point = new Point2D.Double(e.getX(), e.getY());
        //GUIFunctions.printf("Mouse Released: %s", point.toString());
        state.mouseReleased(point, model);
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        Point2D.Double point = new Point2D.Double(e.getX(), e.getY());
        //GUIFunctions.printf("Mouse Entered: %s", point.toString());
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        Point2D.Double point = new Point2D.Double(e.getX(), e.getY());
        //GUIFunctions.printf("Mouse Exited: %s", point.toString());
    }

    /* end MouseListener methods */

    /* begin MouseMotionListener methods */
    @Override
    public void mouseDragged(MouseEvent e)
    {
        Point2D.Double point = new Point2D.Double(e.getX(), e.getY());
        //GUIFunctions.printf("Mouse Dragged: %s", point.toString());
        state.mouseDragged(point, model);
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        Point2D.Double point = new Point2D.Double(e.getX(), e.getY());
        //GUIFunctions.printf("Mouse Moved: %s", point.toString());
    }
    /* end MouseMotionListener methods */

    public void setView(ViewRefresher view)
    {
        this.view = view;
        this.model.deleteObservers();
        this.model.addObserver(view);
    }

    public ViewRefresher getView()
    {
        return view;
    }

    public void setState(DrawingState state)
    {
        this.state = state;
    }
}
