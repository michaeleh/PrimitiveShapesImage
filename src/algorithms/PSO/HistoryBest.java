package algorithms.PSO;

import java.awt.image.BufferedImage;

class HistoryBest {
    private double fitness = Double.MAX_VALUE;
    private Particle best;

    void setBest(double score, Particle best) {
        fitness = score;
        this.best = best.cloneParticle();
        System.out.println("New Best: " + fitness);

    }

    BufferedImage getImage() {
        return best.getImage();
    }

    Particle getBest() {
        return best;
    }

    public double getFitness() {
        return fitness;
    }
}
