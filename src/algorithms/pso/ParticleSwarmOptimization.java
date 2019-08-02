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
public class ParticleSwarmOptimization implements IOptimizationAlgorithm {

    @Override
    public BufferedImage recreateFromPrimitive(BufferedImage original, EShapeType shape) {
        // new blank image with the same canvas attributes as the original
        BufferedImage image = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
        double bestFitness = Double.MAX_VALUE;
        // until max shapes has reached
        for (int shapesIndex = 0; shapesIndex < MAX_SHAPES; shapesIndex++) {
            System.out.println("Shape: " + shapesIndex);
            IEvolutionaryGroup swarm = new Swarm(original, image, shape);
            swarm.init();
            // while swarm is still optimizing
            while (!swarm.isDone()) {
                swarm.calculateFitness();
                swarm.evolve();
            }

            double swarmsFitness = swarm.getTotalBest().getFitness();
            if (swarmsFitness < bestFitness) {
                bestFitness = swarmsFitness;
                image = (BufferedImage) swarm.getTotalBest().getProduct();
                ImageUtils.display(image);
            } else {
                shapesIndex--;
                System.out.println("retrying");
            }


            swarm.close();
        }
        return image;
    }
}

