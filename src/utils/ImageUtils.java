package utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import static algorithms.pso.PSOConstants.*;

/**
 * Image utilities of static functions
 */
public final class ImageUtils {
    private static JFrame frame;

    private ImageUtils() {
    }

    /**
     * Writes new image on new path with _primitive suffix
     *
     * @param image   to write
     * @param oldPath of original image
     * @throws IOException if failed to write image
     */
    public static void writeNewImage(BufferedImage image, String oldPath) throws IOException {
        String[] nameSplit = oldPath.split(FILE_SPLIT_FORMAT);
        String name = nameSplit[0] + IMAGE_SUFFIX;
        ImageIO.write(image, IMAGE_FORMAT, new File(name + "." + IMAGE_FORMAT));
    }

    /**
     * calculate image difference using Root Mean Squared Error between img1 and img2
     *
     * @param img1 Buffered Image
     * @param img2 Buffered Image
     * @return Root Mean Squared Error between data buffer value
     */
    public static double calcImageDiff(BufferedImage img1, BufferedImage img2) {
        DataBuffer dataBuffer = img1.getRaster().getDataBuffer();
        DataBuffer dataBuffer2 = img2.getRaster().getDataBuffer();
        double diff = 0;
        for (int i = 0; i < dataBuffer2.getSize(); i++) {
            int originalElement = dataBuffer2.getElem(i);
            int dataElement = dataBuffer.getElem(i);
            diff += Math.pow(originalElement - dataElement, 2);
        }
        return Math.sqrt(diff / dataBuffer2.getSize());
    }

    /**
     * display image on {@link JFrame}
     *
     * @param img to be displayed
     */
    public static void display(BufferedImage img) {
        if (frame == null) {
            frame = new JFrame();
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new JLabel(new ImageIcon(img)));
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Deep copy an image values
     *
     * @param image to be copied
     * @return new image
     */
    public static BufferedImage deepCopy(BufferedImage image) {
        ColorModel cm = image.getColorModel();
        boolean isAlphaPreMultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = image.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPreMultiplied, null);
    }
}
