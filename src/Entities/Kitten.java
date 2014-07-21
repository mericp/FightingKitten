package Entities;

import Actores.Nekomata;
import DB.MySettings;
import PhysicalObjects.DynamicObject;
import PhysicalObjects.PhysicalObjectsFactory;
import PhysicalObjects.StaticObject;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Kitten extends Actor implements ICollisionable
{
    private float angle;

    //Model
    private DynamicObject dynamicBody;
    public StaticObject wayPoint;

    //View
    private Nekomata nekomata;
    private PointLight lights;

    public Kitten(World world, RayHandler rayHandler)
    {
        dynamicBody = (DynamicObject)PhysicalObjectsFactory.newPhysicalObject.NEW_KINEMATIK_OBJECT.create(world, MySettings.KITTEN_HITBOX_WIDTH, MySettings.KITTEN_HITBOX_HEIGHT);
        dynamicBody.getBody().setUserData(this);

        wayPoint = (StaticObject)PhysicalObjectsFactory.newPhysicalObject.NEW_STATIC_OBJECT.create(world, 1, 1);
        //wayPoint.getBody().getFixtureList().first().getFilterData().categoryBits = (short)0;
        //wayPoint.getBody().getFixtureList().first().getFilterData().groupIndex =  (short)-1;
        //wayPoint.getBody().getFixtureList().first().getFilterData().maskBits = (short)0;

        wayPoint.getBody().setUserData(this);

        TextureRegion texture = MySettings.ATLAS_DAO.getAtlasDAO().getTexture("gatito");
        nekomata = new Nekomata(texture, 8, 12, 3, 0.20f);

        this.addListener(new KittenDragListener(this));

        this.setWidth(MySettings.KITTEN_HITBOX_WIDTH);
        this.setHeight(MySettings.KITTEN_HITBOX_HEIGHT);

        this.lights = new PointLight(rayHandler, 300, new Color(0.7f,0.7f,0.7f, 0.5f), 400 * MySettings.PIXEL_METTERS, 0, 0);
        //this.lights.setContactFilter((short)1, (short)0, (short)1);
        this.lights.setSoft(true);
        this.lights.attachToBody(dynamicBody.getBody(), 0, 0);
        this.lights.setSoftnessLength(0.1f);
    }



    @Override public void draw (Batch batch, float alpha)
    {
        nekomata.draw(batch, alpha);
    }

    public void goToCoords(float x, float y)
    {
        //Angle
        dynamicBody.setDirectionVector(x, y);

        //Velocidad
        dynamicBody.setLinearVelocity(80f);
    }

    public void updateView()
    {
        updateViewPosition();
        updateAnimation();
    }

    // Relate model (body) with view (kitten and nekomata)
    public void updateViewPosition()
    {
        this.setPosition(dynamicBody.getBottomLeftCornerX(), dynamicBody.getBottomLeftCornerY());
        nekomata.setPosition(dynamicBody.getBottomLeftCornerX(), dynamicBody.getBottomLeftCornerY());
    }

    public void updateAnimation()
    {
        if(dynamicBody.getBody().getLinearVelocity().isZero())
        {
            //The kitten is sitting.
            nekomata.setAnimacion(18, false);
        }
        else
        {
            angle = dynamicBody.getVectorDireccion().angle();

            if (goesEast())
            {
                nekomata.setAnimacion(8, false);
            } else if (goesNortheast())
            {
                nekomata.setAnimacion(13, false);
            } else if (goesNorth())
            {
                nekomata.setAnimacion(12, false);
            } else if (goesNorthwest())
            {
                nekomata.setAnimacion(5, false);
            }
            else if (goesWest())
            {
                nekomata.setAnimacion(4, false);
            }
            else if (goesSouthwest())
            {
                nekomata.setAnimacion(1, false);
            }
            else if (goesSouth())
            {
                nekomata.setAnimacion(0, false);
            }
            else if (goesSoutheast())
            {
                nekomata.setAnimacion(9, false);
            }
        }
    }

    private boolean goesEast()
    {
        return (angle >= 0f && angle <= 22.5f) ||
                (angle > 337.5f && angle <= 360f);
    }

    private boolean goesNortheast()
    {
        return angle > 22.5f && angle <= 67.5f;
    }

    private boolean goesNorth()
    {
        return angle > 67.5f && angle <= 112.5f;
    }

    private boolean goesNorthwest()
    {
        return angle > 112.5f && angle <= 157.5f;
    }

    private boolean goesWest()
    {
        return angle > 157.5f && angle <= 202.5f;
    }

    private boolean goesSouthwest()
    {
        return angle > 202.5f && angle <= 247.5f;
    }

    private boolean goesSouth()
    {
        return angle > 247.5f && angle <= 292.5f;
    }

    private boolean goesSoutheast()
    {
        return angle > 292.5f && angle <= 337.5f;
    }

    public void setModelPosition(float x, float y)
    {
        super.setPosition(x, y);
        dynamicBody.setPosition(x, y);
        wayPoint.setPosition(x, y);
    }

    public DynamicObject getDynamicBody()
    {
        return this.dynamicBody;
    }

    public StaticObject getWayPoint()
    {
        return this.wayPoint;
    }

    public int getCenterX()
    {
        return dynamicBody.getCenterX();
    }

    public int getCenterY()
    {
        return dynamicBody.getCenterY();
    }

    @Override
    public void onCollide()
    {
        this.dynamicBody.setLinearVelocity(0f);
        nekomata.setAnimacion(18, false);
    }
}
