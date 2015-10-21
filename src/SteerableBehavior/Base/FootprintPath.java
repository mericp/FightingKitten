package SteerableBehavior.Base;

import DB.StringRes.MySettings;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayDeque;
import java.util.Iterator;


public class FootprintPath {
    private ArrayDeque<Footprint> path;
    private float footPrintDecayTime = 20f;

    public FootprintPath()
    {   path = new ArrayDeque<>(); }

    public Iterator<Footprint> iterator() {   return path.iterator(); }

    public void setFootPrintDecayTime (float footPrintDecayTime) {   this.footPrintDecayTime = footPrintDecayTime; }

    public void addFootprint(Vector2 position)
    {
        Footprint footprint = path.peekFirst();

        if (sameTile(position, footprint))
        {
            footprint.duration = 0f;
        }
        else
        {
            path.addFirst(new Footprint(position));
        }
    }

    public void update (float delta)
    {
        Footprint footprint;
        Iterator<Footprint> iterator = path.descendingIterator();

        while (iterator.hasNext())
        {
            footprint = iterator.next();
            footprint.duration += delta;

            if (footprint.duration > footPrintDecayTime) iterator.remove();
        }
    }

    private boolean sameTile (Vector2 position, Footprint footprint)
    {
        return sameTile(position.x, position.y, footprint);
    }

    private boolean sameTile(float x, float y, Footprint footprint) {
        return footprint != null && footprint.center.x / MySettings.TILE_HEIGHT == x / MySettings.TILE_HEIGHT && footprint.center.y / MySettings.TILE_HEIGHT == y / MySettings.TILE_HEIGHT;
    }
}
