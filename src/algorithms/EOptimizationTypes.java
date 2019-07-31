package algorithms;

import algorithms.PSO.PSO;

/**
 * Enum to hold optimization algorithms and implantation we support
 */
public enum EOptimizationTypes {
    PSO{
        @Override
        public IOptimizationAlgorithm getAlgorithmImpl() {
            return new PSO();
        }
    };
    public abstract IOptimizationAlgorithm getAlgorithmImpl();
}
