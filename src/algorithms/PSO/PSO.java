package algorithms.PSO;

import algorithms.IEvolutionaryGroup;
import algorithms.IOptimizationAlgorithm;
import shapes.EShapeType;
import utils.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

import static algorithms.PSO.PSOConstants.MAX_ITERATIONS;
import static algorithms.PSO.PSOConstants.MAX_SHAPES;

public class PSO implements IEvolutionaryGroup, IOptimizationAlgorithm {
    private Swarm swarm;

    @Override
    public BufferedImage recreateFromPrimitive(BufferedImage original, EShapeType shape) {
        BufferedImage image = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(ImageUtils.getAvgColor(original));
        graphics.fillRect(0,0,original.getWidth(),original.getHeight());
        graphics.dispose();
        int iteration = 1;
        for (int shapes = 0; shapes < MAX_SHAPES; shapes++) {
            System.out.println("Shape: " + (shapes + 1));
            swarm = new Swarm(original, image, shape);
            init();

            while (!swarm.isDone() && iteration<= MAX_ITERATIONS) {
                System.out.println("Iteration: " + iteration++);
                calculateFitness();
                image = swarm.getTotalBest();
                ImageUtils.display(image);
                evolve();
            }
            swarm.close();
            iteration = 0;

        }
        return image;
    }

    @Override
    public void init() {
        swarm.init();
    }

    @Override
    public void calculateFitness() {
        swarm.calculateFitness();
    }

    @Override
    public void evolve() {
        swarm.evolve();
    }
}

