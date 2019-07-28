package utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import static algorithms.PSO.PSOConstants.*;

public class ImageUtils {
    private static JFrame frame;
    private String imgPath;
    private static DataBuffer originalDataBuffer;

    public ImageUtils(String imgPath) {
        this.imgPath = imgPath;
    }

    public BufferedImage readImage() throws IOException {
        BufferedImage image = ImageIO.read(new File(imgPath));
        originalDataBuffer = image.getRaster().getDataBuffer();
        return image;
    }

    public void writeImage(BufferedImage image) throws IOException {
        String[] nameSplit = imgPath.split(FILE_SPLIT_FORMAT);
        String name = nameSplit[0] + IMAGE_SUFFIX;
        ImageIO.write(image, IMAGE_FORMAT, new File(name + "." + IMAGE_FORMAT));
    }

    public static double calcImageDiff(BufferedImage img) {
        DataBuffer dataBuffer = img.getRaster().getDataBuffer();
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < originalDataBuffer.getSize(); i++) {
            int originalElement = originalDataBuffer.getElem(i);
            int dataElement = dataBuffer.getElem(i);
            dotProduct += originalElement * dataElement;
            normA += Math.pow(originalElement, 2);
            normB += Math.pow(dataElement, 2);
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
        long redBucket = 0;
        long greenBucket = 0;
        long blueBucket = 0;
        long pixelCount = 0;

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color c = new Color(image.getRGB(x, y));
                pixelCount++;
                redBucket += c.getRed();
                greenBucket += c.getGreen();
                blueBucket += c.getBlue();
                // does alpha matter?
            }
        }

        int red = (int) (redBucket / pixelCount);
        int green = (int) (greenBucket / pixelCount);
        int blue = (int) (blueBucket / pixelCount);
        return new Color(red, green, blue);
    }
}
