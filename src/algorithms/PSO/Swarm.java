package algorithms.PSO;

import algorithms.IEvolutionaryGroup;
import shapes.EShapeType;
import utils.ObserverLatch;

import java.awt.image.BufferedImage;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static algorithms.PSO.PSOConstants.MAX_ITERATIONS;
import static algorithms.PSO.PSOConstants.MAX_PARTICLES;

public class Swarm implements IEvolutionaryGroup {
    private final ObserverLatch psoCycleLatch;
    private Particle[] swarm;
    private HistoryBest historyBest;
    private ExecutorService executor = Executors.newCachedThreadPool();
    private ObserverLatch latch;
    private int iterationWithoutProgress = 0;

    Swarm(BufferedImage original, BufferedImage previousImage, EShapeType shapeType, ObserverLatch psoCycleLatch) {
        swarm = new Particle[MAX_PARTICLES];
        latch = new ObserverLatch();

        for (int i = 0; i < MAX_PARTICLES; i++) {
            swarm[i] = new Particle(original,previousImage, shapeType, latch);
        }
        historyBest = new HistoryBest();
        this.psoCycleLatch = psoCycleLatch;
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
        iterationWithoutProgress++;
        setBest();
        psoCycleLatch.countdown();
    }

    private void setBest() {
        for (Particle particle : swarm) {
            double fitness = particle.getFitness();
            if (fitness < historyBest.getFitness()){
                historyBest.setBest(fitness, particle);
                this.iterationWithoutProgress = 0;
            }
        }
    }

    @Override
    public void evolve() {
        for (Particle particle : swarm) {
            particle.evolve(historyBest.getBest());
        }
    }

    HistoryBest getTotalBest() {
        return historyBest;
    }

    void close() {
        executor.shutdownNow();
    }

    boolean isDone() {
        return iterationWithoutProgress > MAX_ITERATIONS;
    }
}
