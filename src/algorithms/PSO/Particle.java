package algorithms.PSO;

import algorithms.IEvolutionaryCycle;
import algorithms.IEvolutionaryMemberConsiderBestFit;
import shapes.AbstractVelocityShape;
import shapes.EShapeType;

import java.awt.image.BufferedImage;

import static algorithms.PSO.PSOConstants.MAX_SHAPES;

public class Particle implements IEvolutionaryMemberConsiderBestFit {
    private AbstractVelocityShape[] velocityShapes;
    private int width, height, imageType;
    private int fitness;
    private BufferedImage image;

    Particle(int width, int height, int imageType, EShapeType shapeType) {
        velocityShapes = new AbstractVelocityShape[MAX_SHAPES];
        for (int i = 0; i < MAX_SHAPES; i++) {
            velocityShapes[i] = new AbstractVelocityShape(shapeType);
        }
        this.imageType = imageType;
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
    public void evolve(IEvolutionaryMemberConsiderBestFit bestFit) {
        Particle particle = (Particle) bestFit;
    }

    int getFitness() {
        return fitness;
    }

    BufferedImage getImage() {
        return image;
    }
}
