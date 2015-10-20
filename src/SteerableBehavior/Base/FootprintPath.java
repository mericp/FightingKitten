package SteerableBehavior.Base;

import DB.MySettings;

import java.util.ArrayDeque;
import java.util.Iterator;


public class FootprintPath {
    private ArrayDeque<Footprint> path;
    private float footPrintDecayTime = 20f;

    public FootprintPath()
    {   path = new ArrayDeque<>(); }

    public Iterator<Footprint> iterator() {   return path.iterator(); }

    public void setFootPrintDecayTime (float footPrintDecayTime) {   this.footPrintDecayTime = footPrintDecayTime; }

    public void addFootprint(Spatial spatial)
    {
        addFootprint(spatial.getX(), spatial.getY());
    }

    public void addFootprint(float x, float y)
    {
        Footprint footprint = path.peekFirst();

        if (sameTile(x, y, footprint))
        {
            footprint.duration = 0f;
        }
        else
        {
            path.addFirst(new Footprint(x, y));
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

    private boolean sameTile (Spatial spatial, Footprint footprint)
    {
        return sameTile(spatial.getX(), spatial.getY(), footprint);
    }

    private boolean sameTile(float x, float y, Footprint footprint)
    {
        boolean isSameTile;

        if (footprint == null)
        {
            isSameTile = false;
        }
        else if ( footprint.x /  MySettings.TILE_HEIGHT  == x /  MySettings.TILE_HEIGHT  &&
                footprint.y /  MySettings.TILE_HEIGHT  == y /  MySettings.TILE_HEIGHT  )
        {
            isSameTile = true;
        }
        else
        {
            isSameTile = false;
        }

        return isSameTile;
    }
}
