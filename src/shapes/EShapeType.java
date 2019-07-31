package shapes;

/**
 * Enum of shapes we support
 */
public enum EShapeType {
    OVAL {
        @Override
        public AbstractShape getShape() {
            return new Oval();
        }
    };

    public abstract AbstractShape getShape();
}
