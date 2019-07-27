package algorithms.PSO;

import algorithms.IEvolutionaryAlgorithm;
import shapes.AbstractShape;
import shapes.EShapeType;

import java.awt.*;
import java.awt.image.BufferedImage;

import static algorithms.PSO.PSOConstants.MAX_SHAPES;

public class Particle implements IEvolutionaryAlgorithm {
    private final BufferedImage image;
    private AbstractShape[] shapes;
    private int width,height;
    public Particle(int width, int height, int type, EShapeType shapeType) {
        shapes = new AbstractShape[MAX_SHAPES];
        for (int i=0; i<MAX_SHAPES; i++){
            shapes[i] = shapeType.getShape();
        }
        image = new BufferedImage(width, height, type);
        this.width = width;
        this.height = height;
    }

    @Override
    public void init() {
        for (AbstractShape shape : shapes) {
            shape.init(width, height);
        }
    }

    @Override
    public void calculateFitness() {

    }

    @Override
    public void evolve() {

    }
}
