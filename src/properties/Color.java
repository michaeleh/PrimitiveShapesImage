package properties;

import utils.NumberUtils;

public class Color {
    private int red;
    private int green;
    private int blue;
    private int alpha;

    public java.awt.Color toObj() {
        return new java.awt.Color(red, green, blue, alpha);
    }

    public void setRed(int red) {
        this.red = NumberUtils.clampColor(red);
    }

    public void setGreen(int green) {
        this.green = NumberUtils.clampColor(green);
    }

    public void setBlue(int blue) {
        this.blue = NumberUtils.clampColor(blue);
    }

    public void setAlpha(int alpha) {
        this.alpha = NumberUtils.clampColor(alpha);
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public int getAlpha() {
        return alpha;
    }

    @Override
    public String toString() {
        return "Color{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                ", alpha=" + alpha +
                '}';
    }

    public boolean isValid() {
        return red > 0 && red < 255 && green > 0 && green < 255 && blue > 0 && blue < 255 && alpha > 0 && alpha < 255;
    }
}
