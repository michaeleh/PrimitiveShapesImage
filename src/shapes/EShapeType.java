package shapes;

public enum EShapeType {
    CIRCLE {
        @Override
        public AbstractShape getShape() {
            return new Circle();
        }
    };

    public abstract AbstractShape getShape();
}
