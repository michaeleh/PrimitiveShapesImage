package algorithms.PSO;

import algorithms.IEvolutionaryGroup;
import shapes.EShapeType;
import utils.ObserverLatch;

import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static algorithms.PSO.PSOConstants.MAX_PARTICLES;

public class Swarm implements IEvolutionaryGroup {
    private Particle[] swarm;
    private HistoryBest historyBest;
    private Particle globalBestParticle;
    private ExecutorService executor = Executors.newCachedThreadPool();
    private ObserverLatch latch;

    Swarm(BufferedImage original, EShapeType shapeType) {
        swarm = new Particle[MAX_PARTICLES];
        latch = new ObserverLatch();
        for (int i = 0; i < MAX_PARTICLES; i++) {
            swarm[i] = new Particle(original, shapeType, latch);
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
        latch.init(swarm.length);
        for (Particle particle : swarm) {
            executor.submit(particle::calculateFitness);
        }
        latch.await();
        setBest();
    }

    private void setBest() {
        double globalBestFitness = 0;

        for (Particle particle : swarm) {
            double fitness = particle.getFitness();
            if (fitness > globalBestFitness) {
                globalBestFitness = fitness;
                globalBestParticle = particle;
                historyBest.setIfBest(fitness, particle.getImage());
            }
        }
    }

    @Override
    public void evolve() {
        for (Particle particle : swarm) {
            particle.evolve(globalBestParticle);
        }
    }

    BufferedImage getTotalBest() {
        return historyBest.getImage();
    }
    void close(){
        executor.shutdownNow();

    }
}
