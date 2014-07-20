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
        this.width = width * PIXEL_METTERS;
        this.height = height * PIXEL_METTERS;
        this.directionVector = new Vector2();
    }

    public int getX()
    {
        return (int)((body.getPosition().x - width / 2) * METTERS_PIXEL);
    }
    public int getY()
    {
        return (int)((body.getPosition().y - height / 2) * METTERS_PIXEL);
    }
    public int getCenterX()
    {
        return (int)(body.getPosition().x * METTERS_PIXEL);
    }
    public int getCenterY()
    {
        return (int)(body.getPosition().y * METTERS_PIXEL);
    }

    public void setPosition(float x, float y)
    {
        body.setTransform((x + width/2)* PIXEL_METTERS, (y + height/2)* PIXEL_METTERS, body.getAngle());
    }

    @Override public float getWidth()
    {
        return width;
    }

    public void setWidth(float width)
    {
        this.width = width;
    }

    @Override public float getHeight()
    {
        return height;
    }

    public void setHeight(float height)
    {
        this.height = height;
    }

    @Override public World getWorld()
    {
        return world;
    }

    @Override public Body getBody()
    {
        return body;
    }

    @Override public void setBody(Body body)
    {
        this.body = body;
    }


    public void setDirectionVector(float destinoX, float destinoY)
    {
        //mover para obtener vector posicion
        directionVector.x = (destinoX - this.getCenterX());
        directionVector.y = (destinoY - this.getCenterY());

        //Obtener longitud del vector
        float longitud = Vector2.dst(0, 0, directionVector.x, directionVector.y);

        //Convertir en vector unitario
        directionVector.x = directionVector.x / longitud;
        directionVector.y = directionVector.y / longitud;
    }

    public Vector2 getVectorDireccion()
    {
        return  this.directionVector;
    }

    public void setLinearVeolicity(float velocity)
    {
        body.setLinearVelocity(directionVector.x * velocity * PIXEL_METTERS, directionVector.y * velocity * PIXEL_METTERS);
    }
}
