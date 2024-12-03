package adventOfCode.year2024;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import helpers.InputHelper;

public class Day3 
{
    public static void main(String ... args) throws Exception 
    {
        String lines = InputHelper.getInputAsString(3, false);

    	long safeNumber = computeResults(lines, true);
    	long safeNumber2 = computeResults(lines, false);

    	System.out.printf("part 1 : " +  safeNumber + "\n");
        System.out.printf("part 2 : " +  safeNumber2 + "\n");
    }

    public static int computeResults(String line, boolean alwaysEnabled)
    {
        Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
       
        Matcher matcher = pattern.matcher(line);

        Pattern controlPattern = Pattern.compile("\\b(do\\(\\)|don't\\(\\))");
        Matcher controlMatcher = controlPattern.matcher(line);
        
        int sum = 0;
        int doIndex = 0;
        boolean isEnabled = true;

        while (matcher.find())
        {
        	if (!alwaysEnabled)
        	{
        		while (controlMatcher.find(doIndex) && controlMatcher.start() < matcher.start()) 
        		{
        			String control = controlMatcher.group();
        			isEnabled = control.equals("do()");
        			doIndex = controlMatcher.end();
        		}
        	}
        	
        	if (isEnabled)
        	{
        		int x = Integer.parseInt(matcher.group(1));
        		int y = Integer.parseInt(matcher.group(2));
        		sum += x * y;
        	}

        }
        
        return sum;
    }

}