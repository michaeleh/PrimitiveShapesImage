package algorithms.PSO;

import algorithms.IEvolutionaryGroup;
import shapes.EShapeType;

import java.awt.image.BufferedImage;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static algorithms.PSO.PSOConstants.MAX_ITERATIONS_WITHOUT_PROGRESS;
import static algorithms.PSO.PSOConstants.NUM_PARTICLES;

/**
 * Swarm to hold all the particles, manages their actions and deciding overall best.
 */
public class Swarm implements IEvolutionaryGroup {
    private Particle[] swarm;
    private HistoryBest historyBest;
    private ExecutorService executor = Executors.newCachedThreadPool();
    private CyclicBarrier fitnessBarrier;
    private int iterationWithoutProgress = 0;

    Swarm(BufferedImage original, BufferedImage previousImage, EShapeType shapeType) {
        swarm = new Particle[NUM_PARTICLES];
        fitnessBarrier = new CyclicBarrier(NUM_PARTICLES+1);
        for (int i = 0; i < NUM_PARTICLES; i++) {
            swarm[i] = new Particle(original, previousImage, shapeType, fitnessBarrier);
        }
        historyBest = new HistoryBest();
    }

    /**
     * Initiate all the particles
     */
    @Override
    public void init() {
        for (Particle particle : swarm) {
            particle.init();
        }
    }

    /**
     * calculating the fitness of all particles and deciding on global best
     **/
    @Override
    public void calculateFitness() {
        fitnessBarrier.reset();
        for (Particle particle : swarm) {
            executor.submit(particle::calculateFitness);
        }
        // wait for all particles to finish
        try {
            fitnessBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        // progress number of iterations
        iterationWithoutProgress++;
        // set global best
        setBest();
    }

    /**
     * If global best was found in this iteration then update and reset iteration counter.
     */
    private void setBest() {
        for (Particle particle : swarm) {
            double fitness = particle.getFitness();
            if (fitness < historyBest.getFitness()) {
                historyBest.setBest(fitness, particle);
                this.iterationWithoutProgress = 0;
            }
        }
    }

    /**
     * evolve all particles considering all time best
     */
    @Override
    public void evolve() {
        Particle best = historyBest.getBest();
        for (Particle particle : swarm) {
            particle.evolve(best);
        }
    }


    BufferedImage getTotalBestImage() {
        return historyBest.getImage();
    }

    void close() {
        executor.shutdownNow();
    }

    /**
     * Swarm optimization is done is counter exceeded max iteration without any change
     * @return if should stop the swarm cycle.
     */
    boolean isDone() {
        return iterationWithoutProgress > MAX_ITERATIONS_WITHOUT_PROGRESS;
    }
}
