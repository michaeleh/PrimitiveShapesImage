package imageutils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtils {
    private String imgPath;

    public ImageUtils(String imgPath) {
        this.imgPath = imgPath;
    }

    public BufferedImage readImage() throws IOException {
        return ImageIO.read(new File(imgPath));
    }

    public void writeImage(BufferedImage image) throws IOException {
        String[] nameSplit = imgPath.split("\\.");
        String name = nameSplit[0]+"_primitive";
        String format = nameSplit[1];
        ImageIO.write(image,"png",new File(name+".png"));
    }
}
