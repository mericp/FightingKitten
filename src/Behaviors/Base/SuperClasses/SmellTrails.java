package Behaviors.Base.SuperClasses;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayDeque;
import java.util.Iterator;

public class SmellTrails {
    private ArrayDeque<SmellTrail> path;
    private float smellTrailDecayTime = 20f;

    public SmellTrails()
    {
        path = new ArrayDeque<>();
    }

    public Iterator<SmellTrail> getIterator() {
        return path.iterator();
    }

    public void add(Vector2 position)
    {
        SmellTrail smellTrail = path.peekFirst();

        if (sameTile(position.x, position.y, smellTrail))
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

            if (smellTrail.duration > smellTrailDecayTime)
            {
                iterator.remove();
            }
        }
    }

    private boolean sameTile(float x, float y, SmellTrail smellTrail) {
        if(smellTrail != null )
        {
            if(smellTrail.center.x == x && smellTrail.center.y == y)
            {
                return true;
            }
        }

        return false;
    }
}
