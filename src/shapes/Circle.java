package shapes;

import properties.CenterLocation;
import utils.NumberUtils;

import java.awt.*;

import static algorithms.PSO.PSOConstants.*;

public class Circle extends AbstractShape<Integer> {

    Circle() {
        this.location = new CenterLocation();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setPaint(color.toObj());
        graphics2D.fillOval(location.getX(), location.getY(), scale.getValue(), scale.getValue());
    }

    @Override
    public void init(int width, int height) {
        this.scale.setValue(NumberUtils.randInt(MIN_IMAGE_COORDINATE, Math.max(width, height)));
        this.location.setX(NumberUtils.randInt(MIN_IMAGE_COORDINATE, width));
        this.location.setY(NumberUtils.randInt(MIN_IMAGE_COORDINATE, height));
        this.color.setRed(NumberUtils.randInt(CHANNEL_MIN, CHANNEL_MAX));
        this.color.setBlue(NumberUtils.randInt(CHANNEL_MIN, CHANNEL_MAX));
        this.color.setGreen(NumberUtils.randInt(CHANNEL_MIN, CHANNEL_MAX));
        this.color.setAlpha(NumberUtils.randInt(CHANNEL_MIN, CHANNEL_MAX));
    }


}
