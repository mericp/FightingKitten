package SteerableBehavior.Base;

import SteerableBehavior.Interfaces.IOrientable;
import com.badlogic.gdx.math.MathUtils;

public class Orientable implements IOrientable{
    protected float orientation = 0;

    @Override
    public float get() {
        return orientation;
    }

    @Override
    public void set(float radians) {
        orientation = radians % MathUtils.PI2;
    }
}
