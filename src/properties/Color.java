package properties;

public class Color {
    private int red;
    private int green;
    private int blue;
    private int alpha;

    public java.awt.Color toObj(){
        return new java.awt.Color(red,green,blue,alpha);
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
}
