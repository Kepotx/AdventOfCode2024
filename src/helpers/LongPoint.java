package helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LongPoint
{
    private Long x;
	private Long y;

    public LongPoint(Long x, Long y)
    {
        this.setX(x);
        this.setY(y);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LongPoint point = (LongPoint) o;
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

	public Long getX()
	{
		return x;
	}

	public void setX(Long x)
	{
		this.x = x;
	}

	public Long getY()
	{
		return y;
	}

	public void setY(Long y)
	{
		this.y = y;
	}

    public LongPoint getLeftPoint()
    {
        return new LongPoint(x - 1, y);
    }

    public LongPoint getRightPoint()
    {
        return new LongPoint(x + 1, y);
    }

    public LongPoint getTopPoint()
    {
        return new LongPoint(x, y - 1);
    }

    public LongPoint getBottomPoint()
    {
        return new LongPoint(x, y + 1);
    }
    
    public List<LongPoint> getOrthogonalNeighbors()
    {
        List<LongPoint> neighbors = new ArrayList<>();
        neighbors.add(getLeftPoint());
        neighbors.add(getRightPoint());
        neighbors.add(getTopPoint());
        neighbors.add(getBottomPoint());
        return neighbors;
    }
    
    public boolean isOutOfBonds(Long rows, Long cols)
    {
        return getX() >= 0 && getX() < rows && getY() >= 0 && getY() < cols;
    }
}