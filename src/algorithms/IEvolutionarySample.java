package algorithms;

/**
 * a sample of evolutionary subject, evolves consider another subject.
 */
public interface IEvolutionarySample extends IEvolutionaryCycle {
    /**
     * evolve a subject in the group consider another subject
     * @param bestFit subject to evolve from
     */
    void evolve(IEvolutionarySample bestFit);
}
