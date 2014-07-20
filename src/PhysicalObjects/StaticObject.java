package PhysicalObjects;

import com.badlogic.gdx.math.Vector2;
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
    private Vector2 vectorDirection;

    public StaticObject(World world, int width, int height)
    {
        this.world = world;
        this.width = width * PIXEL_METTERS;
        this.height = height * PIXEL_METTERS;
        this.vectorDirection = new Vector2();
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

    public void setVectorDireccion(Vector2 destino)
    {
        setVectorDireccion(destino.x, destino.y);
    }

    public void setVectorDireccion(float destinoX, float destinoY)
    {
        //mover para obtener vector posicion
        vectorDirection.x = (destinoX - this.getCenterX());
        vectorDirection.y = (destinoY - this.getCenterY());

        //Obtener longitud del vector
        float longitud = Vector2.dst(0, 0, vectorDirection.x, vectorDirection.y);

        //Convertir en vector unitario
        vectorDirection.x = vectorDirection.x / longitud;
        vectorDirection.y = vectorDirection.y / longitud;
    }

    public Vector2 getVectorDireccion()
    {
        return  this.vectorDirection;
    }
}
