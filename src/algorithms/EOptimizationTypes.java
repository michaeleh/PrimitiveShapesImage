package algorithms;

import algorithms.pso.ParticleSwarmOptimization;

/**
 * Enum to hold optimization algorithms and implantation we support
 */
public enum EOptimizationTypes {
    PSO{
        @Override
        public IOptimizationAlgorithm getAlgorithmImpl() {
            return new ParticleSwarmOptimization();
        }
    };
    public abstract IOptimizationAlgorithm getAlgorithmImpl();
}
