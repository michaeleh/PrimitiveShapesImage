package utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static algorithms.PSO.PSOConstants.*;

public class ImageUtils {
    private static JFrame frame;
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

    public static double calcImageDiff(BufferedImage img1, BufferedImage img2) {
        int width = img1.getWidth();
        int height = img1.getHeight();

        long diff = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                diff += pixelDiff(img1.getRGB(x, y), img2.getRGB(x, y));
            }
        }
        return Math.sqrt(diff / (4 * width * height));
    }

    private static int pixelDiff(int rgb1, int rgb2) {
        Color color1 = new Color(rgb1);
        Color color2 = new Color(rgb2);
        return (int) (Math.pow(color1.getRed() - color2.getRed(), 2)
                + Math.pow(color1.getGreen() - color2.getGreen(), 2)
                + Math.pow(color1.getBlue() - color2.getBlue(), 2)
                + Math.pow(color1.getAlpha() - color2.getAlpha(), 2));
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
}
