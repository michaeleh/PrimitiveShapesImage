package algorithms;

/**
 * Evolutionary algorithms cycle
 */
public interface IEvolutionaryCycle {
    /**
     * initiate the cycles participants
     */
    void init();
    /**
     * calculate iteration fitness
     */
    void calculateFitness();

}
