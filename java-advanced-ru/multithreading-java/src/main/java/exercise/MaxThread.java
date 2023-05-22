package exercise;

import java.util.Arrays;
import java.util.logging.Logger;

// BEGIN
public class MaxThread extends Thread {

    private static final Logger LOGGER = Logger.getLogger("MaxThread");

    public int[] ints;

    public Integer maxInt;

    public MaxThread(int[] ints) {
        this.ints = ints;
    }

    @Override
    public void run() {
        LOGGER.info("MAX THREAD STARTED");
        maxInt = Arrays.stream(ints)
                .max()
                .getAsInt();
        LOGGER.info("MAX THREAD FINISHED");
    }

    public Integer getMaxInt() {
        return maxInt;
    }
}
// END
