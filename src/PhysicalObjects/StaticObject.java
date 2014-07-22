package PhysicalObjects;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import static DB.MySettings.METERS_PIXEL;
import static DB.MySettings.PIXEL_METERS;

public class StaticObject implements IPhysicalObject
{
    private World world;
    private float width;
    private float height;
    private Body body;

    public StaticObject(World world, int width, int height)
    {
        this.world = world;
        this.width = width * PIXEL_METERS;
        this.height = height * PIXEL_METERS;
    }

    @Override
    public int getBottomLeftCornerX() {
        return (int)((body.getPosition().x - width / 2) * METERS_PIXEL);
    }

    @Override
    public int getBottomLeftCornerY() {
        return (int)((body.getPosition().y - height / 2) * METERS_PIXEL);
    }

    public void setPosition(float x, float y)
    {
        body.setTransform((x + width/2)* PIXEL_METERS, (y + height/2)* PIXEL_METERS, body.getAngle());
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
