package algorithms;

import algorithms.PSO.PSO;

public enum EOptimizationTypes {
    PSO{
        @Override
        public IOptimizationAlgorithm getAlgorithmImpl() {
            return new PSO();
        }
    };
    public abstract IOptimizationAlgorithm getAlgorithmImpl();
}
