package Objects.Kitten.MVC;

import Objects.Kitten.Listeners.KittenClickedListener;
import Objects.Base.BaseView.Nekomata;
import DB.StringRes.MySettings;
import Objects.Kitten.Listeners.KittenDragListener;
import DB.StringRes.KittenAnimationDictionary;
import com.badlogic.gdx.math.Vector2;

public class KittenView extends Nekomata
{
    private  KittenModel model;
    private final KittenController controller;
    private Double angle;
    private KittenAnimationDictionary animationDictionary;

    public KittenView(KittenController controller, KittenModel model)
    {
        super(MySettings.ATLAS_DAO.getAtlasDAO().getTexture(MySettings.KITTEN), 8, 12, 3, 0.20f, true);

        this.controller = controller;
        this.model = model;

        animationDictionary = new KittenAnimationDictionary();
        animationDictionary.Create();

        addListener(new KittenDragListener(this));
        addListener(new KittenClickedListener(this));

        setWidth(MySettings.TILE_WIDTH);
        setHeight(MySettings.TILE_HEIGHT);

        setPosition(model.getPosition().x, model.getPosition().y);
    }

    @Override
    public void updateAnimation()
    {
        setPosition(model.getPosition().x, model.getPosition().y);

        boolean sitting = model.getLinearVelocity().isZero(0.1f);

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
            angle = (Math.toDegrees(model.getOrientation()) + 360) % 360;

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
        controller.dragged(clickPosition, model);
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
