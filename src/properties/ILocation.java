package properties;

public interface ILocation<T> {
    T getX();
    T getY();
    void setX(T x);
    void setY(T y);
}
