package exercise;

// BEGIN
import java.util.logging.Logger;

public class MaxThread extends Thread {
    private static final Logger logger = Logger.getLogger(MaxThread.class.getName());
    private int[] numbers;
    private int maxValue;

    public MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    public int getMaxValue() {
        return maxValue;
    }

    @Override
    public void run() {
        logger.info("Thread " + Thread.currentThread().getName() + " started");
        maxValue = numbers[0];
        for (int number : numbers) {
            if (number > maxValue) {
                maxValue = number;
            }
        }
        logger.info("Thread " + Thread.currentThread().getName() + " finished");
    }
}
// END
