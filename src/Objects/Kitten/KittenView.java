package Objects.Kitten;

import DB.NotificationsDictionary;
import Objects.Base.BaseDto.PositionDTO;
import Objects.Base.BaseView.Nekomata;
import DB.MySettings;
import Entities.KittenDragListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class KittenView extends Nekomata implements PropertyChangeListener
{
    private float angle;
    private KittenModel kittenModel;

    public KittenView(KittenModel km)
    {
        super(MySettings.ATLAS_DAO.getAtlasDAO().getTexture("gatito"), 8, 12, 3, 0.20f);

        addListener(new KittenDragListener(this));

        this.setWidth(MySettings.KITTEN_HITBOX_WIDTH);
        this.setHeight(MySettings.KITTEN_HITBOX_HEIGHT);

        kittenModel = km;
        setPosition(kittenModel.getDynamicBody().getBottomLeftCornerX(), kittenModel.getDynamicBody().getBottomLeftCornerY());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String notification = evt.getPropertyName();

        switch (notification) {
            case NotificationsDictionary.POSITION_SET:
                PositionDTO dto = (PositionDTO) evt.getNewValue();
                setPosition(dto.x, dto.y);
                break;

            case NotificationsDictionary.ANIMATION_CHANGED:
                updateAnimation();
                break;

        }
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
