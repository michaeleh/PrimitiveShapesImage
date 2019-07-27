package algorithms.PSO;

import algorithms.IEvolutionaryAlgorithm;
import algorithms.IOptimizationAlgorithm;
import shapes.EShapeType;

import java.awt.image.BufferedImage;

public class PSO implements IEvolutionaryAlgorithm, IOptimizationAlgorithm {
    private Swarm swarm;

    @Override
    public BufferedImage recreateFromPrimitive(BufferedImage original, EShapeType shape) {
        swarm = new Swarm(original.getWidth(), original.getHeight(), original.getType(), shape);
        init();
        return null;
    }

    @Override
    public void init() {
        swarm.init();
    }

    @Override
    public void calculateFitness() {

    }

    @Override
    public void evolve() {

    }
}

