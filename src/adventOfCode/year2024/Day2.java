package adventOfCode.year2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import helpers.InputHelper;

public class Day2 
{
    public static void main(String ... args) throws Exception 
    {
        List<List<Integer>> lines = InputHelper.getInputAsGenericList(2, false, lineParser());

    	long safeNumber = lines.stream().filter(Day2::isSafe).count();
    	long safeNumber2 = lines.stream().filter(Day2::isSafeTwo).count();

    	System.out.printf("part 1 : " +  safeNumber + "\n");
        System.out.printf("part 2 : " +  safeNumber2 + "\n");
    }

	private static Function<String, List<Integer>> lineParser()
	{
		return line -> Arrays.stream(line.split(" "))
		              .map(Integer::parseInt)
		              .collect(Collectors.toList());
	}
	
	private static boolean isSafe(List<Integer> line) 
	{
	    Integer firstInput = line.get(0);
	    Integer secondInput = line.get(1);
	    Integer difference = secondInput - firstInput;
	    boolean isNegative = difference < 0;

	    if (Math.abs(difference) > 3 || difference == 0)
	    {
	        return false;
	    }

	    for (int i = 1; i < line.size() - 1; i++) {
	        firstInput = line.get(i);
	        secondInput = line.get(i + 1);
	        difference = secondInput - firstInput;

	        boolean isLocalNegative = difference < 0;
	        if (isLocalNegative != isNegative || Math.abs(difference) > 3 || difference == 0)
	        {
	            return false;
	        }
	    }

	    return true;
	}
	
	private static boolean isSafeTwo(List<Integer> line) 
	{
	    for (int i = 0; i < line.size(); i++)
	    {
	        List<Integer> subLine = new ArrayList<>(line);
	        subLine.remove(i);
	        if (isSafe(subLine))
	        {
	            return true;
	        }
	    }

	    return false;
	}

}