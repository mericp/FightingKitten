package SteerableBehavior.Base;

import DB.StringRes.MySettings;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayDeque;
import java.util.Iterator;


public class SmellTrails {
    private ArrayDeque<SmellTrail> path;
    private float smellTrailDecayTime = 20f;

    public SmellTrails()
    {   path = new ArrayDeque<>(); }

    public Iterator<SmellTrail> iterator() {   return path.iterator(); }

    public void setSmellTrailDecayTime (float smellTrailDecayTime)
    {
        this.smellTrailDecayTime = smellTrailDecayTime;
    }

    public void addSmellTrail(Vector2 position)
    {
        SmellTrail smellTrail = path.peekFirst();

        if (sameTile(position, smellTrail))
        {
            smellTrail.duration = 0f;
        }
        else
        {
            path.addFirst(new SmellTrail(position));
        }
    }

    public void update (float delta)
    {
        SmellTrail smellTrail;
        Iterator<SmellTrail> iterator = path.descendingIterator();

        while (iterator.hasNext())
        {
            smellTrail = iterator.next();
            smellTrail.duration += delta;

            if (smellTrail.duration > smellTrailDecayTime) iterator.remove();
        }
    }

    private boolean sameTile (Vector2 position, SmellTrail smellTrail)
    {
        return sameTile(position.x, position.y, smellTrail);
    }

    private boolean sameTile(float x, float y, SmellTrail smellTrail) {
        return smellTrail != null && smellTrail.center.x / MySettings.TILE_HEIGHT == x / MySettings.TILE_HEIGHT && smellTrail.center.y / MySettings.TILE_HEIGHT == y / MySettings.TILE_HEIGHT;
    }
}
