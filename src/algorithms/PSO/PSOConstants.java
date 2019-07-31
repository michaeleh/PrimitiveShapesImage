package algorithms.PSO;

/**
 * Constant class with parameters for pso algorithm.
 */
public final class PSOConstants {
    static final int MAX_SHAPES = 200;
    public static final int MIN_IMAGE_COORDINATE = 0;
    public static final int CHANNEL_MIN = 0;
    public static final int CHANNEL_MAX = 255;
    public static final String FILE_SPLIT_FORMAT = "\\.";
    public static final String IMAGE_FORMAT = "png";
    public static final String IMAGE_SUFFIX = "_primitive";
    static final int NUM_PARTICLES = 180;
    static final int VELOCITY_FACTOR = 10;
    public static final double W = 0.4;
    public static final double C1 = 2;
    public static final double C2 = 2;
    static final int MAX_ITERATIONS_WITHOUT_PROGRESS = 10;
}
