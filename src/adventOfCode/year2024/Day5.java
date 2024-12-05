package adventOfCode.year2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import helpers.InputHelper;

public class Day5
{
    public static void main(String... args) throws Exception
	{
        List<List<Integer>> rules = InputHelper.getInputAsGenericList(51, false, line -> {
            List<Integer> list = new ArrayList<>();
            list.add(Integer.parseInt(line.split("\\|")[0]));
            list.add(Integer.parseInt(line.split("\\|")[1]));
            return list;
        });
        
        List<List<Integer>> pageToSort = InputHelper.getInputAsGenericList(52, false, lineParser());

        Integer count1 = 0;
        Integer count2 = 0;
        PageComparator comparator = new PageComparator(rules);
        
		for (List<Integer> list : pageToSort)
        {
		    List<Integer> listOld = new ArrayList<>(list);
	        Collections.sort(list, comparator);
            if (listOld.equals(list))
            {
                count1 += list.get(list.size()/2);
            }
            else
            {
                count2 += list.get(list.size()/2);
            }
        }
        System.out.printf("part 1 :" + count1 + "\n");
        System.out.printf("part 2 :" + count2 + "\n");
	}

    protected static class PageComparator implements Comparator<Integer>
    {
        private final List<List<Integer>> rules;

        public PageComparator(List<List<Integer>> rules) {
            this.rules = rules;
        }
        
        @Override
        public int compare(Integer page1, Integer page2)
        {
            for (List<Integer> list : rules)
            {
                if (list.get(0).equals(page1) && list.get(1).equals(page2))
                {
                    return -1;
                }
                if (list.get(1).equals(page1) && list.get(0).equals(page2))
                {
                    return 1;
                }
            }
            return 0;
        }
    }
    
    private static Function<String, List<Integer>> lineParser()
    {
        return line -> Arrays.stream(line.split(","))
                      .map(Integer::parseInt)
                      .collect(Collectors.toList());
    }
    
}