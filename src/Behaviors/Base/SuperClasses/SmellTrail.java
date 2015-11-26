package Behaviors.Base.SuperClasses;

import com.badlogic.gdx.math.Vector2;

public class SmellTrail {
    public Vector2 center;
    public float duration;

    public SmellTrail(Vector2 position)
    {
        setTileCenter(position);
        duration = 0;
    }

    private void setTileCenter(Vector2 position)
    {
        center = position;
    }
}
