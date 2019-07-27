package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static algorithms.PSO.PSOConstants.*;

public class ImageUtils {
    private String imgPath;

    public ImageUtils(String imgPath) {
        this.imgPath = imgPath;
    }

    public BufferedImage readImage() throws IOException {
        return ImageIO.read(new File(imgPath));
    }

    public void writeImage(BufferedImage image) throws IOException {
        String[] nameSplit = imgPath.split(FILE_SPLIT_FORMAT);
        String name = nameSplit[0] + IMAGE_SUFFIX;
        String format = nameSplit[1];
        ImageIO.write(image, IMAGE_FORMAT, new File(name + "." + IMAGE_FORMAT));
    }

    public static double calcImageSimilarity(BufferedImage img1, BufferedImage img2){
        int width = img1.getWidth();
        int height = img1.getHeight();

        long diff = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                diff += pixelDiff(img1.getRGB(x, y), img2.getRGB(x, y));
            }
        }
        long maxDiff = 3L * 255 * width * height;

        return 100 -  100.0 * diff / maxDiff;
    }

    private static int pixelDiff(int rgb1, int rgb2) {
        Color color1 = new Color(rgb1);
        Color color2 = new Color(rgb2);
        return Math.abs(color1.getRed() - color2.getRed())
                + Math.abs(color1.getGreen() - color2.getGreen())
                + Math.abs(color1.getBlue() - color2.getBlue())
                + Math.abs(color1.getAlpha() - color2.getAlpha());
    }
}
