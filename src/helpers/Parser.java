package helpers;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parser {

	public static List<String> parseInput(String input, String separatorRegex)
	{
        return Stream.of(input.split(separatorRegex))
                .collect(Collectors.toList());
    }
	
	public static <T> List<T> parseInput(String input, String separatorRegex, Function<String, T> mapToEntry)
	{
        return Stream.of(input.split(separatorRegex))
                .map(mapToEntry)
                .collect(Collectors.toList());
    }
}
