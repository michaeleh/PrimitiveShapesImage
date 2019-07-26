package properties;

public class Color {
    private int red;
    private int green;
    private int blue;
    private int alpha;

    public java.awt.Color toObj(){
        return new java.awt.Color(red,green,blue,alpha);
    }
}
