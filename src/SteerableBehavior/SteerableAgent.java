package SteerableBehavior;

import SteerableBehavior.Base.*;
import SteerableBehavior.Base.Steerable;

public class SteerableAgent extends Steerable {
    public Pursuable pursuable = new Pursuable();

    public void setPosition(float x, float y)
    {
        position.set(x, y);
        hitbox.get().setCenter(x, y);

        if (pursuable.get())
        {
            pursuable.getFootprint().add(x, y);
        }
    }
}
