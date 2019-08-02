import algorithms.EOptimizationTypes;
import shapes.EShapeType;
import utils.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        String imgPath = "C:\\michael\\work\\Dss\\Demo\\mona.jpg";
        BufferedImage image = ImageIO.read(new File(imgPath));

        EOptimizationTypes optimizationAlgorithm = EOptimizationTypes.PSO;
        EShapeType eShapeType = EShapeType.OVAL;

        BufferedImage newImage = optimizationAlgorithm.getAlgorithmImpl().recreateFromPrimitive(image, eShapeType);

        ImageUtils.writeNewImage(newImage, imgPath);
        float millis = System.currentTimeMillis() - start;
        System.out.println("Took: " + millis / (1000 * 60));

    }
}
