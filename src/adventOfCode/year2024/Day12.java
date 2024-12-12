package adventOfCode.year2024;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import adventOfCode.year2024.pojo.Region;
import helpers.InputHelper;
import helpers.Pair;
import helpers.Point;

public class Day12
{
    static Map<Pair<Long, Integer>, Long> cache = new HashMap<>();

    public static void main(String... args) throws Exception
    {
        List<List<Character>> mapGrid = InputHelper.getInputAsGridOfChars(12, false);

        List<Region> regions = findRegions(mapGrid);
        Integer part1 = regions.stream()
                .mapToInt(region -> region.computeArea() * region.computePerimeter(mapGrid.size()))
                .sum();
        
        Integer part2 = regions.stream()
                .mapToInt(region -> region.computeArea() * region.countCorners(mapGrid.size()))
                .sum();
        System.out.printf("part 1: " + part1 + "\n");
        System.out.printf("part 2: " + part2);
    }
    

    public static List<Region> findRegions(List<List<Character>> mapGrid)
    {
        List<Region> regions = new ArrayList<>();
        int rows = mapGrid.size();
        int cols = mapGrid.get(0).size();
        Set<Point> visited = new HashSet<>();

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                Point current = new Point(i, j);
                if (!visited.contains(current))
                {
                    char plantType = mapGrid.get(i).get(j);
                    Region region = new Region(plantType);
                    exploreRegion(mapGrid, current, plantType, visited, region);
                    regions.add(region);
                }
            }
        }

        return regions;
    }

    public static void exploreRegion(List<List<Character>> mapGrid, Point point, char plantType, Set<Point> visited, Region region)
    {
        visited.add(point);

        region.addPlot(point);

        for (Point neighbor : point.getOrthogonalNeighbors())
        {
            if (!visited.contains(neighbor) && neighbor.isOutOfBonds(mapGrid.size(), mapGrid.size()) && mapGrid.get(neighbor.getX()).get(neighbor.getY()) == plantType) {
                exploreRegion(mapGrid, neighbor, plantType, visited, region);
            }
        }
    }
    
    
}
