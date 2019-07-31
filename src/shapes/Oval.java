package shapes;

import utils.NumbersUtils;

import java.awt.*;

import static algorithms.pso.PSOConstants.MIN_IMAGE_COORDINATE;

/**
 * Oval implementation of abstract shape
 */
public class Oval extends AbstractShape {
    /**
     * Drawing oval
     *
     * @param graphics2D to draw the shape on
     */
    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setPaint(color.toObj());
        graphics2D.fillOval(getLocationX(), getLocationY(), scaleX.getValue(), scaleY.getValue());
    }

    /**
     * @return a single point location.
     */
    @Override
    protected int getNumOfPointsInPosition() {
        return 1;
    }

    private int getLocationX() {
        return position.getX()[0];
    }

    private int getLocationY() {
        return position.getY()[0];
    }


}
