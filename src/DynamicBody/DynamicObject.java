package DynamicBody;

import DB.MySettings;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class DynamicObject
{
    private World world;
    private float width;
    private float height;
    private Body body;

    public DynamicObject(World world, int width, int height)
    {
        this.world = world;
        this.width = width * MySettings.PIXEL_METTERS;
        this.height = height * MySettings.PIXEL_METTERS;
    }

    public int getX()
    {
        return (int)((body.getPosition().x - width / 2) * MySettings.METTERS_PIXEL);
    }

    public int getY()
    {
        return (int)((body.getPosition().y - height / 2) * MySettings.METTERS_PIXEL);
    }

    public void setPosition(float x, float y)
    {
        body.setTransform(x * MySettings.PIXEL_METTERS, y * MySettings.PIXEL_METTERS, body.getAngle());
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public World getWorld() {
        return world;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
