package adventOfCode.year2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import helpers.InputHelper;
import helpers.Point;

public class Day10
{

    public static void main(String... args) throws Exception
    {
        List<List<Integer>> map = InputHelper.getInputAsGenericList(10, false, lineParser());

        List<Point> trailheads = findTrailheads(map);

        Integer part1 = trailheads.stream().mapToInt(trailhead -> countTrailsDFS(map, trailhead)).sum();

        Integer part2 = trailheads.stream().mapToInt(trailhead -> countDistinctTrails(map, trailhead)).sum();

        System.out.printf("part 1 : " + part1 + "\n");
        System.out.printf("part 2 : " + part2 + "\n");
    }

    private static Function<String, List<Integer>> lineParser()
    {
        return line -> Arrays.stream(line.split("")).map(Integer::parseInt).collect(Collectors.toList());
    }

    private static Integer countDistinctTrails(List<List<Integer>> map, Point startingPoint)
    {
        Set<List<Point>> distinctTrails = new HashSet<>();

        dfs(map, startingPoint, new ArrayList<>(), distinctTrails);

        return distinctTrails.size();
    }

    private static void dfs(List<List<Integer>> map, Point current, List<Point> currentTrail, Set<List<Point>> distinctTrails)
    {
        int rows = map.size();
        int cols = map.get(0).size();
        int currentHeight = map.get(current.getX()).get(current.getY());

        currentTrail.add(current);

        if (currentHeight == 9)
        {
            distinctTrails.add(new ArrayList<>(currentTrail));
            return;
        }

        for (int[] direction : new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}})
        {
            int nx = current.getX() + direction[0];
            int ny = current.getY() + direction[1];

            if (nx >= 0 && nx < rows && ny >= 0 && ny < cols)
            {
                int neighborHeight = map.get(nx).get(ny);

                if (neighborHeight == currentHeight + 1)
                {
                    dfs(map, new Point(nx, ny), new ArrayList<>(currentTrail), distinctTrails);
                }
            }
        }
    }

    private static Integer countTrailsDFS(List<List<Integer>> map, Point startingPoint)
    {
        List<Point> visited = new ArrayList<>();
        Set<Point> reachableNines = new HashSet<>();

        dfsPart1(map, startingPoint, visited, reachableNines);

        return reachableNines.size();
    }

    private static void dfsPart1(List<List<Integer>> map, Point current, List<Point> currentTrail, Set<Point> reachableNines)
    {
        int rows = map.size();
        int cols = map.get(0).size();
        int currentHeight = map.get(current.getX()).get(current.getY());

        currentTrail.add(current);

        if (currentHeight == 9)
        {
            reachableNines.add(current);
            return;
        }

        for (int[] direction : new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}})
        {
            int nx = current.getX() + direction[0];
            int ny = current.getY() + direction[1];

            if (nx >= 0 && nx < rows && ny >= 0 && ny < cols)
            {
                Point neighbor = new Point(nx, ny);
                int neighborHeight = map.get(nx).get(ny);

                if (!currentTrail.contains(neighbor) && neighborHeight == currentHeight + 1)
                {
                    dfsPart1(map, neighbor, currentTrail, reachableNines);
                }
            }
        }
    }

    private static List<Point> findTrailheads(List<List<Integer>> map)
    {
        List<Point> trailheads = new ArrayList<>();
        for (int i = 0; i < map.size(); i++)
        {
            for (int j = 0; j < map.get(i).size(); j++)
            {
                if (map.get(i).get(j) == 0)
                {
                    trailheads.add(new Point(i, j));
                }
            }
        }
        return trailheads;
    }
}
