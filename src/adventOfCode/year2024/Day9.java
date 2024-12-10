package adventOfCode.year2024;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import helpers.InputHelper;

public class Day9
{

    public static void main(String... args) throws Exception
    {
        String input = InputHelper.getInputAsString(9, false);
        Map<Integer, Integer> idToSpaceMap = new HashMap<>();
        
        List<String> partition = createPartition(input, idToSpaceMap);

        List<String> compactedPartition = compactDisk1(partition);
        System.out.println("part 1 : " + calculateChecksum(compactedPartition));
        
        List<String> compactedPartition2 = compactDisk2(partition, idToSpaceMap);
        System.out.println("part 2 : " + calculateChecksum(compactedPartition2));
    }
    
    public static List<String> createPartition(String diskMap, Map<Integer, Integer> idToSpaceMap)
    {
    	List<String> partition = new ArrayList<>();
        int currentFileID = 0;
        int diskMapLength = diskMap.length();
        
        for (int i = 0; i < diskMapLength; i ++)
        {
        	 int length = Character.getNumericValue(diskMap.charAt(i));

             if (i % 2 == 0)
    		 {
                 for (int j = 0; j < length; j++) {
                     partition.add(String.valueOf(currentFileID));
                 }
                 idToSpaceMap.put(currentFileID, length);
                 currentFileID++;
             } 
             else
             {
                 for (int j = 0; j < length; j++) {
                     partition.add(".");
                 }
             }
        }

        return partition;
    }
    
    public static long calculateChecksum(List<String> compactedPartition)
    {
        long checksum = 0;

        for (int i = 0; i < compactedPartition.size(); i++)
        {
        	String currentId = compactedPartition.get(i);
            if (!currentId.equals("."))
            {
                int fileId = Integer.valueOf(currentId);
                checksum += i * fileId;
            }
        }

        return checksum;
    }

    public static List<String> compactDisk1(List<String> partition) 
    {
    	List<String> newPartition = new ArrayList<String>(partition);
        for (int i = newPartition.size() - 1; i >= 0; i--) {
            if (!newPartition.get(i).equals(".")) 
            {
                for (int j = 0; j < i; j++) {
                    if (newPartition.get(j).equals(".")) 
                    {
                    	newPartition.set(j, newPartition.get(i));
                    	newPartition.set(i, ".");
                        break;
                    }
                }
            }
        }
        
        return newPartition;
    }
    public static List<String> compactDisk2(List<String> partition, Map<Integer, Integer> idToSpaceMap) 
    {
    	List<String> newPartition = new ArrayList<String>(partition);
        for (int i = newPartition.size() - 1; i >= 0; i--) 
        {
        	if (!newPartition.get(i).equals(".")) 
            { 
        		Integer partitionId = Integer.valueOf(newPartition.get(i));
        		Integer size = idToSpaceMap.get(partitionId);
            	
                for (int j = 0; j < i; j++) {
                    if (newPartition.get(j).equals(".")) 
                    {
                    	boolean isEnoughSpace = true;
                    	 for (int k = j; k < j + size; k++) 
                    	 {
                    		 isEnoughSpace = isEnoughSpace && newPartition.get(k).equals(".");
                         }
                    	 for (int k = j; k < j + size; k++) 
                    	 {
                    		 if (isEnoughSpace)
                    		 {
	                    		 newPartition.set(k, newPartition.get(i));
	                    		 newPartition.set(i, ".");
	                    		 if (k+1 != j + size)
	                    		 {
	                    			 i--;
	                    		 }
                    		 }
                         }
                		 if (isEnoughSpace)
                		 {
                			 break;
                		 }
                    }
                }
            }
        }
        
        return newPartition;
    }
}
