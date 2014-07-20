package PhysicalObjects;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public interface IPhysicalObject
{
    public float getWidth();
    public float getHeight();
    public World getWorld();
    public void setBody(Body body);
    public Body getBody();
}