package algorithms.PSO;

import algorithms.IEvolutionaryAlgorithm;
import shapes.EShapeType;

import static algorithms.PSO.PSOConstants.MAX_PARTICLES;

public class Swarm implements IEvolutionaryAlgorithm {
    private Particle[] swarm;

    Swarm(int width, int height, int imgType, EShapeType shapeType) {
        swarm = new Particle[MAX_PARTICLES];
        for (int i = 0; i < MAX_PARTICLES; i++) {
            swarm[i] = new Particle(width, height, imgType,shapeType);
        }
    }

    @Override
    public void init() {
        for (Particle particle : swarm) {
            particle.init();
        }
    }

    @Override
    public void calculateFitness() {

    }

    @Override
    public void evolve() {

    }
}
