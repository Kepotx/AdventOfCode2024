package adventOfCode.year2024;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import helpers.InputHelper;
import helpers.Point;

public class Day8 
{
	 public static void main(String... args) throws Exception
	    {
            List<String> gridInput = InputHelper.getInputAsListOfLines(8, false);
            int N = gridInput.size();
            int M = gridInput.get(0).length();
            char[][] grid = new char[N][M];
            for (int i = 0; i < N; i++) {
                grid[i] = gridInput.get(i).toCharArray();
            }

            Map<Character, List<Point>> nodes = new HashMap<>();
            for (int i = 0; i < N; i++)
            {
                for (int j = 0; j < M; j++)
                {
                    char c = grid[i][j];
                    if (c != '.')
                    {
                        nodes.computeIfAbsent(c, k -> new ArrayList<>()).add(new Point(i, j));
                    }
                }
            }

            int partOneResult = calculatePartOne(nodes, N, M);
            System.out.println("Part One: " + partOneResult);

            int partTwoResult = calculatePartTwo(nodes, N, M);
            System.out.println("Part Two: " + partTwoResult);
        }

        private static int calculatePartOne(Map<Character, List<Point>> nodes, int rows, int cols) {
            Set<Point> antinodes = new HashSet<>();
            for (Map.Entry<Character, List<Point>> entry : nodes.entrySet())
            {
                List<Point> nodeList = entry.getValue();
                int size = nodeList.size();
                for (int i = 0; i < size; i++)
                {
                    for (int j = 0; j < i; j++)
                    {
                        Point node1 = nodeList.get(i);
                        Point node2 = nodeList.get(j);
                        addAntinode(antinodes, node1, node2, rows, cols);
                        addAntinode(antinodes, node2, node1, rows, cols);
                    }
                }
            }
            return antinodes.size();
        }

        private static int calculatePartTwo(Map<Character, List<Point>> nodes, int rows, int cols) {
            Set<Point> antinodes = new HashSet<>();
            for (Map.Entry<Character, List<Point>> entry : nodes.entrySet()) {
                List<Point> nodeList = entry.getValue();
                int size = nodeList.size();

                antinodes.addAll(nodeList);

                for (int i = 0; i < size; i++)
                {
                    for (int j = 0; j < i; j++)
                    {
                        Point node1 = nodeList.get(i);
                        Point node2 = nodeList.get(j);
                        addAllAntinodesInLine(antinodes, node1, node2, rows, cols);
                        addAllAntinodesInLine(antinodes, node2, node1, rows, cols);
                    }
                }
            }
            return antinodes.size();
        }

        private static void addAntinode(Set<Point> antinodes, Point p1, Point p2, int rows, int cols)
        {
            int newX = p2.getX() + (p2.getX() - p1.getX());
            int newY = p2.getY() + (p2.getY() - p1.getY());
            if (newX >= 0 && newX < rows && newY >= 0 && newY < cols)
            {
                antinodes.add(new Point(newX, newY));
            }
        }

        private static void addAllAntinodesInLine(Set<Point> antinodes, Point p1, Point p2, int rows, int cols)
        {
            Point direction = new Point(p2.getX() - p1.getX(), p2.getY() - p1.getY());
            Point current = new Point(p2.getX(), p2.getY());

            while (current.getX() >= 0 && current.getX() < rows && current.getY() >= 0 && current.getY() < cols)
            {
                antinodes.add(new Point(current.getX(), current.getY()));
                current = new Point(current.getX() + direction.getX(), current.getY() + direction.getY());
            }
        }
}
