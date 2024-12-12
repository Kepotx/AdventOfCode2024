package adventOfCode.year2024.pojo;
import java.util.ArrayList;
import java.util.List;

import helpers.Point;


public class Region
{
    private List<Point> plots;
    private char plantType;
    
    public Region(char plantType)
    {
        this.plantType = plantType;
        this.plots = new ArrayList<>();
    }
    
    public void addPlot(Point current)
    {
        plots.add(current);
        
    }

    public int computeArea()
    {
        return plots.size();
    }

    public int computePerimeter(int gridSize)
    {
        int perimeter = 0;

        for (Point plot : plots)
        {

            for (Point neighbor : plot.getOrthogonalNeighbors())
            {
                if (!plots.contains(neighbor))
                {
                    perimeter++;
                }
            }
        }

        return perimeter;
    }
    
    public int countCorners(int gridSize)
    {
        int corners = 0;

        for (Point plot : plots) {
            boolean topLeft = !plots.contains(plot.getTopPoint()) && !plots.contains(plot.getLeftPoint());
            boolean topRight = !plots.contains(plot.getTopPoint()) && !plots.contains(plot.getRightPoint());
            boolean bottomLeft = !plots.contains(plot.getBottomPoint()) && !plots.contains(plot.getLeftPoint());
            boolean bottomRight = !plots.contains(plot.getBottomPoint()) && !plots.contains(plot.getRightPoint());

            if (topLeft) corners++;
            if (topRight) corners++;
            if (bottomLeft) corners++;
            if (bottomRight) corners++;
            
            boolean innerTopLeft = plots.contains(plot.getTopPoint()) && plots.contains(plot.getLeftPoint())
                    && !plots.contains(plot.getTopPoint().getLeftPoint());
            boolean innerTopRight = plots.contains(plot.getTopPoint()) && plots.contains(plot.getRightPoint())
                    && !plots.contains(plot.getTopPoint().getRightPoint());
            boolean innerBottomLeft = plots.contains(plot.getBottomPoint()) && plots.contains(plot.getLeftPoint())
                    && !plots.contains(plot.getBottomPoint().getLeftPoint());
            boolean innerBottomRight = plots.contains(plot.getBottomPoint()) && plots.contains(plot.getRightPoint())
                    && !plots.contains(plot.getBottomPoint().getRightPoint());

            if (innerTopLeft) corners++;
            if (innerTopRight) corners++;
            if (innerBottomLeft) corners++;
            if (innerBottomRight) corners++;
        }

        return corners;
    }
    
    public String displayPrice(int gridSize)
    {
        return "A region of " + plantType + " plants with price " + computeArea() +" * " + countCorners(gridSize) + " = " + computeArea() * countCorners(gridSize);
    }
}
