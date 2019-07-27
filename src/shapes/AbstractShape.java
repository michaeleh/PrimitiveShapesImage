package shapes;

import properties.Color;
import properties.ILocation;
import properties.Orientation;
import properties.Scale;

import java.awt.*;

public abstract class AbstractShape<T> {

    protected ILocation<T> location;
    protected Orientation orientation;
    protected Scale scale = new Scale();
    protected Color color = new Color();

    public ILocation<T> getLocation() {
        return location;
    }

    public Color getColor() {
        return color;
    }


    public Scale getScale() {
        return scale;
    }

    public abstract void draw(Graphics2D graphics2D);

    public abstract void init(int width, int height, int factor);

    public void init(int width, int height) {
        init(width, height, 1);
    }

}
