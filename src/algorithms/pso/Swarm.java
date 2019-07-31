package algorithms.pso;

import algorithms.IEvolutionaryGroup;
import algorithms.IEvolutionarySample;
import shapes.EShapeType;

import java.awt.image.BufferedImage;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static algorithms.pso.PSOConstants.MAX_ITERATIONS_WITHOUT_PROGRESS;
import static algorithms.pso.PSOConstants.NUM_PARTICLES;

/**
 * Swarm to hold all the particles, manages their actions and deciding overall best.
 */
public class Swarm implements IEvolutionaryGroup {
    private final IEvolutionarySample<BufferedImage>[] swarm;
    private final HistoryBest historyBest;
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final CyclicBarrier fitnessBarrier;
    private int iterationWithoutProgress = 0;

    Swarm(BufferedImage original, BufferedImage previousImage, EShapeType shapeType) {
        swarm = new Particle[NUM_PARTICLES];
        fitnessBarrier = new CyclicBarrier(NUM_PARTICLES + 1);
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
        for (IEvolutionarySample particle : swarm) {
            particle.init();
        }
    }

    /**
     * calculating the fitness of all particles and deciding on global best
     **/
    @Override
    public void calculateFitness() {
        for (IEvolutionarySample particle : swarm) {
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
        for (IEvolutionarySample particle : swarm) {
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
        IEvolutionarySample best = historyBest.getBest();
        for (IEvolutionarySample particle : swarm) {
            particle.evolve(best);
        }
    }

    @Override
    public IEvolutionarySample getTotalBest() {
        return historyBest.getBest();
    }


    @Override
    public void close() {
        executor.shutdownNow();
    }

    /**
     * Swarm optimization is done is counter exceeded max iteration without any change
     *
     * @return if should stop the swarm cycle.
     */
    @Override
    public boolean isDone() {
        return iterationWithoutProgress > MAX_ITERATIONS_WITHOUT_PROGRESS;
    }
}
