package algorithms.PSO;

import algorithms.IEvolutionaryGroup;
import algorithms.IOptimizationAlgorithm;
import shapes.EShapeType;

import java.awt.image.BufferedImage;

import static algorithms.PSO.PSOConstants.TIME_CAP;

public class PSO implements IEvolutionaryGroup, IOptimizationAlgorithm {
    private Swarm swarm;

    @Override
    public BufferedImage recreateFromPrimitive(BufferedImage original, EShapeType shape) {
        swarm = new Swarm(original.getWidth(), original.getHeight(), original.getType(), shape);
        init();
        long startTime = System.currentTimeMillis();
        BufferedImage image = null;
        while (System.currentTimeMillis() - startTime < TIME_CAP) {
            swarm.calculateFitness();
            image = swarm.getTotalBest();
            swarm.evolve();
        }

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

