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
     * for each property we are computing its new velocity  from PSO equation
     * and adding this velocity to current position thus gives us new position.
     *
     * @param personalBest {@link algorithms.IEvolutionarySample} personal best
     * @param globalBest   {@link algorithms.IEvolutionarySample} all time best
     */
    public void update(AbstractShape personalBest, AbstractShape globalBest) {
        // mapping each shape
        ValidPropertiesBounds bounds = new ValidPropertiesBounds(shape.getMaxWidth(), shape.getMaxHeight());
        ShapeMapper personalBestMapper = new ShapeMapper(personalBest);
        ShapeMapper globalBestMapper = new ShapeMapper(globalBest);
        ShapeMapper shapeMapper = new ShapeMapper(shape);
        ShapeMapper velocityMapper = new ShapeMapper(velocity);

        // for each primitive properties
        EPrimitiveProperty[] properties = EPrimitiveProperty.values();

        for (EPrimitiveProperty property : properties) {
            // get new velocity
            int newVelocity = calculateNewVelocity(shapeMapper.getValue(property), velocityMapper.getValue(property),
                    personalBestMapper.getValue(property), globalBestMapper.getValue(property));
            // get new value
            int newValue = shapeMapper.getValue(property) + newVelocity;
            // set new shape value with clamp to its valid bounds
            int minBound = bounds.getMinBound(property);
            int maxBound = bounds.getMaxBound(property);
            shapeMapper.setValue(property, NumbersUtils.clamp(newValue, minBound, maxBound));
            // set new velocity
            velocityMapper.setValue(property, newVelocity);
        }

        // for each position property
        EPositionProperty[] positionProperties = EPositionProperty.values();
        for (EPositionProperty property : positionProperties) {
            int positionLength = shapeMapper.getPositionLength();
            // for  each index of position vector
            for (int i = 0; i < positionLength; i++) {
                // calculate its new velocity
                int newVelocity = calculateNewVelocity(shapeMapper.getValue(property, i), velocityMapper.getValue(property, i),
                        personalBestMapper.getValue(property, i), globalBestMapper.getValue(property, i));
                // calculate its new value
                int newValue = shapeMapper.getValue(property, i) + newVelocity;
                // set its new value considering max bound clamp
                int maxBound = bounds.getMaxBound(property);
                shapeMapper.setValue(property, i, NumbersUtils.clamp(newValue, MIN_IMAGE_COORDINATE, maxBound));
                // setting velocity new value
                velocityMapper.setValue(property, i, newVelocity);
            }
        }
        // updating shape and velocity from map
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
