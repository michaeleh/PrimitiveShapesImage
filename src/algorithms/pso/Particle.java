package algorithms.pso;

import algorithms.IEvolutionarySample;
import shapes.EShapeType;
import shapes.VelocityShape;
import utils.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static algorithms.pso.PSOConstants.VELOCITY_FACTOR;

/**
 * Particle class representing a particle in a swarm
 */
public class Particle implements IEvolutionarySample<BufferedImage> {
    private final CyclicBarrier cyclicBarrier;
    private final EShapeType shapeType;
    private VelocityShape velocityShape;
    private double fitness;
    private BufferedImage image;
    private final BufferedImage original;
    private Particle personalBest;
    private final BufferedImage previousImage;

    Particle(BufferedImage original, BufferedImage previousImage, EShapeType shapeType, CyclicBarrier cyclicBarrier) {
        velocityShape = new VelocityShape(shapeType);
        this.previousImage = previousImage;
        this.shapeType = shapeType;
        this.original = original;
        this.cyclicBarrier = cyclicBarrier;
    }

    /**
     * Initiate the velocity shape both shape parameters and velocity parameters.
     * For the velocity shape we will factor everything by VELOCITY_FACTOR
     */
    @Override
    public void init() {
        int width = original.getWidth();
        int height = original.getHeight();
        velocityShape.getShape().init(width, height);
        velocityShape.getVelocity().init(width, height, VELOCITY_FACTOR);
    }

    /**
     * calculate fitness of the particle.
     * Finding how close are we to the original image.
     */
    @Override
    public void calculateFitness() {
        //multiple particles cant use the same image to write on.
        image = ImageUtils.deepCopy(previousImage);
        Graphics2D graphics = image.createGraphics();
        velocityShape.getShape().draw(graphics);
        graphics.dispose();
        fitness = ImageUtils.calcImageDiff(image, original);

        // if this iteration is the particles personal best.
        if (personalBest == null || personalBest.getFitness() > fitness) {
            personalBest = (Particle) cloneSample();
        }
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

    }

    /**
     * Updating the shape using PSO equation to achieve minimum error.
     * Updating is done using the particle's data, personal best and global best.
     *
     * @param globalBest best particle for this shape count
     */
    @Override
    public void evolve(IEvolutionarySample globalBest) {
        velocityShape.update(personalBest.getVelocityShape().getShape(),
                ((Particle) globalBest).getVelocityShape().getShape());
    }

    @Override
    public BufferedImage getProduct() {
        return image;
    }


    private VelocityShape getVelocityShape() {
        return velocityShape;
    }

    @Override
    public double getFitness() {
        return fitness;
    }


    /**
     * Cloning current particle to avoid global and personal best overridden
     *
     * @return new particle with current particle's info
     */
    @Override
    public IEvolutionarySample<BufferedImage> cloneSample() {
        Particle newParticle = new Particle(original, previousImage, shapeType, null);
        newParticle.velocityShape = new VelocityShape(shapeType);
        newParticle.velocityShape.setShape(velocityShape.getShape());
        newParticle.velocityShape.setVelocity(velocityShape.getVelocity());
        newParticle.fitness = fitness;
        newParticle.image = ImageUtils.deepCopy(image);
        return newParticle;
    }
}
