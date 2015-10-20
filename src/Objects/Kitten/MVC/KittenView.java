package Objects.Kitten.MVC;

import Listeners.KittenClickedListener;
import Objects.Base.BaseDto.PositionDTO;
import Objects.Base.BaseView.Nekomata;
import DB.MySettings;
import DB.NotificationsDictionary;
import Listeners.KittenDragListener;
import Objects.Kitten.KittenAnimationDictionary;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class KittenView extends Nekomata implements PropertyChangeListener
{
    private final KittenModel kittenModel;
    private final KittenController kittenController;
    private float angle;
    private KittenAnimationDictionary animationDictionary;

    public KittenView(KittenController kittenController, KittenModel kittenModel, RayHandler rayHandler)
    {
        super(MySettings.ATLAS_DAO.getAtlasDAO().getTexture("gatito"), 8, 12, 3, 0.20f, true);

        this.kittenController = kittenController;
        this.kittenModel = kittenModel;

        animationDictionary = new KittenAnimationDictionary();
        animationDictionary.Create();

        addListener(new KittenDragListener(this));
        addListener(new KittenClickedListener(this));

        setWidth(MySettings.TILE_WIDTH);
        setHeight(MySettings.TILE_HEIGHT);

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
                PositionDTO dto = (PositionDTO) evt.getNewValue();
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
        boolean sitting = kittenModel.getDynamicBody().getBody().getLinearVelocity().isZero();
        boolean selected = isSelected && sitting;

        if(selected)
        {
            animate("Selected");
        }
        else if (sitting)
        {
            animate("Sitting");
        }
        else
        {
            angle = kittenModel.getDynamicBody().getVectorDireccion().angle();

            if (goesEast())
            {
                animate("GoingEast");
            }
            else if (goesNortheast())
            {
                animate("GoingNorthEast");
            }
            else if (goesNorth())
            {
                animate("GoingNorth");
            }
            else if (goesNorthwest())
            {
                animate("GoingNorthWest");
            }
            else if (goesWest())
            {
                animate("GoingWest");
            }
            else if (goesSouthwest())
            {
                animate("GoingSouthWest");
            }
            else if (goesSouth())
            {
                animate("GoingSouth");
            }
            else if (goesSoutheast())
            {
                animate("GoingSouthEast");
            }
        }
    }

    private void animate(String name)
    {
        setAnimation(animationDictionary.getAnimationNumber(name), false);
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

    private boolean isSelected = false;
    public void mousehover()
    {
        isSelected = true;
    }

    public void mouseleft()
    {
        isSelected = false;
    }
}
