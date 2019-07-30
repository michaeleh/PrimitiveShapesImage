package algorithms.PSO;

import algorithms.IEvolutionaryGroup;
import algorithms.IOptimizationAlgorithm;
import shapes.EShapeType;
import utils.ImageUtils;
import utils.ObserverLatch;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static algorithms.PSO.PSOConstants.MAX_SHAPES;
import static algorithms.PSO.PSOConstants.NUM_OF_SWARMS;

public class PSO implements IEvolutionaryGroup, IOptimizationAlgorithm {
    private Swarm[] swarms = new Swarm[PSOConstants.NUM_OF_SWARMS];
    private ExecutorService swarmExecutor = Executors.newFixedThreadPool(NUM_OF_SWARMS);
    private ObserverLatch latch = new ObserverLatch();
    @Override
    public BufferedImage recreateFromPrimitive(BufferedImage original, EShapeType shape) {
        BufferedImage image = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());

        for (int shapesIndex = 0; shapesIndex < MAX_SHAPES; shapesIndex++) {
            System.out.println("Shapes: " + shapesIndex);
            for (int i = 0; i < PSOConstants.NUM_OF_SWARMS; i++) {
                swarms[i] = new Swarm(original, image, shape,latch);
            }
            init();
            while (!Arrays.stream(swarms).allMatch(Swarm::isDone)) {
                latch.init(NUM_OF_SWARMS);
                calculateFitness();
                double fitness = Double.MAX_VALUE;
                for (Swarm swarm : swarms) {
                    HistoryBest swarmBest = swarm.getTotalBest();
                    if (swarmBest.getFitness() < fitness) {
                        fitness = swarmBest.getFitness();
                        image = swarmBest.getImage();
                    }
                }
                evolve();
            }
            ImageUtils.display(image);
            for (Swarm swarm : swarms) {
                swarm.close();
            }
        }
        return image;
    }

    @Override
    public void init() {
        for (Swarm swarm : swarms) {
            swarm.init();
        }
    }

    @Override
    public void calculateFitness() {
        for (Swarm swarm : swarms) {
            swarmExecutor.submit(swarm::calculateFitness);
        }
        latch.await();
    }

    @Override
    public void evolve() {
        for (Swarm swarm : swarms) {
            swarm.evolve();
        }
    }
}

