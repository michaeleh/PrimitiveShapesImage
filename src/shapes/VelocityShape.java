package shapes;

import properties.EPositionProperty;
import properties.EPrimitiveProperty;
import utils.NumbersUtils;

import static algorithms.pso.PSOConstants.*;

/**
 * Specific use of shape and its velocity per property
 */
public class VelocityShape {
    private AbstractShape shape;
    private AbstractShape velocity;

    public VelocityShape(EShapeType shapeType) {
        shape = shapeType.getShape();
        velocity = shapeType.getShape();

    }

    public AbstractShape getShape() {
        return shape;
    }

    public AbstractShape getVelocity() {
        return velocity;
    }

    /**
     * Updating shape properties considering it velocity
     * for each property we are computing
     *
     * @param personalBest {@link algorithms.IEvolutionarySample} personal best
     * @param globalBest   {@link algorithms.IEvolutionarySample} all time best
     */
    public void update(AbstractShape personalBest, AbstractShape globalBest) {
        ShapeMapper personalBestMapper = new ShapeMapper(personalBest);
        ShapeMapper globalBestMapper = new ShapeMapper(globalBest);
        ShapeMapper shapeMapper = new ShapeMapper(shape);
        ShapeMapper velocityMapper = new ShapeMapper(velocity);

        EPrimitiveProperty[] properties = EPrimitiveProperty.values();

        for (EPrimitiveProperty property : properties) {
            int newVelocity = calculateNewVelocity(shapeMapper.getValue(property), velocityMapper.getValue(property),
                    personalBestMapper.getValue(property), globalBestMapper.getValue(property));
            int newValue = shapeMapper.getValue(property) + newVelocity;
            shapeMapper.setValue(property,
                    NumbersUtils.clamp(newValue, shapeMapper.getMinBound(property), shapeMapper.getMaxBound(property)));
            velocityMapper.setValue(property, newVelocity);
        }

        EPositionProperty[] positionProperties = EPositionProperty.values();
        for (EPositionProperty property : positionProperties) {
            int positionLength = shapeMapper.getPositionLength();
            for (int i = 0; i < positionLength; i++) {

                int newVelocity = calculateNewVelocity(shapeMapper.getValue(property, i), velocityMapper.getValue(property, i),
                        personalBestMapper.getValue(property, i), globalBestMapper.getValue(property, i));

                int newValue = shapeMapper.getValue(property, i) + newVelocity;
                shapeMapper.setValue(property, i,
                        NumbersUtils.clamp(newValue, MIN_IMAGE_COORDINATE, shapeMapper.getMaxBound(property)));
                velocityMapper.setValue(property, i, newVelocity);

            }
        }

        shape = shapeMapper.toAbstractShape();
        velocity = velocityMapper.toAbstractShape();

    }


    /**
     * calculating new velocity using particle swarm optimization equation
     *
     * @param value        of new shape
     * @param velocity     of new shape
     * @param personalBest of current particle
     * @param globalBest   of all swarm
     * @return new velocity
     */
    private int calculateNewVelocity(int value, int velocity, int personalBest, int globalBest) {
        return (int) (W * velocity
                + C1 * NumbersUtils.randBetween0and1() * (personalBest - value)
                + C2 * NumbersUtils.randBetween0and1() * (globalBest - value));

    }

    public void setShape(AbstractShape shape) {
        this.shape = shape;
    }

    public void setVelocity(AbstractShape velocity) {
        this.velocity = velocity;
    }
}
