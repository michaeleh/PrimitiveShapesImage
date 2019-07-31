package shapes;

import properties.Color;
import properties.Position;
import utils.NumbersUtils;

import static algorithms.pso.PSOConstants.*;

/**
 * Specific use of shape and its velocity per property
 */
public class VelocityShape {
    private AbstractShape shape;
    private AbstractShape velocity;

    public VelocityShape(EShapeType shapeType) {
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
        Position shapePosition = shape.getPosition();
        Position velocityPosition = velocity.getPosition();
        Position personalBestPosition = personalBest.getPosition();
        Position globalBestPosition = globalBest.getPosition();

        int newScaleXVelocity = calculateNewVelocity(shape.getScaleX().getValue(), velocity.getScaleX().getValue(),
                personalBest.getScaleX().getValue(), globalBest.getScaleX().getValue());
        int newScaleX = newScaleXVelocity + shape.getScaleX().getValue();

        int newScaleYVelocity = calculateNewVelocity(shape.getScaleY().getValue(), velocity.getScaleY().getValue(),
                personalBest.getScaleY().getValue(), globalBest.getScaleY().getValue());
        int newScaleY = newScaleYVelocity + shape.getScaleY().getValue();

        int newBlueVelocity = calculateNewVelocity(shapeColor.getBlue(), velocityColor.getBlue(), personalBestColor.getBlue(), globalBestColor.getBlue());
        int newBlueValue = newBlueVelocity + shapeColor.getBlue();

        int newGreenVelocity = calculateNewVelocity(shapeColor.getGreen(), velocityColor.getGreen(), personalBestColor.getGreen(), globalBestColor.getGreen());
        int newGreenValue = newGreenVelocity + shapeColor.getGreen();

        int newRedVelocity = calculateNewVelocity(shapeColor.getRed(), velocityColor.getRed(), personalBestColor.getRed(), globalBestColor.getRed());
        int newRedValue = newRedVelocity + shapeColor.getRed();

        int newAlphaVelocity = calculateNewVelocity(shapeColor.getAlpha(), velocityColor.getAlpha(), personalBestColor.getAlpha(), globalBestColor.getAlpha());
        int newAlphaValue = newAlphaVelocity + shapeColor.getAlpha();


        int length = this.shape.getPosition().getX().length;
        int[] newShapeXVelocity = new int[length];
        int[] newShapeYVelocity = new int[length];
        int[] newShapeX = new int[length];
        int[] newShapeY = new int[length];
        for (int i = 0; i < length; i++) {
            newShapeXVelocity[i] = calculateNewVelocity(shapePosition.getX()[i], velocityPosition.getX()[i],
                    personalBestPosition.getX()[i], globalBestPosition.getX()[i]);
            newShapeX[i] = shapePosition.getX()[i] + newShapeXVelocity[i];

            newShapeYVelocity[i] = calculateNewVelocity(shapePosition.getY()[i], velocityPosition.getY()[i],
                    personalBestPosition.getY()[i], globalBestPosition.getY()[i]);
            newShapeY[i] = shapePosition.getY()[i] + newShapeYVelocity[i];
        }

        velocity.getScaleX().setValue(newScaleXVelocity);
        velocity.getScaleY().setValue(newScaleYVelocity);
        velocity.getColor().setRed(newRedVelocity);
        velocity.getColor().setBlue(newBlueVelocity);
        velocity.getColor().setGreen(newGreenVelocity);
        velocity.getColor().setAlpha(newAlphaVelocity);
        velocity.getPosition().setX(newShapeXVelocity);
        velocity.getPosition().setY(newShapeYVelocity);
        shape.getColor().setRed(NumbersUtils.clampColor(newRedValue));
        shape.getColor().setBlue(NumbersUtils.clampColor(newBlueValue));
        shape.getColor().setGreen(NumbersUtils.clampColor(newGreenValue));
        shape.getColor().setAlpha(NumbersUtils.clampColor(newAlphaValue));
        shape.getScaleX().setValue(NumbersUtils.clamp(newScaleX, 0, shape.getMaxWidth()));
        shape.getScaleY().setValue(NumbersUtils.clamp(newScaleY, 0, shape.getMaxHeight()));
        shape.getPosition().setX(NumbersUtils.clamp(newShapeX, 0, shape.getMaxWidth()));
        shape.getPosition().setY(NumbersUtils.clamp(newShapeY, 0, shape.getMaxHeight()));
    }

    private int calculateNewVelocity(int value, int velocity, int personalBest, int globalBest) {
        return (int) (W * velocity
                + C1 * NumbersUtils.randBetween0and1() * (personalBest - value)
                + C2 * NumbersUtils.randBetween0and1() * (globalBest - value));

    }

    public void setShape(AbstractShape shape) {
        this.shape = shape;
    }

    public void setVelocity(AbstractShape velocity) {
        this.velocity = velocity;
    }
}
