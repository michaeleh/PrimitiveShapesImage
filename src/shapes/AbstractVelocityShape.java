package shapes;

public class AbstractVelocityShape {
    private AbstractShape shape;
    private AbstractShape velocity;

    public AbstractVelocityShape(EShapeType shapeType) {
        shape = shapeType.getShape();
        velocity = shapeType.getShape();
    }

    public AbstractShape getShape() {
        return shape;
    }

    public AbstractShape getVelocity() {
        return velocity;
    }
}
