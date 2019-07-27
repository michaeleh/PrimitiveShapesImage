package properties;

import utils.NumberUtils;

public class Scale {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = NumberUtils.clampColor(value);
    }

    @Override
    public String toString() {
        return "Scale{" +
                "value=" + value +
                '}';
    }

    public boolean isValid() {
        return value < 400 && value > 0;
    }
}
