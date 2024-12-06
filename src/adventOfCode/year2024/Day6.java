package adventOfCode.year2024;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import helpers.InputHelper;

public class Day6
{
    private static List<List<Integer>> directions = List.of(List.of(-1, 0), List.of(0, 1), List.of(1, 0), List.of(0, -1));

    public static void main(String... args) throws Exception
    {
        List<List<Character>> originalMap = InputHelper.getInputAsGenericList(6, false, lineParser());
        List<List<Character>> guardianMap = InputHelper.getInputAsGenericList(6, false, lineParser());

        int posX = 0;
        int posY = 0;
        
        for (int row = 0; row < originalMap.size(); row++)
        {
            List<Character> line = originalMap.get(row);
            for (int col = 0; col < line.size(); col++)
            {
                if (line.get(col) == '^')
                {
                    posX = col;
                    posY = row;
                }
            }
        }

        fillGuardianMap(originalMap, guardianMap, posX, posY);

        long visitedPositions = guardianMap.stream()
                .flatMap(List::stream)
                .filter(c -> c == 'X')
                .count();

        guardianMap.get(posY).set(posX, '^');
        
        System.out.printf("part 1 : " + visitedPositions + "\n");
        System.out.printf("part 2 : " + countLoops(guardianMap, posX, posY) + "\n");
    }

    public static int countLoops(List<List<Character>> map, int startX, int startY) {

        int rows = map.size();
        int cols = map.get(0).size();
        int count = 0;

        
        for (int y = 0; y < rows; y++)
        {
            for (int x = 0; x < cols; x++)
            {
                if (map.get(y).get(x) == 'X')
                {
                    map.get(y).set(x, '#');
                    if (doesLoop(map,startX, startY))
                    {
                        count++;
                    }
                    map.get(y).set(x, 'X');
                }
            }
        }

        return count;
    }
    
    private static void fillGuardianMap(List<List<Character>> originalMap, List<List<Character>> guardianMap, int posX, int posY)
    {
        int dir = 0;

        guardianMap.get(posY).set(posX, 'X');

        while (true)
        {
            int nextX = posX + directions.get(dir).get(1);
            int nextY = posY + directions.get(dir).get(0);

            if (nextY < 0 || nextY >= originalMap.size() || nextX < 0 || nextX >= originalMap.get(0).size()) {
                break;
            }
            else if (originalMap.get(nextY).get(nextX) == '#')
            {
                dir = (dir + 1) % 4;
            }
            else
            {
                posY = nextY;
                posX = nextX;

                guardianMap.get(posY).set(posX, 'X');
            }
        }
    }

    private static Function<String, List<Character>> lineParser()
    {
        return line -> line.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
    }
    
    public static boolean doesLoop(List<List<Character>> originalMap, int startX, int startY) {

        int rows = originalMap.size();
        int cols = originalMap.get(0).size();

        Set<String> visitedStates = new HashSet<>();
        int posX = startX;
        int posY = startY;
        int dir = 0;

        while (true)
        {
            int nextX = posX + directions.get(dir).get(1);
            int nextY = posY + directions.get(dir).get(0);
            
            String state = posX + "," + posY + "," + dir;

            if (visitedStates.contains(state))
            {
                return true;
            }
            
            visitedStates.add(state);

            if (nextY < 0 || nextY >= rows || nextX < 0 || nextX >= cols )
            {
                return false;
            }
            else if (originalMap.get(nextY).get(nextX) == '#')
            {
                dir = (dir + 1) % 4;
            }
            else
            {
                posX = nextX;
                posY = nextY;
            }
            
        }
    }
}
