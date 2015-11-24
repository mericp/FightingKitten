package SteerableBehavior.Base;

import DB.StringRes.MySettings;
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
        float x = position.x - position.x % MySettings.TILE_HEIGHT + MySettings.TILE_HEIGHT / 2;
        float y = position.y - position.y % MySettings.TILE_HEIGHT + MySettings.TILE_HEIGHT / 2;

        center = new Vector2(x, y);
    }
}
