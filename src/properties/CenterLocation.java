package properties;

public class CenterLocation implements ILocation<Integer> {
    private int x, y;

    @Override
    public Integer getX() {
        return x;
    }

    @Override
    public Integer getY() {
        return y;
    }

    @Override
    public void setX(Integer x) {
        this.x =x;
    }

    @Override
    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public boolean isValid() {
        return x > 0 && x < 400 && y > 0 && y < 400;
    }


    @Override
    public String toString() {
        return "CenterLocation{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
