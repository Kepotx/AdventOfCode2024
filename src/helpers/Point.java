package helpers;

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
}