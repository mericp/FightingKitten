package PhysicalObjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import static DB.MySettings.METTERS_PIXEL;
import static DB.MySettings.PIXEL_METTERS;

public class DynamicObject implements IPhysicalObject
{
    private World world;
    private float width;
    private float height;
    private Body body;
    private Vector2 directionVector;

    public DynamicObject(World world, int width, int height)
    {
        this.world = world;
        this.width = convertToMetters(width);
        this.height = convertToMetters(height);
        this.directionVector = new Vector2();
    }

    @Override public int getBottomLeftCornerX()
    {
        return (int)(convertToPixels(body.getPosition().x - width / 2));
    }
    @Override public int getBottomLeftCornerY()
    {
        return (int)(convertToPixels(body.getPosition().y - height / 2));
    }

    public int getCenterX()
    {
        return (int)(convertToPixels(body.getPosition().x));
    }
    public int getCenterY()
    {
        return (int)(convertToPixels(body.getPosition().y));
    }

    //Calculates coords of the center of the dynamic object from the bottom left corner coords (x, y)
    @Override public void setPosition(float x, float y)
    {
        body.setTransform(convertToMetters(x + width/2),
                          convertToMetters(y + height/2),
                          body.getAngle());
    }

    @Override public void setWidth(float width)
    {
        this.width = width;
    }
    @Override public float getWidth()
    {
        return width;
    }

    @Override public void setHeight(float height)
    {
        this.height = height;
    }
    @Override public float getHeight()
    {
        return height;
    }

    @Override public World getWorld()
    {
        return world;
    }

    @Override public void setBody(Body body)
    {
        this.body = body;
    }
    @Override public Body getBody()
    {
        return body;
    }

    public void setDirectionVector(float detinationX, float destinationY)
    {
        //Move to 0,0 to get position vector.
        directionVector.x = (detinationX - this.getCenterX());
        directionVector.y = (destinationY - this.getCenterY());

        //Convert to unit vector.
        directionVector.nor();
    }

    public Vector2 getVectorDireccion()
    {
        return  this.directionVector;
    }

    public void setLinearVelocity(float velocity)
    {
        body.setLinearVelocity(convertToMetters(directionVector.x * velocity), convertToMetters(directionVector.y * velocity));
    }

    private float convertToPixels(float metters)
    {
        return metters * METTERS_PIXEL;
    }

    private float convertToMetters(float pixels)
    {
        return pixels * PIXEL_METTERS;
    }
}
