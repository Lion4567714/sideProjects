/**
 * Stopwatch Class
 * 
 * Used for timing code. Helpful for finding where optimization is needed.
 */
public class Stopwatch {
    
    public double startTime;
    public double endTime;
    public double duration;

    public Stopwatch() {
        startTime = -1;
        endTime = -1;
        duration = -1;
    }

    public void start() {
        startTime = System.nanoTime();
        endTime = -1;
        duration = -1;
    }

    public double stop() {
        if (startTime == -1) {
            return -1;
        }

        endTime = System.nanoTime();
        duration = endTime - startTime;
        return duration;
    }

    public double getDurationInMilliseconds() {
        return duration / 1000000;
    }

    public double getDurationInSeconds() {
        return duration / 1000000000;
    }

    public void reset() {
        startTime = -1;
        endTime = -1;
        duration = -1;
    }

}
