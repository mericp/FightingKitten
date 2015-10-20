package SteerableBehavior;

import SteerableBehavior.Base.*;
import SteerableBehavior.Base.Steerable;
import com.badlogic.gdx.physics.box2d.World;

public class SteerableAgent extends Steerable {
    public Pursuable pursuable = new Pursuable();

    public SteerableAgent(){ super(); }

    public SteerableAgent(World world){ super(world); }

    public void setPosition(float x, float y)
    {
        position.set(x, y);
        hitbox.get().setCenter(x, y);

        if (pursuable.get())
        {
            pursuable.getFootprint().addFootprint(x, y);
        }
    }
}
