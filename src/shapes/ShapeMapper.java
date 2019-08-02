package shapes;

import algorithms.pso.PSOConstants;
import properties.EPositionProperty;
import properties.EPrimitiveProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Shape mapper of {@link AbstractShape}
 */
class ShapeMapper {
    private final Map<EPrimitiveProperty, Integer> propertyMap;
    private final Map<EPositionProperty, int[]> positionPropertyMap;
    private final AbstractShape shape;

    /**
     * Build map by property  type to value
     * And a map for position type
     * @param shape to build from
     */
    ShapeMapper(AbstractShape shape) {
        this.shape = shape;
        this.propertyMap = new HashMap<>();
        propertyMap.put(EPrimitiveProperty.RED, shape.getColor().getRed());
        propertyMap.put(EPrimitiveProperty.GREEN, shape.getColor().getGreen());
        propertyMap.put(EPrimitiveProperty.BLUE, shape.getColor().getBlue());
        propertyMap.put(EPrimitiveProperty.ALPHA, shape.getColor().getAlpha());
        propertyMap.put(EPrimitiveProperty.ANGLE, shape.getOrientation().getAngle());
        propertyMap.put(EPrimitiveProperty.SCALE_X, shape.getScaleX().getValue());
        propertyMap.put(EPrimitiveProperty.SCALE_Y, shape.getScaleY().getValue());

        positionPropertyMap = new HashMap<>();
        positionPropertyMap.put(EPositionProperty.X, shape.getPosition().getX());
        positionPropertyMap.put(EPositionProperty.Y, shape.getPosition().getY());
    }

    int getValue(EPrimitiveProperty property) {
        return propertyMap.get(property);
    }

    void setValue(EPrimitiveProperty property, int value) {
        propertyMap.put(property, value);
    }

    int getValue(EPositionProperty property, int index) {
        return positionPropertyMap.get(property)[index];
    }

    void setValue(EPositionProperty property, int index, int value) {
        positionPropertyMap.get(property)[index] = value;
    }

    /**
     * @return the length of position length for the first axis (all are the same)
     */
    int getPositionLength() {
        return positionPropertyMap.get(EPositionProperty.values()[0]).length;
    }

    /**
     * Getting max valid value for a property
     * @param primitiveProperty a property to check
     * @return max value
     */
    int getMaxBound(EPrimitiveProperty primitiveProperty) {
        switch (primitiveProperty) {
            case RED:
            case GREEN:
            case BLUE:
            case ALPHA:
                return PSOConstants.CHANNEL_MAX;
            case ANGLE:
                return PSOConstants.ANGLE_MAX;
            case SCALE_X:
                return shape.getMaxWidth();
            case SCALE_Y:
                return shape.getMaxHeight();
        }
        return Integer.MAX_VALUE;
    }

    /**
     * getting max bound for position
     * @param property of position type
     * @return max valid value for position
     */
    int getMaxBound(EPositionProperty property) {
        switch (property) {
            case X:
                return shape.getMaxWidth();
            case Y:
                return shape.getMaxHeight();
        }
        return Math.min(shape.getMaxWidth(), shape.getMaxHeight());
    }

    /**
     * Getting min valid value for a property
     * @param primitiveProperty a property to check
     * @return min value
     */
    int getMinBound(EPrimitiveProperty primitiveProperty) {
        switch (primitiveProperty) {
            case RED:
            case GREEN:
            case BLUE:
            case ALPHA:
                return PSOConstants.CHANNEL_MIN;
            case ANGLE:
                return PSOConstants.ANGLE_MIN;
            case SCALE_X:
            case SCALE_Y:
                return PSOConstants.MIN_IMAGE_COORDINATE;
        }
        return Integer.MAX_VALUE;
    }

    /**
     * updating shape from map values
     * @return updated shape
     */
    AbstractShape toAbstractShape() {
        shape.getColor().setRed(propertyMap.get(EPrimitiveProperty.RED));
        shape.getColor().setGreen(propertyMap.get(EPrimitiveProperty.GREEN));
        shape.getColor().setBlue(propertyMap.get(EPrimitiveProperty.BLUE));
        shape.getColor().setAlpha(propertyMap.get(EPrimitiveProperty.ALPHA));
        shape.getOrientation().setAngle(propertyMap.get(EPrimitiveProperty.ANGLE));
        shape.getScaleX().setValue(propertyMap.get(EPrimitiveProperty.SCALE_X));
        shape.getScaleY().setValue(propertyMap.get(EPrimitiveProperty.SCALE_Y));
        shape.getPosition().setX(positionPropertyMap.get(EPositionProperty.X));
        shape.getPosition().setY(positionPropertyMap.get(EPositionProperty.Y));
        return shape;
    }

}
