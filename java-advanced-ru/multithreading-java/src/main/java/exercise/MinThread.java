package exercise;

import java.util.Arrays;
import java.util.logging.Logger;

// BEGIN
public class MinThread extends Thread {

    private static final Logger LOGGER = Logger.getLogger("MinLogger");
    public int[] ints;

    public Integer minInt;

    public MinThread(int[] ints) {
        this.ints = ints;
    }

    @Override
    public void run() {
        LOGGER.info("MIN THREAD STARTED");
        minInt = Arrays.stream(ints)
                .min()
                .getAsInt();
        LOGGER.info("MIN THREAD FINISHED");
    }

    public Integer getMinInt() {
        return minInt;
    }
}
// END
