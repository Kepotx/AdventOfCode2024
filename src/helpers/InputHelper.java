package helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputHelper
{

	public static List<String> getInputAsListOfLines(int day) throws IOException
	{
        return getInputAsListOfLines(day, false);
	}
	
	public static List<String> getInputAsListOfLines(int day, boolean test) throws IOException
	{
        return Files.readAllLines(Path.of("inputs/day" + day + (test ? "test" : "") + ".txt"));
	}
	
	public static String getInputAsString(int day, boolean test) throws IOException
	{
		return Files.readString(Path.of("inputs/day" + day + (test ? "test" : "") + ".txt"));
	}

	public static List<List<Character>> getInputAsGridOfChars(int day, boolean test) throws IOException
    {
        return InputHelper.getInputAsGenericList(day, test, line -> line.chars().mapToObj(e -> (char) e).collect(Collectors.toList()));
    }
	public static <T> List<T> getInputAsGenericList(int day, boolean test, Function<String, T> mapToEntry) throws IOException
	{
        return getInputAsListOfLines(day, test)
                    .stream()
                    .map(mapToEntry)
                    .collect(Collectors.toList());
    }
	
	public static <T> List<T> parseInput(String input, String separatorRegex, Function<String, T> mapToEntry)
	{
        if (input == null || input.isBlank())
        {
            return List.of();
        }
        return Stream.of(input.split(separatorRegex))
                .map(String::trim)
                .map(mapToEntry)
                .collect(Collectors.toList());
    }

	/**
	 * @day day the day to parse
	 * @day separatorRegex the regex separator to use to split the inputs
	 */
	public static List<String> getInputWithSeparator(int day, boolean test, String separatorRegex) throws IOException
	{
        return Parser.parseInput(getInputAsString(day, test), separatorRegex);
    }
	
	/**
	 * @day day the day to parse
	 * @day separatorRegex the regex separator to use to split the inputs
	 */
	public static List<String> getInputWithSeparatorTrim(int day, boolean test, String separatorRegex) throws IOException
	{
        return Stream.of(getInputAsString(day, test).split(separatorRegex))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
