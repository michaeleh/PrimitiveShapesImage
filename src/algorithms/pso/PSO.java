package algorithms.pso;

import algorithms.IEvolutionaryGroup;
import algorithms.IOptimizationAlgorithm;
import shapes.EShapeType;
import utils.ImageUtils;

import java.awt.image.BufferedImage;

import static algorithms.pso.PSOConstants.MAX_SHAPES;

/**
 * Particle Swarm Optimization algorithm cycle
 */
public class PSO implements IOptimizationAlgorithm {

    @Override
    public BufferedImage recreateFromPrimitive(BufferedImage original, EShapeType shape) {
        // new blank image with the same canvas attributes as the original
        BufferedImage image = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());

        // until max shapes has reached
        for (int shapesIndex = 0; shapesIndex < MAX_SHAPES; shapesIndex++) {
            IEvolutionaryGroup swarm = new Swarm(original, image, shape);
            swarm.init();
            // while swarm is still optimizing
            while (!swarm.isDone()) {
                swarm.calculateFitness();
                image = (BufferedImage) swarm.getTotalBest().getProduct();
                swarm.evolve();
            }
            ImageUtils.display(image);
            swarm.close();
        }
        return image;
    }

}

