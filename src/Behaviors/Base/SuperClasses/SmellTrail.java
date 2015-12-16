package Behaviors.Base.SuperClasses;

import com.badlogic.gdx.math.Vector2;

public class SmellTrail {
    public final Vector2 center;
    public float duration;

    public SmellTrail(Vector2 position)
    {
        center = position;
        duration = 0;
    }
}
