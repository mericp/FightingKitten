package PhysicalObjects;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import static DB.MySettings.METERS_PIXEL;
import static DB.MySettings.PIXEL_METERS;

public class StaticObject implements IPhysicalObject
{
    private World world;

    private Body body;
    private float widthOfTheBody;
    private float heightOfTheBody;

    public StaticObject(World world, int width, int height)
    {
        this.world = world;
        this.widthOfTheBody = width * PIXEL_METERS;
        this.heightOfTheBody = height * PIXEL_METERS;
    }

    @Override
    public int getBottomLeftCornerX() {
        return (int)((body.getPosition().x - widthOfTheBody / 2) * METERS_PIXEL);
    }

    @Override
    public int getBottomLeftCornerY() {
        return (int)((body.getPosition().y - heightOfTheBody / 2) * METERS_PIXEL);
    }

    public void setPosition(float x, float y)
    {
        body.setTransform((x + widthOfTheBody /2)* PIXEL_METERS, (y + heightOfTheBody /2)* PIXEL_METERS, body.getAngle());
    }

    public float getWidth()
    {
        return widthOfTheBody;
    }

    public void setWidth(float width)
    {
        this.widthOfTheBody = width;
    }

    public float getHeight()
    {
        return heightOfTheBody;
    }

    public void setHeight(float height)
    {
        this.heightOfTheBody = height;
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
