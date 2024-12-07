package adventOfCode.year2024;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import helpers.InputHelper;

public class Day7
{

    public static void main(String... args) throws Exception
    {
        List<String> rawInput = InputHelper.getInputAsListOfLines(7, false);
        List<Long> testValues = rawInput.stream().map(input -> Long.parseLong(input.split(":")[0])).toList();

        List<List<Long>> numbersList = rawInput.stream()
            .map(s -> s.split(":")[1])
            .map(part -> Arrays.stream(part.trim().split(" "))
                    .map(Long::parseLong)
                    .collect(Collectors.toList()))
            .collect(Collectors.toList());

        Long validValues = 0l;
        Long validValues2 = 0l;
        
        for (int i = 0; i < rawInput.size(); i++)
		{
        	Long testValue = testValues.get(i);
        	List<Long> numbers = numbersList.get(i);
			if (isEquationPossible(testValue, numbers, false))
			{
				validValues += testValue;
				validValues2 += testValue;
			}
			else if(isEquationPossible(testValue, numbers, true))
			{
				validValues2 += testValue;
			}
		}
        
        
        System.out.printf("part 1 : " + validValues + "\n");
        System.out.printf("part 2 : " + validValues2 + "\n");
    }
    
	private static boolean isEquationPossible(Long testValue, List<Long> numbers, boolean concat) {
	    Set<Long> possibleResults = new HashSet<>();
	    possibleResults.add(numbers.get(0));

	    for (int i = 1; i < numbers.size(); i++) {
	    	Long currentNumber = numbers.get(i);
	        Set<Long> nextResults = new HashSet<>();

	        for (Long result : possibleResults) {
	            nextResults.add(result + currentNumber);
	            nextResults.add(result * currentNumber);
	            if (concat)
	            {
	            	nextResults.add(Long.parseLong(result.toString() + currentNumber.toString()));
	            }
	        }
	        possibleResults = nextResults;
	    }

	    return possibleResults.contains(testValue);
	}
	
}
