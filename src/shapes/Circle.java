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
            graphics2D.fillOval(location.getX(), location.getY(), scaleX.getValue(), scaleY.getValue());

    }

    @Override
    public void init(int width, int height, int factor) {
        this.maxHeight = height;
        this.maxWidth = width;
        this.scaleX.setValue(NumberUtils.randInt(MIN_IMAGE_COORDINATE, Math.min(width, height) / (10)));
        this.scaleY.setValue(NumberUtils.randInt(MIN_IMAGE_COORDINATE, Math.min(width, height) / (10)));
        this.location.setX(NumberUtils.randInt(MIN_IMAGE_COORDINATE, width / factor));
        this.location.setY(NumberUtils.randInt(MIN_IMAGE_COORDINATE, height / factor));
        this.color.setRed(NumberUtils.randInt(CHANNEL_MIN, CHANNEL_MAX / factor));
        this.color.setBlue(NumberUtils.randInt(CHANNEL_MIN, CHANNEL_MAX / factor));
        this.color.setGreen(NumberUtils.randInt(CHANNEL_MIN, CHANNEL_MAX / factor));
        this.color.setAlpha(NumberUtils.randInt(CHANNEL_MIN, CHANNEL_MAX / factor));
    }


}
