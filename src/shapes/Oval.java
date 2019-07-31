package shapes;

import utils.NumbersUtils;

import java.awt.*;

import static algorithms.PSO.PSOConstants.*;

/**
 * Oval implementation of abstract shape
 */
public class Oval extends AbstractShape{
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
     * get location init value of center location.
     * @param maxBound max value
     * @param factor of scale down value
     * @return array of point represents location
     */
    @Override
    protected int[] getLocationInitValue(int maxBound,int factor) {
        return new int[]{NumbersUtils.randInt(MIN_IMAGE_COORDINATE, maxBound) / factor};
    }

    public int getLocationX() {
        return position.getX()[0];
    }

    public int getLocationY() {
        return position.getY()[0];
    }


}
