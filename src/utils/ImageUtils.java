package utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import static algorithms.PSO.PSOConstants.*;

public class ImageUtils {
    private static JFrame frame;
    private String imgPath;
    private static int[] imageVector;


    public ImageUtils(String imgPath) {
        this.imgPath = imgPath;
    }

    public BufferedImage readImage() throws IOException {
        BufferedImage image = ImageIO.read(new File(imgPath));
        imageVector = toVector(image);
        return image;
    }

    public void writeImage(BufferedImage image) throws IOException {
        String[] nameSplit = imgPath.split(FILE_SPLIT_FORMAT);
        String name = nameSplit[0] + IMAGE_SUFFIX;
        ImageIO.write(image, IMAGE_FORMAT, new File(name + "." + IMAGE_FORMAT));
    }

    public static double calcImageDiff(BufferedImage img) {
        int[] asVector = toVector(img);
        return cosineSimilarity(asVector, imageVector);
    }

    private static int[] toVector(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();

        int[] img1asVector = new int[height * width * 3];
        int index = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color color1 = new Color(img.getRGB(i, j));
                img1asVector[index] = color1.getRed();
                img1asVector[index + 1] = color1.getBlue();
                img1asVector[index + 2] = color1.getGreen();
                index = index + 3;
            }
        }
        return img1asVector;
    }

    private static double cosineSimilarity(int[] vectorA, int[] vectorB) {
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            normA += Math.pow(vectorA[i], 2);
            normB += Math.pow(vectorB[i], 2);
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }


    public static void display(BufferedImage img) {
        if (frame == null) {
            frame = new JFrame();
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new JLabel(new ImageIcon(img)));
        frame.pack();
        frame.setVisible(true);

    }

    public static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    public static Color getAvgColor(BufferedImage image) {
//        long redBucket = 0;
//        long greenBucket = 0;
//        long blueBucket = 0;
//        long pixelCount = 0;
//
//        for (int y = 0; y < image.getHeight(); y++) {
//            for (int x = 0; x < image.getWidth(); x++) {
//                Color c = new Color(image.getRGB(x, y));
//                pixelCount++;
//                redBucket += c.getRed();
//                greenBucket += c.getGreen();
//                blueBucket += c.getBlue();
//                // does alpha matter?
//            }
//        }
//
//        int red = (int) (redBucket / pixelCount);
//        int green = (int) (greenBucket / pixelCount);
//        int blue = (int) (blueBucket / pixelCount);
//        return new Color(red, green, blue);
        return new Color(image.getRGB(0,0));
    }
}
