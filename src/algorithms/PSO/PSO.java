package algorithms.PSO;

import algorithms.IEvolutionaryGroup;
import algorithms.IOptimizationAlgorithm;
import shapes.EShapeType;
import utils.ImageUtils;

import java.awt.image.BufferedImage;

import static algorithms.PSO.PSOConstants.TIME_CAP;

public class PSO implements IEvolutionaryGroup, IOptimizationAlgorithm {
    private Swarm swarm;

    @Override
    public BufferedImage recreateFromPrimitive(BufferedImage original, EShapeType shape) {
        swarm = new Swarm(original, shape);
        init();
        long startTime = System.currentTimeMillis();
        BufferedImage image = null;
        int iteration = 1;
        while (System.currentTimeMillis() - startTime < TIME_CAP) {
            System.out.println("Iteration: " + iteration++);
            calculateFitness();
            image = swarm.getTotalBest();
            ImageUtils.display(image);
            evolve();
        }
        swarm.close();
        return image;
    }

    @Override
    public void init() {
        swarm.init();
    }

    @Override
    public void calculateFitness() {
        swarm.calculateFitness();
    }

    @Override
    public void evolve() {
        swarm.evolve();
    }
}

