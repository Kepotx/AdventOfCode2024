package days;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import helpers.InputHelper;

public class Day1 
{
    public static void main(String ... args) throws Exception 
    {
    	List<Integer> leftList = new ArrayList<Integer>();
    	List<Integer> rightList = new ArrayList<Integer>();
    	
    	List<String> lines = InputHelper.getInputAsListOfLines(1);
    	
    	for (String line : lines)
    	{
        	String leftInput = line.split("   ")[0];
        	String rightInput = line.split("   ")[1];

        	leftList.add(Integer.valueOf(leftInput));
        	rightList.add(Integer.valueOf(rightInput));
    	}
    	
    	Collections.sort(leftList); 
    	Collections.sort(rightList);
    	
    	Integer drift = 0;
    	Integer similarity = 0;
    	
    	for(int i = 0 ; i < leftList.size() ; i++ )
        {
    		Integer right = rightList.get(i);
    		Integer left = leftList.get(i);
    		Integer difference = Math.abs(left - right);
    		
    		drift += difference;
    		
    		if (leftList.contains(right))
    		{
    			similarity += right;
    		}
        }

        System.out.printf("part 1: " + drift + "\n");
        System.out.printf("part 2: " + similarity);

    }

	}