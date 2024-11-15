package exercise;

import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

import java.util.HashMap;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numbers) {
        MaxThread maxThread = new MaxThread(numbers);
        MinThread minThread = new MinThread(numbers);

        maxThread.start();
        minThread.start();

        try {
            maxThread.join();
            minThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Map<String, Integer> result = new HashMap<>();
        result.put("max", maxThread.getMaxValue());
        result.put("min", minThread.getMinValue());

        return result;
    }
    // END
}