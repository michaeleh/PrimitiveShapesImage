package algorithms.PSO;

import algorithms.IEvolutionaryGroup;
import algorithms.IOptimizationAlgorithm;
import shapes.EShapeType;
import utils.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

import static algorithms.PSO.PSOConstants.MAX_SHAPES;

public class PSO implements IEvolutionaryGroup, IOptimizationAlgorithm {
    private Swarm swarm;

    @Override
    public BufferedImage recreateFromPrimitive(BufferedImage original, EShapeType shape) {
        BufferedImage image = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(ImageUtils.getAvgColor(original));
        graphics.fillRect(0, 0, original.getWidth(), original.getHeight());
        graphics.dispose();
        for (int shapesIndex = 0; shapesIndex < MAX_SHAPES; shapesIndex++) {
            System.out.println("Shapes: " + shapesIndex);
            swarm = new Swarm(original, image, shape);
            init();
            while (!swarm.isDone()) {
                calculateFitness();
                image = swarm.getTotalBest();
                ImageUtils.display(image);
                evolve();
            }
            swarm.close();
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

