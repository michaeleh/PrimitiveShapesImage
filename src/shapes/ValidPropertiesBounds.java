package shapes;

import algorithms.pso.PSOConstants;
import properties.EPositionProperty;
import properties.EPrimitiveProperty;

/**
 * class to store valid boundaries for properties
 */
class ValidPropertiesBounds {
    private int width, height;

    ValidPropertiesBounds(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Getting max valid value for a property
     *
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
                return width * 2;
            case SCALE_Y:
                return height * 2;
        }
        return Integer.MAX_VALUE;
    }

    /**
     * getting max bound for position
     *
     * @param property of position type
     * @return max valid value for position
     */
    int getMaxBound(EPositionProperty property) {
        switch (property) {
            case X:
                return width;
            case Y:
                return height;
        }
        return Math.min(width, height);
    }

    /**
     * Getting min valid value for a property
     *
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
        return Integer.MIN_VALUE;
    }
}
