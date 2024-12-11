package adventOfCode.year2024;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import helpers.InputHelper;
import helpers.Pair;

public class Day11
{
    static Map<Pair<Long, Integer>, Long> cache = new HashMap<>();

    public static void main(String... args) throws Exception
    {
        String input = InputHelper.getInputAsString(11, false);
        List<Long> stones = Arrays.stream(input.split(" "))
        .map(Long::parseLong)
        .collect(Collectors.toList());

        long part1 = stones.stream()
                .mapToLong(stone -> blink(stone, 25))
                .sum();
        
        long part2 = stones.stream()
                .mapToLong(stone -> blink(stone, 75))
                .sum();
        
        System.out.printf("part 1: " + part1 + "\n");
        System.out.printf("part 2: " + part2);
    }
    
    // Tried to use computeIfAbsent to be more clear, but had a concurrence problem
    private static long blink(long stone, int iteration)
    {
        var cacheKey = Pair.of(stone, iteration);
        if (cache.containsKey(cacheKey))
        {
            return cache.get(cacheKey);
        }
        
        Long result;
        String digitAsString = String.valueOf(stone);
        int nDigits = digitAsString.length();
        int nextIteration = iteration - 1;
        
        if (iteration == 0)
        {
            result = 1L;
        }
        else if (stone == 0)
        {
            result = blink(1, nextIteration);
        }
        else if (nDigits % 2 == 0)
        {
            long left = Long.valueOf(digitAsString.substring(0, nDigits / 2));
            long right = Long.valueOf(digitAsString.substring(nDigits / 2));
            
            result = blink(left, nextIteration) + blink(right, nextIteration);
        }
        else
        {
            result = blink(stone * 2024, nextIteration);
        }

        cache.put(cacheKey, result);
        return result;
        
    }
}
