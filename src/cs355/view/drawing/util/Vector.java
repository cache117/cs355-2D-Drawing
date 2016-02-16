package cs355.view.drawing.util;

import java.awt.geom.Point2D;

/**
 * Represents a linear algebra vector with two entries.
 */
public class Vector
{
    private double x, y;

    public Vector(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector(Point2D.Double point)
    {
        this(point.x, point.y);
    }

    /**
     * Calculates the dot-product of this Vector with the given Vector.
     *
     * @param vector the Vector to calculate the dot-product with.
     * @return the result of the dot-product operation.
     */
    public double dotProduct(Vector vector)
    {
        return (this.x * vector.x) + (this.y * vector.y);
    }

    /**
     * Subtracts the given Vector from this Vector
     *
     * @param vector the Vector to subtract
     * @return this Vector after the subtraction operation.
     */
    public Vector subtract(Vector vector)
    {
        double x = this.x - vector.x;
        double y = this.y - vector.y;
        return new Vector(x, y);
    }

    public Vector add(Vector vector)
    {
        double x = this.x + vector.x;
        double y = this.y + vector.y;
        return new Vector(x, y);
    }

    public Vector orthogonalize()
    {
        double x = -y;
        double y = this.x;
        return new Vector(x, y);
    }

    public double length()
    {
        return length(new Vector(0,0));
    }

    public double length(Vector start)
    {
        return Math.sqrt(square(this.x - start.x) + square(this.y - start.y));
    }

    public static Vector calculateNormal(Vector p1, Vector p2)
    {
        Vector vectorDifference = p2.subtract(p1);
        Vector result = vectorDifference.orthogonalize();
        double length = vectorDifference.length();
        result.applyScaling(1 / length);
        return result;
    }

    private double square(double number)
    {
        return Math.pow(number, 2);
    }

    public Vector applyScaling(double scalingAmount)
    {
        double x = this.x * scalingAmount;
        double y = this.y * scalingAmount;
        return new Vector(x, y);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Vector vector = (Vector) o;

        if (Double.compare(vector.x, x) != 0)
            return false;
        return Double.compare(vector.y, y) == 0;

    }

    @Override
    public int hashCode()
    {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString()
    {
        return String.format("[%s]\n[%s]", x, y);
    }
}
