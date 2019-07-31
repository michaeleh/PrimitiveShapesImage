package properties;

/**
 *  location for shape
 */
public class Position {
    // array of integer to represent location of x and y (polygon/ center)
    private int[] x, y;

    public int[] getX() {
        return x;
    }

    public void setX(int[] x) {
        this.x = x;
    }

    public int[] getY() {
        return y;
    }

    public void setY(int[] y) {
        this.y = y;
    }
}
