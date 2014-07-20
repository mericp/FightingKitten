package PhysicalObjects;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import static DB.MySettings.METTERS_PIXEL;
import static DB.MySettings.PIXEL_METTERS;

public class StaticObject implements IPhysicalObject
{
    private World world;
    private float width;
    private float height;
    private Body body;

    public StaticObject(World world, int width, int height)
    {
        this.world = world;
        this.width = width * PIXEL_METTERS;
        this.height = height * PIXEL_METTERS;
    }

    @Override
    public int getBottomLeftCornerX() {
        return (int)((body.getPosition().x - width / 2) * METTERS_PIXEL);
    }

    @Override
    public int getBottomLeftCornerY() {
        return (int)((body.getPosition().y - height / 2) * METTERS_PIXEL);
    }

    public void setPosition(float x, float y)
    {
        body.setTransform((x + width/2)* PIXEL_METTERS, (y + height/2)* PIXEL_METTERS, body.getAngle());
    }

    public float getWidth()
    {
        return width;
    }

    public void setWidth(float width)
    {
        this.width = width;
    }

    public float getHeight()
    {
        return height;
    }

    public void setHeight(float height)
    {
        this.height = height;
    }

    public World getWorld()
    {
        return world;
    }

    public Body getBody()
    {
        return body;
    }

    public void setBody(Body body)
    {
        this.body = body;
    }
}
