package algorithms;

/**
 * Evolutionary group holders extends the cycle and supports evolve
 */
public interface IEvolutionaryGroup extends IEvolutionaryCycle{
    /**
     * evolve the group
     */
    void evolve();

    /**
     * Gets total best subject of group
     * @return total best
     */
    IEvolutionarySample getTotalBest();

    /**
     * checks if group is done evolving
     * @return is done evolving
     */
    boolean isDone();

    /**
     * close all the group open tasks
     */
    void close();
}
