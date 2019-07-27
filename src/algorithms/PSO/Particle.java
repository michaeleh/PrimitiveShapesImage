package algorithms.PSO;

import algorithms.IEvolutionaryAlgorithm;
import shapes.AbstractVelocityShape;
import shapes.EShapeType;

import java.awt.image.BufferedImage;

import static algorithms.PSO.PSOConstants.MAX_SHAPES;

public class Particle implements IEvolutionaryAlgorithm {
    private final BufferedImage image;
    private AbstractVelocityShape[] velocityShapes;
    private int width, height;

    Particle(int width, int height, int type, EShapeType shapeType) {
        velocityShapes = new AbstractVelocityShape[MAX_SHAPES];
        for (int i = 0; i < MAX_SHAPES; i++) {
            velocityShapes[i] = new AbstractVelocityShape(shapeType);
        }
        image = new BufferedImage(width, height, type);
        this.width = width;
        this.height = height;
    }

    @Override
    public void init() {
        for (AbstractVelocityShape velocityShape : velocityShapes) {
            velocityShape.getShape().init(width, height);
            velocityShape.getVelocity().init(width, height);
        }
    }

    @Override
    public void calculateFitness() {

    }

    @Override
    public void evolve() {

    }
}
