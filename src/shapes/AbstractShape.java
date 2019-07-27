package shapes;

import properties.*;
import properties.Color;

import java.awt.*;

public abstract class AbstractShape<T> {

    protected ILocation<T> location;
    protected Orientation orientation;
    protected Scale scale = new Scale();
    protected Color color = new Color();

    public ILocation<T> getLocation(){
        return location;
    }

    public Color getColor() {
        return color;
    }


    public Scale getScale() {
        return scale;
    }

    public abstract void draw(Graphics2D graphics2D);

    public abstract void init(int width, int height);
}
