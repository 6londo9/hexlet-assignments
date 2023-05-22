package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] ints) {
        Map<String, Integer> result = new HashMap<>();
        MaxThread maxThread = new MaxThread(ints);
        MinThread minThread = new MinThread(ints);

        maxThread.start();
        minThread.start();

        try {
            maxThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        result.put("max", maxThread.getMaxInt());
        result.put("min", minThread.getMinInt());

        return result;
    }
    // END
}
