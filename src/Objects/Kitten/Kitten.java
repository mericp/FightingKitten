package Objects.Kitten;

import DB.MySettings;
import Entities.ICollisionable;
import Entities.KittenDragListener;
import Objects.Base.BaseView.Nekomata;
import PhysicalObjects.DynamicObject;
import PhysicalObjects.PhysicalObjectsFactory;
import PhysicalObjects.StaticObject;
import box2dLight.PointLight;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Kitten extends Actor implements ICollisionable
{
    public Kitten(World world)
    {
        //Model
        dynamicBody = (DynamicObject)PhysicalObjectsFactory.create(DynamicObject.class, world, MySettings.KITTEN_HITBOX_WIDTH, MySettings.KITTEN_HITBOX_HEIGHT);
        dynamicBody.getBody().setUserData(this);
        wayPoint = (StaticObject)PhysicalObjectsFactory.create(StaticObject.class, world, 1, 1);
        wayPoint.getBody().setUserData(this);

        //View
        TextureRegion texture = MySettings.ATLAS_DAO.getAtlasDAO().getTexture("gatito");
        nekomata = new Nekomata(texture, 8, 12, 3, 0.20f);
        this.addListener(new KittenDragListener(this));
        this.setWidth(MySettings.KITTEN_HITBOX_WIDTH);
        this.setHeight(MySettings.KITTEN_HITBOX_HEIGHT);
    }

    // Relate model (body) with view (kitten and nekomata) <-- This seems a work for the Controller!
    public void updateViewPosition()
    {
        this.setPosition(dynamicBody.getInterpolatedX(), dynamicBody.getInterpolatedY());
        nekomata.setPosition(dynamicBody.getInterpolatedX(), dynamicBody.getInterpolatedY());
    }

    //#################################View#################################
    private Nekomata nekomata;
    private float angle;

    @Override public void draw (Batch batch, float alpha)
    {
        nekomata.draw(batch, alpha);
    }

    public void updateView()
    {
        updateAnimation();
    }

    public void updateAnimation()
    {
        if(dynamicBody.getBody().getLinearVelocity().isZero())
        {
            //The kitten is sitting.
            nekomata.setAnimation(18, false);
        }
        else
        {
            angle = dynamicBody.getVectorDireccion().angle();

            if (goesEast())
            {
                nekomata.setAnimation(8, false);
            } else if (goesNortheast())
            {
                nekomata.setAnimation(13, false);
            } else if (goesNorth())
            {
                nekomata.setAnimation(12, false);
            } else if (goesNorthwest())
            {
                nekomata.setAnimation(5, false);
            }
            else if (goesWest())
            {
                nekomata.setAnimation(4, false);
            }
            else if (goesSouthwest())
            {
                nekomata.setAnimation(1, false);
            }
            else if (goesSouth())
            {
                nekomata.setAnimation(0, false);
            }
            else if (goesSoutheast())
            {
                nekomata.setAnimation(9, false);
            }
        }
    }

    private boolean goesEast()
    {
        return (angle >= 0f && angle <= 22.5f) || (angle > 337.5f && angle <= 360f);
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

    ////#################################Model#################################
    private DynamicObject dynamicBody;
    public StaticObject wayPoint;

    public void goToCoords(float x, float y)
    {
        //Angle
        dynamicBody.setDirectionVector(x, y);

        //Velocidad
        dynamicBody.setLinearVelocity(80f);
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

    public void interpolatePositions(float alpha)
    {
        dynamicBody.interpolatePositions(alpha);
        updateViewPosition();
    }

    @Override
    public void onCollide()
    {
        //Model
        this.dynamicBody.setLinearVelocity(0f);

        //View
        nekomata.setAnimation(18, false);
    }
}
