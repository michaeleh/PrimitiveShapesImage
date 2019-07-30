import algorithms.EOptimizationTypes;
import algorithms.IOptimizationAlgorithm;
import shapes.EShapeType;
import utils.ImageUtils;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        ImageUtils imageUtils = new ImageUtils("C:\\michael\\work\\Dss\\marlin.jpg");
        BufferedImage image = imageUtils.readImage();
        IOptimizationAlgorithm optimizationAlgorithm = EOptimizationTypes.PSO.getAlgorithmImpl();
        EShapeType eShapeType = EShapeType.CIRCLE;

        BufferedImage newImage = optimizationAlgorithm.recreateFromPrimitive(image, eShapeType);

        imageUtils.writeImage(newImage);
        float millis = System.currentTimeMillis() - start;
        System.out.println("Took: "+millis/(1000 * 60));

    }
}
