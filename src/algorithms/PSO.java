package algorithms;

import shapes.AbstractShape;
import shapes.EShapeType;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PSO implements IOptimizationAlgorithm {


    @Override
    public BufferedImage recreateFromPrimitive(BufferedImage original, EShapeType shape) {
        BufferedImage image =  new BufferedImage ( 1000, 1000, BufferedImage.TYPE_INT_ARGB );
        Graphics2D graphics2D = image.createGraphics();
        AbstractShape t1 = shape.getShape();
        t1.getLocation().setX(200);
        t1.getLocation().setY(200);
        t1.getScale().setValue(100);
        t1.draw(graphics2D);
        graphics2D.dispose();
        return image;
    }
}
