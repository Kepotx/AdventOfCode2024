package helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Point
{
    private int x;
	private int y;

    public Point(int x, int y)
    {
        this.setX(x);
        this.setY(y);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return getX() == point.getX() && getY() == point.getY();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getX(), getY());
    }

    @Override
    public String toString()
    {
        return "(" + getX() + ", " + getY() + ")";
    }

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

    public Point getLeftPoint()
    {
        return new Point(x - 1, y);
    }

    public Point getRightPoint()
    {
        return new Point(x + 1, y);
    }

    public Point getTopPoint()
    {
        return new Point(x, y - 1);
    }

    public Point getBottomPoint()
    {
        return new Point(x, y + 1);
    }
    
    public List<Point> getOrthogonalNeighbors()
    {
        List<Point> neighbors = new ArrayList<>();
        neighbors.add(getLeftPoint());
        neighbors.add(getRightPoint());
        neighbors.add(getTopPoint());
        neighbors.add(getBottomPoint());
        return neighbors;
    }
    
    public boolean isOutOfBonds(int rows, int cols)
    {
        return getX() >= 0 && getX() < rows && getY() >= 0 && getY() < cols;
    }
}