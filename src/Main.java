import algorithms.EOptimizationTypes;
import algorithms.IOptimizationAlgorithm;
import shapes.EShapeType;
import utils.ImageUtils;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        ImageUtils imageUtils = new ImageUtils("C:\\michael\\work\\Dss\\mona.jpg");
        BufferedImage image = imageUtils.readImage();
        IOptimizationAlgorithm optimizationAlgorithm = EOptimizationTypes.PSO.getAlgorithmImpl();
        EShapeType eShapeType = EShapeType.CIRCLE;

        BufferedImage newImage = optimizationAlgorithm.recreateFromPrimitive(image, eShapeType);
        System.out.println("Best similar: "+ImageUtils.calcImageDiff(newImage));

        imageUtils.writeImage(newImage);
        float millis = System.currentTimeMillis() - start;
        System.out.println(millis/(1000 * 60));

    }
}
