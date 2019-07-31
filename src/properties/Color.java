package properties;

/**
 * Color representation of RGB, alpha
 */
public class Color {
    private int red;
    private int green;
    private int blue;
    private int alpha;

    /**
     * builds a color object to set the drawn shape to
     * @return color obj from rgba
     */
    public java.awt.Color toObj() {
        return new java.awt.Color(red, green, blue, alpha);
    }

    public void setRed(int red) {
        this.red = red;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
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

}
