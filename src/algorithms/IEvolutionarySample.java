package algorithms;

/**
 * a sample of evolutionary subject, evolves consider another subject.
 */
public interface IEvolutionarySample<T> extends IEvolutionaryCycle {
    /**
     * evolve a subject in the group consider another subject
     *
     * @param bestFit subject to evolve from
     */
    void evolve(IEvolutionarySample bestFit);

    /**
     * @return the value/ product if this sample.
     */
    T getProduct();

    /**
     * @return fitness value of sample
     */
    double getFitness();

    /**
     * Clone sample
     * @return new clone of this object
     */
    IEvolutionarySample<T> cloneSample();
}
