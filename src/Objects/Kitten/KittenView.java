package Objects.Kitten;

import Objects.Base.BaseView.Nekomata;
import DB.MySettings;
import Entities.KittenDragListener;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;

public class KittenView extends Nekomata
{
    private float angle;
    private KittenModel kittenModel;

    public KittenView(KittenModel km, RayHandler rayHandler)
    {
        super(MySettings.ATLAS_DAO.getAtlasDAO().getTexture("gatito"), 8, 12, 3, 0.20f);

        addListener(new KittenDragListener(this));

        this.setWidth(MySettings.KITTEN_HITBOX_WIDTH);
        this.setHeight(MySettings.KITTEN_HITBOX_HEIGHT);

        kittenModel = km;
        setPosition(kittenModel.getDynamicBody().getBottomLeftCornerX(), kittenModel.getDynamicBody().getBottomLeftCornerY());

        setLights(rayHandler);
    }

    private void setLights(RayHandler rayHandler)
    {
        PointLight lights = new PointLight(rayHandler, 300, new Color(0.7f,0.7f,0.7f, 0.5f), 400 * MySettings.PIXEL_METERS, 0, 0);
        lights.setSoft(true);
        lights.attachToBody(kittenModel.getDynamicBody().getBody(), 0, 0);
        lights.setSoftnessLength(0.1f);
    }

    public void updateAnimation()
    {
        if(kittenModel.getDynamicBody().getBody().getLinearVelocity().isZero())
        {
            //The kitten is sitting.
            this.setAnimation(18, false);
        }
        else
        {
            angle = kittenModel.getDynamicBody().getVectorDireccion().angle();

            if (goesEast())
            {
                this.setAnimation(8, false);
            } else if (goesNortheast())
            {
                this.setAnimation(13, false);
            } else if (goesNorth())
            {
                this.setAnimation(12, false);
            } else if (goesNorthwest())
            {
                this.setAnimation(5, false);
            }
            else if (goesWest())
            {
                this.setAnimation(4, false);
            }
            else if (goesSouthwest())
            {
                this.setAnimation(1, false);
            }
            else if (goesSouth())
            {
                this.setAnimation(0, false);
            }
            else if (goesSoutheast())
            {
                this.setAnimation(9, false);
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
}
