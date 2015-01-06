package Views;

import Actores.Nekomata;
import Controllers.KittenController;
import DB.MySettings;
import DB.NotificationsDictionary;
import DTOs.KittenDTOs;
import Entities.KittenDragListener;
import Models.KittenModel;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class KittenView extends Nekomata implements PropertyChangeListener, IMobView
{
    private final KittenModel kittenModel;
    private final KittenController kittenController;
    private float angle;

    public KittenView(KittenController kittenController, KittenModel kittenModel, RayHandler rayHandler)
    {
        super(MySettings.ATLAS_DAO.getAtlasDAO().getTexture("gatito"), 8, 12, 3, 0.20f);

        this.kittenController = kittenController;
        this.kittenModel = kittenModel;

        addListener(new KittenDragListener(this));

        this.setWidth(MySettings.KITTEN_HITBOX_WIDTH);
        this.setHeight(MySettings.KITTEN_HITBOX_HEIGHT);

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

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        String notification = evt.getPropertyName();

        switch (notification)
        {
            case NotificationsDictionary.POSITION_SET:
                KittenDTOs.PositionDTO dto = (KittenDTOs.PositionDTO) evt.getNewValue();
                setPosition(dto.x, dto.y);
                break;

            case NotificationsDictionary.ANIMATION_CHANGED:
                updateAnimation();
                break;

            case NotificationsDictionary.POSITION_INTERPOLATED:
                interpolatePosition();
                break;

        }
    }

    private void interpolatePosition()
    {
        setPosition(kittenModel.getDynamicBody().getInterpolatedX(), kittenModel.getDynamicBody().getInterpolatedY());
    }

    @Override
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

    public void dragged(Vector2 clickPosition)
    {
        kittenController.KittenDragged(clickPosition, kittenModel);
    }
}
