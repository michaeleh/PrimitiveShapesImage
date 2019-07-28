package shapes;

import properties.Color;
import properties.ILocation;
import utils.NumberUtils;

import static algorithms.PSO.PSOConstants.*;

public class AbstractVelocityShape {
    private AbstractShape shape;
    private AbstractShape velocity;

    public AbstractVelocityShape(EShapeType shapeType) {
        shape = shapeType.getShape();
        velocity = shapeType.getShape();
    }

    public AbstractShape getShape() {
        return shape;
    }

    public AbstractShape getVelocity() {
        return velocity;
    }

    public void update(AbstractShape personalBest, AbstractShape globalBest) {
        Color personalBestColor = personalBest.getColor();
        Color globalBestColor = globalBest.getColor();
        Color velocityColor = velocity.getColor();
        Color shapeColor = shape.getColor();
        ILocation<Integer> shapeLocation = shape.getLocation();
        ILocation<Integer> velocityLocation = velocity.getLocation();
        ILocation<Integer> personalBestLocation = personalBest.getLocation();
        ILocation<Integer> globalBestLocation = globalBest.getLocation();

        int newScaleVelocity = calculateNewVelocity(shape.getScale().getValue(), velocity.getScale().getValue(),
                personalBest.getScale().getValue(), globalBest.getScale().getValue());
        int newScale = newScaleVelocity + shape.getScale().getValue();

        int newBlueVelocity = calculateNewVelocity(shapeColor.getBlue(), velocityColor.getBlue(), personalBestColor.getBlue(), globalBestColor.getBlue());
        int newBlueValue = newBlueVelocity + shapeColor.getBlue();

        int newGreenVelocity = calculateNewVelocity(shapeColor.getGreen(), velocityColor.getGreen(), personalBestColor.getGreen(), globalBestColor.getGreen());
        int newGreenValue = newGreenVelocity + shapeColor.getGreen();

        int newRedVelocity = calculateNewVelocity(shapeColor.getRed(), velocityColor.getRed(), personalBestColor.getRed(), globalBestColor.getRed());
        int newRedValue = newRedVelocity + shapeColor.getRed();

        int newAlphaVelocity = calculateNewVelocity(shapeColor.getAlpha(), velocityColor.getAlpha(), personalBestColor.getAlpha(), globalBestColor.getAlpha());
        int newAlphaValue = newAlphaVelocity + shapeColor.getAlpha();

        int newShapeXVelocity = calculateNewVelocity(shapeLocation.getX(), velocityLocation.getX(),
                personalBestLocation.getX(), globalBestLocation.getX());
        int newShapeX = newShapeXVelocity + shapeLocation.getX();

        int newShapeYVelocity = calculateNewVelocity(shapeLocation.getY(), velocityLocation.getY(),
                personalBestLocation.getY(), globalBestLocation.getY());
        int newShapeY = newShapeYVelocity + shapeLocation.getY();


        velocity.getScale().setValue(newScaleVelocity);
        velocity.getColor().setRed(newRedVelocity);
        velocity.getColor().setBlue(newBlueVelocity);
        velocity.getColor().setGreen(newGreenVelocity);
        velocity.getColor().setAlpha(newAlphaVelocity);
        velocity.getLocation().setX(newShapeXVelocity);
        velocity.getLocation().setY(newShapeYVelocity);
        shape.getColor().setRed(NumberUtils.clampColor(newRedValue));
        shape.getColor().setBlue(NumberUtils.clampColor(newBlueValue));
        shape.getColor().setGreen(NumberUtils.clampColor(newGreenValue));
        shape.getColor().setAlpha(NumberUtils.clampColor(newAlphaValue));
        shape.getScale().setValue(NumberUtils.clampCanvas(newScale));
        shape.getLocation().setX(NumberUtils.clampCanvas(newShapeX));
        shape.getLocation().setY(NumberUtils.clampCanvas(newShapeY));
    }

    private int calculateNewVelocity(int value, int velocity, int personalBest, int globalBest) {
        return (int) (W * velocity
                + C1 * NumberUtils.randBetween0and1() * (personalBest - value)
                + C2 * NumberUtils.randBetween0and1() * (globalBest - value));

    }
}
