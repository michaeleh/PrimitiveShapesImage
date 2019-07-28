package algorithms.PSO;

import algorithms.IEvolutionarySample;
import shapes.EShapeType;
import shapes.VelocityShape;
import utils.ImageUtils;
import utils.ObserverLatch;

import java.awt.*;
import java.awt.image.BufferedImage;

import static algorithms.PSO.PSOConstants.VELOCITY_FACTOR;

public class Particle implements IEvolutionarySample {
    private final ObserverLatch latch;
    private final EShapeType shapeType;
    private VelocityShape velocityShape;
    private double fitness;
    private BufferedImage image, original;
    private Particle personalBest;
    private BufferedImage previousImage;

    Particle(BufferedImage original, BufferedImage previousImage, EShapeType shapeType, ObserverLatch latch) {
        velocityShape = new VelocityShape(shapeType);
        this.previousImage = previousImage;
        this.shapeType = shapeType;
        this.original = original;
        this.latch = latch;
    }

    @Override
    public void init() {
        velocityShape.getShape().init(original.getWidth(), original.getHeight());
        velocityShape.getVelocity().init(original.getWidth(), original.getHeight(), VELOCITY_FACTOR);
    }

    @Override
    public void calculateFitness() {
        image = ImageUtils.deepCopy(previousImage);
        Graphics2D graphics = image.createGraphics();
        velocityShape.getShape().draw(graphics);
        graphics.dispose();
        fitness = ImageUtils.calcImageDiff(image);
        latch.countdown();
        if (personalBest == null || personalBest.getFitness() > fitness) {
            personalBest = cloneParticle();
        }

    }

    @Override
    public void evolve(IEvolutionarySample globalBest) {
        velocityShape.update(personalBest.getVelocityShape().getShape(),
                ((Particle) globalBest).getVelocityShape().getShape());

    }

    private VelocityShape getVelocityShape() {
        return velocityShape;
    }

    double getFitness() {
        return fitness;
    }

    BufferedImage getImage() {
        return image;
    }

    Particle cloneParticle() {
        Particle newParticle = new Particle(original, previousImage, shapeType, latch);
        newParticle.velocityShape = new VelocityShape(shapeType);
        newParticle.velocityShape.setShape(velocityShape.getShape());
        newParticle.velocityShape.setVelocity(velocityShape.getVelocity());
        newParticle.fitness = fitness;
        newParticle.image = image;
        return newParticle;
    }
}
