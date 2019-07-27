package algorithms.PSO;

public class PSOConstants {
    static final int MAX_SHAPES = 10;
    public static final int MIN_IMAGE_COORDINATE = 0;
    public static final int CHANNEL_MIN = 0;
    public static final int CHANNEL_MAX = 255;
    public static final String FILE_SPLIT_FORMAT = "\\.";
    public static final String IMAGE_FORMAT = "png";
    public static final String IMAGE_SUFFIX = "_primitive";
    static final int MAX_PARTICLES = 100;
    static final int VELOCITY_FACTOR = 40;
    public static final double W = 0.9;
    public static final double C1 = 2;
    public static final double C2 = 2;
//    static final int TIME_CAP = 10000;
    static final int TIME_CAP = 1000 * 60 ;
}
