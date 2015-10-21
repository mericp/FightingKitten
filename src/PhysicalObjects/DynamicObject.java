package PhysicalObjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import static DB.StringRes.MySettings.METERS_PIXEL;
import static DB.StringRes.MySettings.PIXEL_METERS;

public class DynamicObject implements IPhysicalObject
{
    private final World world;
    private Body body;
    private float widthOfTheBody;
    private float heightOfTheBody;
    private final Vector2 directionVector;

    // Interpolation
    private final Vector2 lastPosition;
    private final Vector2 interpoledPosition;

    public DynamicObject(World world, int width, int height)
    {
        this.world = world;
        widthOfTheBody = convertToMeters(width);
        heightOfTheBody = convertToMeters(height);
        directionVector = new Vector2();
        lastPosition = new Vector2();
        interpoledPosition = new Vector2();
    }

    @Override
    public int getBottomLeftCornerX()
    {
        return (int)(convertToPixels(body.getPosition().x - widthOfTheBody / 2));
    }

    @Override
    public int getBottomLeftCornerY()
    {
        return (int)(convertToPixels(body.getPosition().y - heightOfTheBody / 2));
    }

    private int getCenterX()
    {
        return (int)(convertToPixels(body.getPosition().x));
    }
    private int getCenterY()
    {
        return (int)(convertToPixels(body.getPosition().y));
    }

    //Calculates coords of the center of the dynamic object from the bottom left corner coords (x, y)
    @Override
    public void setPosition(float x, float y)
    {
        body.setTransform(convertToMeters(x + widthOfTheBody / 2),
                          convertToMeters(y + heightOfTheBody / 2),
                          body.getAngle());
    }

    @Override
    public void setWidth(float width)
    {
        this.widthOfTheBody = width;
    }

    @Override
    public float getWidth()
    {
        return widthOfTheBody;
    }

    @Override
    public void setHeight(float height)
    {
        this.heightOfTheBody = height;
    }

    @Override
    public float getHeight()
    {
        return heightOfTheBody;
    }

    @Override
    public World getWorld()
    {
        return world;
    }

    @Override
    public void setBody(Body body)
    {
        this.body = body;
    }

    @Override
    public Body getBody()
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
        return (int)(convertToPixels(this.interpoledPosition.x - widthOfTheBody / 2));
    }

    public int getInterpolatedY()
    {
        return (int)(convertToPixels(this.interpoledPosition.y - heightOfTheBody / 2));
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
