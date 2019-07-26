package shapes;

import properties.CenterLocation;

import java.awt.*;

public class Circle extends AbstractShape<Integer> {

    Circle() {
        this.location = new CenterLocation();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setPaint(Color.BLACK);
        graphics2D.fillOval(location.getX(), location.getY(),scale.getValue(),scale.getValue());
    }



}
