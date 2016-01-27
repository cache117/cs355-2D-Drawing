package cs355.view.drawing;

/**
 * An exception to indicated an invalid number of points.
 */
public class InvalidPointsException extends Exception
{
    public InvalidPointsException()
    {
        super();
    }

    public InvalidPointsException(String message)
    {
        super(message);
    }
}
