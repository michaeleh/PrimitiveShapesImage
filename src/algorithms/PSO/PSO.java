package algorithms.PSO;

import algorithms.IEvolutionaryGroup;
import algorithms.IOptimizationAlgorithm;
import shapes.EShapeType;
import utils.ImageUtils;

import java.awt.image.BufferedImage;

import static algorithms.PSO.PSOConstants.MAX_SHAPES;

/**
 * Particle Swarm Optimization algorithm cycle
 */
public class PSO implements IEvolutionaryGroup, IOptimizationAlgorithm {
    private Swarm swarm;

    @Override
    public BufferedImage recreateFromPrimitive(BufferedImage original, EShapeType shape) {
        // new blank image with the same canvas attributes as the original
        BufferedImage image = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());

        // until max shapes has reached
        for (int shapesIndex = 0; shapesIndex < MAX_SHAPES; shapesIndex++) {
            System.out.println("Shapes: " + shapesIndex);
            swarm = new Swarm(original, image, shape);
            init();
            // while swarm is still optimizing
            while (!swarm.isDone()) {
                calculateFitness();
                image = swarm.getTotalBestImage();
                ImageUtils.display(image);
                evolve();
            }
            swarm.close();
        }
        return image;
    }

    /**
     * Initiate the swarm
     */
    @Override
    public void init() {
        swarm.init();
    }

    /**
     * calculate the swarm fitness
     */
    @Override
    public void calculateFitness() {
        swarm.calculateFitness();
    }

    /**
     * evolve the swarm
     */
    @Override
    public void evolve() {
        swarm.evolve();
    }
}

