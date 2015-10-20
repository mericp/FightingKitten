package SteerableBehavior.Base;

import DB.MySettings;

public class Footprint {
    public int x;
    public int y;
    public float duration;

    public Footprint(int x, int y)
    {
        setTileCenter(x, y);
        duration = 0;
    }

    public Footprint(float x, float y)
    {
        setTileCenter((int) x, (int) y);
        duration = 0;
    }

    public Footprint(Spatial espacial)
    {
        setTileCenter((int) espacial.getX(), (int) espacial.getY());
        duration = 0;
    }

    private void setTileCenter(int x, int y)
    {
        this.x = x - x % MySettings.TILE_HEIGHT + MySettings.TILE_HEIGHT / 2;
        this.y = y - y % MySettings.TILE_HEIGHT + MySettings.TILE_HEIGHT / 2;
    }
}
