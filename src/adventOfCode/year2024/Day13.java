package adventOfCode.year2024;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import adventOfCode.year2024.pojo.ClawMachine;
import helpers.InputHelper;
import helpers.LongPoint;
import helpers.Pair;

public class Day13
{
    static Map<Pair<Long, Integer>, Long> cache = new HashMap<>();

    public static void main(String... args) throws Exception
    {
        List<String> input = InputHelper.getInputAsListOfLines(13, false);

        Long tokens = extracted(input, 0);
        System.out.println("part 1: " + tokens);

        Long tokens2 = extracted(input, 10000000000000l);
        System.out.println("part 2: " + tokens2);
    }

    private static Long extracted(List<String> input, long l)
    {
        List<ClawMachine> machines = parseInput(input, l);
        return machines.stream().mapToLong(ClawMachine::computeTokens).sum();
    }

    public static List<ClawMachine> parseInput(List<String> input, long l)
    {
        List<ClawMachine> machines = new ArrayList<>();
        Pattern buttonAPattern = Pattern.compile("Button A: X\\+(\\d+), Y\\+(\\d+)");
        Pattern buttonBPattern = Pattern.compile("Button B: X\\+(\\d+), Y\\+(\\d+)");
        Pattern prizePattern = Pattern.compile("Prize: X=(\\d+), Y=(\\d+)");

        LongPoint buttonA = null, buttonB = null, prize = null;
        for (String line : input)
        {
            Matcher matcherA = buttonAPattern.matcher(line);
            Matcher matcherB = buttonBPattern.matcher(line);
            Matcher matcherPrize = prizePattern.matcher(line);

            if (matcherA.matches())
            {
                buttonA = new LongPoint(Long.parseLong(matcherA.group(1)), Long.parseLong(matcherA.group(2)));
            }
            else if (matcherB.matches())
            {
                buttonB = new LongPoint(Long.parseLong(matcherB.group(1)), Long.parseLong(matcherB.group(2)));
            }
            else if (matcherPrize.matches())
            {
                prize = new LongPoint(Long.parseLong(matcherPrize.group(1)) + l, Long.parseLong(matcherPrize.group(2)) + l);
            }

            if (buttonA != null && buttonB != null && prize != null)
            {
                machines.add(new ClawMachine(buttonA, buttonB, prize));
                buttonA = null;
                buttonB = null;
                prize = null;
            }
        }

        return machines;
    }

}
