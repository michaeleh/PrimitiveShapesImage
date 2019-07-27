package algorithms;

public interface IEvolutionaryAlgorithm {
    void init();
    void calculateFitness();
    void evolve();
}
