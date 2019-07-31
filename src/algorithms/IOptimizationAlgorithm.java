package algorithms;

import shapes.EShapeType;

import java.awt.image.BufferedImage;

/**
 * Optimization algorithm interface for recreating images from primitive shapes
 */
public interface IOptimizationAlgorithm {
    /**
     * recreate image from abstract shapes
     * @param original image received as input
     * @param shape to draw
     * @return original image recreated from abstract shape.
     */
    BufferedImage recreateFromPrimitive(BufferedImage original, EShapeType shape);
}
