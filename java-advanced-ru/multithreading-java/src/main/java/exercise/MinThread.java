package exercise;

// BEGIN
import java.util.logging.Logger;

public class MinThread extends Thread {
    private static final Logger logger = Logger.getLogger(MinThread.class.getName());
    private int[] numbers;
    private int minValue;

    public MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    public int getMinValue() {
        return minValue;
    }

    @Override
    public void run() {
        logger.info("Thread " + Thread.currentThread().getName() + " started");
        minValue = numbers[0];
        for (int number : numbers) {
            if (number < minValue) {
                minValue = number;
            }
        }
        logger.info("Thread " + Thread.currentThread().getName() + " finished");
    }
}
