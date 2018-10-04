package terning;

import java.util.Random;

public class Dice {
    // roll the die and return the value (1-6)
    public int roll() {
        Random r = new Random();
        int Low = 1;
        int High = 7;
        // Rolls random number between 0 and 7
        int Result = r.nextInt(6) + 1;
        return Result;
    }
    // roll the die n times and print the values
    public void rollMultiple(int n) {
        for (int i=1; i<=n; i++) {
            System.out.print(roll() + " ");
        }
    }
}
