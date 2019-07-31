package shapes;

import properties.Color;
import properties.Position;
import properties.Orientation;
import properties.Scale;
import utils.NumbersUtils;

import java.awt.*;

import static algorithms.pso.PSOConstants.*;

/**
 * Abstract shape class extended by implementation of shape
 */
public abstract class AbstractShape {
    private int maxWidth, maxHeight;
    final Position position = new Position();
    private final Orientation orientation = new Orientation();
    final Scale scaleX = new Scale();
    final Scale scaleY = new Scale();
    final Color color = new Color();

    Position getPosition() {
        return position;
    }

    Color getColor() {
        return color;
    }


    Scale getScaleX() {
        return scaleX;
    }

    /**
     * draw the shape on image's graphics
     *
     * @param graphics2D to draw the shape on
     */
    public abstract void draw(Graphics2D graphics2D);

    /**
     * init shape attributes with factor
     *
     * @param width  of the image
     * @param height of the image
     * @param factor to scale down
     */
    public void init(int width, int height, int factor) {
        this.maxHeight = height;
        this.maxWidth = width;
        this.scaleX.setValue(NumbersUtils.randInt(MIN_IMAGE_COORDINATE, width / factor));
        this.scaleY.setValue(NumbersUtils.randInt(MIN_IMAGE_COORDINATE, height / factor));
        this.position.setX(getLocationInitValue(width, factor));
        this.position.setY(getLocationInitValue(height, factor));
        this.color.setRed(NumbersUtils.randInt(CHANNEL_MIN, CHANNEL_MAX / factor));
        this.color.setBlue(NumbersUtils.randInt(CHANNEL_MIN, CHANNEL_MAX / factor));
        this.color.setGreen(NumbersUtils.randInt(CHANNEL_MIN, CHANNEL_MAX / factor));
        this.color.setAlpha(NumbersUtils.randInt(CHANNEL_MIN, CHANNEL_MAX / factor));
        this.orientation.setAngle(NumbersUtils.randInt(ANGLE_MIN, ANGLE_MAX) / factor);
    }

    /**
     * init shape attributes without factor
     *
     * @param width  of the image
     * @param height of the image
     */
    public void init(int width, int height) {
        init(width, height, 1);
    }

    int getMaxWidth() {
        return maxWidth;
    }

    int getMaxHeight() {
        return maxHeight;
    }

    Scale getScaleY() {
        return scaleY;
    }

    /**
     * get location init value specific for each shape.
     * @param maxBound max value
     * @param factor of scale down value
     * @return array of point represents location
     */
    protected abstract int[] getLocationInitValue(int maxBound, int factor);
}
