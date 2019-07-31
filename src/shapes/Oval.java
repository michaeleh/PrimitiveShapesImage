package shapes;

import java.awt.*;

/**
 * Oval implementation of abstract shape
 */
public class Oval extends AbstractShape {

    private static final int SINGLE_CENTER_POSITION = 1;

    /**
     * Drawing oval
     *
     * @param graphics2D to draw the shape on
     */
    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setPaint(color.toObj());
        graphics2D.rotate(Math.toRadians(orientation.getAngle()));
        graphics2D.fillOval(position.getX()[0], position.getY()[0], scaleX.getValue(), scaleY.getValue());
    }

    /**
     * @return a single point location.
     */
    @Override
    protected int getNumOfPointsInPosition() {
        return SINGLE_CENTER_POSITION;
    }

}
