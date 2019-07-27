package algorithms.PSO;

import algorithms.IEvolutionaryMemberConsiderBestFit;
import shapes.AbstractVelocityShape;
import shapes.EShapeType;
import utils.ImageUtils;
import utils.ObserverLatch;

import java.awt.*;
import java.awt.image.BufferedImage;

import static algorithms.PSO.PSOConstants.MAX_SHAPES;

public class Particle implements IEvolutionaryMemberConsiderBestFit {
    private final ObserverLatch latch;
    private AbstractVelocityShape[] velocityShapes;
    private double fitness;
    private BufferedImage image, original;

    Particle(BufferedImage original, EShapeType shapeType, ObserverLatch latch) {
        velocityShapes = new AbstractVelocityShape[MAX_SHAPES];
        for (int i = 0; i < MAX_SHAPES; i++) {
            velocityShapes[i] = new AbstractVelocityShape(shapeType);
        }
        this.original = original;
        this.latch = latch;
    }

    @Override
    public void init() {
        for (AbstractVelocityShape velocityShape : velocityShapes) {
            velocityShape.getShape().init(original.getWidth(), original.getHeight());
            velocityShape.getVelocity().init(original.getWidth(), original.getHeight());
        }
    }

    @Override
    public void calculateFitness() {
        image = new BufferedImage(original.getWidth(),original.getHeight(),original.getType());
        Graphics2D graphics = image.createGraphics();
        for (AbstractVelocityShape velocityShape: velocityShapes){
            velocityShape.getShape().draw(graphics);
        }
        graphics.dispose();
        fitness = ImageUtils.calcImageSimilarity(image, original);
        latch.countdown();
    }

    @Override
    public void evolve(IEvolutionaryMemberConsiderBestFit bestFit) {
        Particle particle = (Particle) bestFit;
    }

    double getFitness() {
        return fitness;
    }

    BufferedImage getImage() {
        return image;
    }
}
