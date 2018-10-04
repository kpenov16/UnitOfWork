package terning;

import java.lang.reflect.Array;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiceTest {

    int TestSize = 60000;
    int testError = 400;

    @org.junit.Test
    public void roll() {

        Dice dice = new Dice();
        int IntArray[] = new int[6];

        // Rolls die and adds results to correct field in IntArray
        for(int i=0; i<TestSize; i++) {
            int NewNumb = dice.roll();
            IntArray[NewNumb-1]++;
        }

        // Sets upper and lower Error-margin based on total die thrown
        double minBound =(TestSize/6)-testError;
        double maxBound =(TestSize/6)+testError;

        // Prints out results of the rolls
        // Asserts if counted die results lie within error-margin
        int totalThrows = 0;
        for(int i = 0; i < Array.getLength(IntArray); i++) {
            System.out.print((i+1)+": "+IntArray[i]+"  ");
            assertTrue(minBound <= IntArray[i] && IntArray[i] <= maxBound);
            System.out.println("Antal udfald af "+(i+1)+" ligger mellem "+minBound+" : "+maxBound);
            totalThrows += IntArray[i];
        }
        System.out.println("Expected total: "+TestSize);
        System.out.println("Total Found: " + totalThrows);
        assertEquals(TestSize, totalThrows);
    }
}