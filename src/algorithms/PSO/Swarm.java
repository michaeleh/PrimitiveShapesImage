package algorithms.PSO;

import algorithms.IEvolutionaryCycle;
import algorithms.IEvolutionaryGroup;
import shapes.EShapeType;

import java.awt.image.BufferedImage;

import static algorithms.PSO.PSOConstants.MAX_PARTICLES;

public class Swarm implements IEvolutionaryGroup {
    private Particle[] swarm;
    private HistoryBest historyBest;
    private Particle globalBestParticle;

    Swarm(int width, int height, int imgType, EShapeType shapeType) {
        swarm = new Particle[MAX_PARTICLES];
        for (int i = 0; i < MAX_PARTICLES; i++) {
            swarm[i] = new Particle(width, height, imgType, shapeType);
        }

        historyBest = new HistoryBest();
    }

    @Override
    public void init() {
        for (Particle particle : swarm) {
            particle.init();
        }
    }

    @Override
    public void calculateFitness() {
        int globalBestFitness = 0;

        for (Particle particle : swarm) {
            particle.calculateFitness();
            int fitness = particle.getFitness();

            if (fitness > globalBestFitness) {
                globalBestFitness = fitness;
                globalBestParticle = particle;
                historyBest.setIfBest(fitness, particle.getImage());
            }
        }
    }

    @Override
    public void evolve() {
        for (Particle particle: swarm){
            particle.evolve(globalBestParticle);
        }
    }

    BufferedImage getTotalBest() {
        return historyBest.getImage();
    }
}
