import algorithms.EOptimizationTypes;
import algorithms.IOptimizationAlgorithm;
import shapes.EShapeType;
import utils.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        String imgPath = "C:\\michael\\work\\Dss\\marlin.jpg";
        BufferedImage image = ImageIO.read(new File(imgPath));

        IOptimizationAlgorithm optimizationAlgorithm = EOptimizationTypes.PSO.getAlgorithmImpl();
        EShapeType eShapeType = EShapeType.OVAL;

        BufferedImage newImage = optimizationAlgorithm.recreateFromPrimitive(image, eShapeType);

        ImageUtils.writeNewImage(newImage, imgPath);
        float millis = System.currentTimeMillis() - start;
        System.out.println("Took: " + millis / (1000 * 60));

    }
}
