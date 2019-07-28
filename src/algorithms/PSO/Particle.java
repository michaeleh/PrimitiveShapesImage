package algorithms.PSO;

import algorithms.IEvolutionarySample;
import shapes.AbstractVelocityShape;
import shapes.EShapeType;
import utils.ImageUtils;
import utils.ObserverLatch;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import static algorithms.PSO.PSOConstants.MAX_SHAPES;
import static algorithms.PSO.PSOConstants.VELOCITY_FACTOR;

public class Particle implements IEvolutionarySample {
    private final ObserverLatch latch;
    private final EShapeType shapeType;
    private AbstractVelocityShape[] velocityShapes;
    private double fitness;
    private BufferedImage image, original;
    private Particle personalBest;

    Particle(BufferedImage original, EShapeType shapeType, ObserverLatch latch) {
        velocityShapes = new AbstractVelocityShape[MAX_SHAPES];
        for (int i = 0; i < MAX_SHAPES; i++) {
            velocityShapes[i] = new AbstractVelocityShape(shapeType);
        }
        this.shapeType = shapeType;
        this.original = original;
        this.latch = latch;
    }

    @Override
    public void init() {
        for (AbstractVelocityShape velocityShape : velocityShapes) {
            velocityShape.getShape().init(original.getWidth(), original.getHeight());
            velocityShape.getVelocity().init(original.getWidth(), original.getHeight(),VELOCITY_FACTOR);
        }
    }

    @Override
    public void calculateFitness() {
        image = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
        Graphics2D graphics = image.createGraphics();
        for (AbstractVelocityShape velocityShape : velocityShapes) {
            velocityShape.getShape().draw(graphics);
        }
        graphics.dispose();
        fitness = ImageUtils.calcImageDiff(image);
        latch.countdown();
        if (personalBest == null || personalBest.getFitness() < fitness) {
            personalBest = cloneParticle();
        }

    }

    @Override
    public void evolve(IEvolutionarySample globalBest) {
        for (int i = 0; i < velocityShapes.length; i++) {
            velocityShapes[i].update(personalBest.getVelocityShapes()[i].getShape(),
                    ((Particle) globalBest).getVelocityShapes()[i].getShape());
        }
    }

    private AbstractVelocityShape[] getVelocityShapes() {
        return velocityShapes;
    }

    double getFitness() {
        return fitness;
    }

    BufferedImage getImage() {
        return image;
    }

    Particle cloneParticle(){
        Particle newParticle = new Particle(original,shapeType,latch);
        newParticle.velocityShapes = Arrays.copyOf(velocityShapes, velocityShapes.length);
        newParticle.fitness = fitness;
        newParticle.image = image;
        return  newParticle;
    }
}
