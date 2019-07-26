package algorithms;

import shapes.AbstractShape;
import shapes.EShapeType;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface IOptimizationAlgorithm {
    BufferedImage recreateFromPrimitive(BufferedImage original, EShapeType shape);
}
