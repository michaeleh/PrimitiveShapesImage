package algorithms.pso;

import algorithms.IEvolutionarySample;

/**
 * History Best class stores the particle with the best fitness score
 */
class HistoryBest {
    // initiate with worst possible fitness
    private double fitness = Double.MAX_VALUE;
    private IEvolutionarySample best;

    /**
     * Setting new Best Particle
     *
     * @param score of the new particle
     * @param best  bew best particle
     */
    void setBest(double score, IEvolutionarySample best) {
        fitness = score;
        this.best = best.cloneSample();
    }


    IEvolutionarySample getBest() {
        return best;
    }

    double getFitness() {
        return fitness;
    }
}
