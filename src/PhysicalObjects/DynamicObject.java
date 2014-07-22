package PhysicalObjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import static DB.MySettings.METERS_PIXEL;
import static DB.MySettings.PIXEL_METERS;

public class DynamicObject implements IPhysicalObject
{
    private World world;
    private float width;
    private float height;
    private Body body;
    private Vector2 directionVector;
    private Vector2 lastPosition;
    private Vector2 interpoledPosition;

    public DynamicObject(World world, int width, int height)
    {
        this.world = world;
        this.width = convertToMeters(width);
        this.height = convertToMeters(height);
        this.directionVector = new Vector2();

        this.lastPosition = new Vector2();
        this.interpoledPosition = new Vector2();
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
        body.setTransform(convertToMeters(x + width / 2),
                          convertToMeters(y + height / 2),
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

    public void saveLastPosition()
    {
        this.lastPosition.set(body.getPosition().x, body.getPosition().y);
    }

    public void interpolatePositions(float alpha)
    {
        this.interpoledPosition.x = lastPosition.x + (body.getPosition().x - lastPosition.x) * alpha;
        this.interpoledPosition.y = lastPosition.y + (body.getPosition().y - lastPosition.y) * alpha;
    }

    public int getInterpolatedX()
    {
        return (int)(convertToPixels(this.interpoledPosition.x - width / 2));
    }

    public int getInterpolatedY()
    {
        return (int)(convertToPixels(this.interpoledPosition.y - height / 2));
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
        body.setLinearVelocity(convertToMeters(directionVector.x * velocity), convertToMeters(directionVector.y * velocity));
    }

    private float convertToPixels(float metters)
    {
        return metters * METERS_PIXEL;
    }

    private float convertToMeters(float pixels)
    {
        return pixels * PIXEL_METERS;
    }
}
