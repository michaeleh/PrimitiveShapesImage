import algorithms.EOptimizationTypes;
import algorithms.IOptimizationAlgorithm;
import imageutils.ImageUtils;
import shapes.EShapeType;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ImageUtils imageUtils = new ImageUtils("C:\\michael\\work\\Dss\\marlin.jpg");
        BufferedImage image = imageUtils.readImage();

        IOptimizationAlgorithm optimizationAlgorithm = EOptimizationTypes.PSO.getAlgorithmImpl();
        EShapeType eShapeType = EShapeType.CIRCLE;

        BufferedImage newImage = optimizationAlgorithm.recreateFromPrimitive(image, eShapeType);
        imageUtils.writeImage(newImage);

    }
}
