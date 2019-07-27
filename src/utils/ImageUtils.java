package utils;

import javax.imageio.ImageIO;
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
}
