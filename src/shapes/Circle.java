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
        if (isValid()) {
            graphics2D.setPaint(color.toObj());
            graphics2D.fillOval(location.getX(), location.getY(), scale.getValue(), scale.getValue());
        }
    }

    private boolean isValid() {
        return color.isValid() && location.isValid() && scale.isValid();
    }

    @Override
    public void init(int width, int height, int factor) {
        this.scale.setValue(NumberUtils.randInt(MIN_IMAGE_COORDINATE, Math.max(width, height) / factor));
        this.location.setX(NumberUtils.randInt(MIN_IMAGE_COORDINATE, width / factor));
        this.location.setY(NumberUtils.randInt(MIN_IMAGE_COORDINATE, height / factor));
        this.color.setRed(NumberUtils.randInt(CHANNEL_MIN, CHANNEL_MAX / factor));
        this.color.setBlue(NumberUtils.randInt(CHANNEL_MIN, CHANNEL_MAX / factor));
        this.color.setGreen(NumberUtils.randInt(CHANNEL_MIN, CHANNEL_MAX / factor));
        this.color.setAlpha(NumberUtils.randInt(CHANNEL_MIN, CHANNEL_MAX / factor));
    }


}
