package adventOfCode.year2024;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import helpers.InputHelper;

public class Day4
{
	public static void main(String... args) throws Exception
	{
		List<List<Character>> listOfChar = InputHelper.getInputAsGridOfChars(4, false);

		System.out.printf("part 1 : " + countOccurrences(listOfChar, "XMAS") + "\n");
		System.out.printf("part 2 : " + findXMASCross(listOfChar) + "\n");
	}

	private static Function<String, List<Character>> lineParser()
	{
		return line -> line.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
	}

	public static long countOccurrences(List<List<Character>> listOfChar, String targetWord)
	{
		List<String> words = generateAllWords(listOfChar);
		return words.stream().mapToLong(word -> countSubstring(word, targetWord)).sum();
	}

	private static long countSubstring(String word, String targetWord)
	{
		int index = 0;
		int count = 0;
		while ((index = word.indexOf(targetWord, index)) != -1)
		{
			count++;
			index += 1;
		}
		return count;
	}

	public static List<String> generateAllWords(List<List<Character>> grid)
	{
		int rows = grid.size();
		int cols = grid.get(0).size();
		List<String> allWords = new ArrayList<>();

		for (List<Character> row : grid)
		{
			StringBuilder sb = new StringBuilder();
			for (Character ch : row)
			{
				sb.append(ch);
			}
			allWords.add(sb.toString());
			allWords.add(sb.reverse().toString());
		}

		for (int col = 0; col < cols; col++)
		{
			StringBuilder sb = new StringBuilder();
			for (int row = 0; row < rows; row++)
			{
				sb.append(grid.get(row).get(col));
			}
			allWords.add(sb.toString());
			allWords.add(sb.reverse().toString());
		}

		for (int start = 0; start < rows + cols - 1; start++)
		{
			StringBuilder topLeftToBottomRight = new StringBuilder();
			for (int row = 0; row < rows; row++)
			{
				int col = start - row;
				if (col >= 0 && col < cols)
				{
					topLeftToBottomRight.append(grid.get(row).get(col));
				}
			}
			allWords.add(topLeftToBottomRight.toString());
			allWords.add(topLeftToBottomRight.reverse().toString());
		}

		for (int start = 0; start < rows + cols - 1; start++)
		{
			StringBuilder topRightToBottomLeft = new StringBuilder();
			for (int row = 0; row < rows; row++)
			{
				int col = (cols - 1) - (start - row);
				if (col >= 0 && col < cols)
				{
					topRightToBottomLeft.append(grid.get(row).get(col));
				}
			}
			allWords.add(topRightToBottomLeft.toString());
			allWords.add(topRightToBottomLeft.reverse().toString());
		}

		return allWords;
	}

	public static int findXMASCross(List<List<Character>> listOfChar)
	{
		int rows = listOfChar.size();
		int cols = listOfChar.get(0).size();
		int count = 0;

		for (int row = 1; row < rows - 1; row++)
		{
			for (int col = 1; col < cols - 1; col++)
			{
				if (isXMAS(listOfChar, row, col))
				{
					count++;
				}
			}
		}

		return count;
	}

	private static boolean isXMAS(List<List<Character>> grid, int row, int col)
	{
		boolean topLeftToBottomRight = (grid.get(row - 1).get(col - 1) == 'M'
				&& grid.get(row).get(col) == 'A'
				&& grid.get(row + 1).get(col + 1) == 'S')
				|| (grid.get(row - 1).get(col - 1) == 'S'
				&& grid.get(row).get(col) == 'A'
				&& grid.get(row + 1).get(col + 1) == 'M');

		boolean topRightToBottomLeft = (grid.get(row - 1).get(col + 1) == 'M'
				&& grid.get(row).get(col) == 'A'
				&& grid.get(row + 1).get(col - 1) == 'S')
				|| (grid.get(row - 1).get(col + 1) == 'S'
				&& grid.get(row).get(col) == 'A'
				&& grid.get(row + 1).get(col - 1) == 'M');

		return topLeftToBottomRight && topRightToBottomLeft;
	}
}