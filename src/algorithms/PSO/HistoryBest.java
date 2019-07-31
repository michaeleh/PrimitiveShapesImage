package algorithms.PSO;

import java.awt.image.BufferedImage;

/**
 * History Best class stores the particle with the best fitness score
 */
class HistoryBest {
    // initiate with worst possible fitness
    private double fitness = Double.MAX_VALUE;
    private Particle best;

    /**
     * Setting new Best Particle
     *
     * @param score of the new particle
     * @param best  bew best particle
     */
    void setBest(double score, Particle best) {
        fitness = score;
        this.best = best.cloneParticle();
    }

    /**
     * Getting the best particle ever's image
     *
     * @return BufferedImage of best particle
     */
    BufferedImage getImage() {
        return best.getImage();
    }

    Particle getBest() {
        return best;
    }

    double getFitness() {
        return fitness;
    }
}
