package adventOfCode.year2024.pojo;
import helpers.LongPoint;

public class ClawMachine
{
    private LongPoint buttonA;
    private LongPoint buttonB;
    private LongPoint prize;

    public ClawMachine(LongPoint buttonA, LongPoint buttonB, LongPoint prize)
    {
        this.buttonA = buttonA;
        this.buttonB = buttonB;
        this.prize = prize;
    }

    public LongPoint getButtonA() {
        return buttonA;
    }

    public LongPoint getButtonB() {
        return buttonB;
    }

    public LongPoint getPrize() {
        return prize;
    }

    public long computeTokens()
    {
        long denominator = buttonA.getX() * buttonB.getY() - buttonA.getY() * buttonB.getX();

        long a = (prize.getX() * buttonB.getY() - prize.getY() * buttonB.getX()) / denominator;
        long b = (prize.getY() * buttonA.getX() - prize.getX() * buttonA.getY()) / denominator;

        if (((a * buttonA.getX() + b * buttonB.getX()) == prize.getX()) && ((a * buttonA.getY() + b * buttonB.getY()) == prize.getY()))
        {
            return a*3+b;
        }
        else
        {
            return 0;
        }
    }
    
}