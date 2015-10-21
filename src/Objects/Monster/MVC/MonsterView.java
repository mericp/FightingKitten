package Objects.Monster.MVC;

import DB.StringRes.MySettings;
import DB.StringRes.NotificationsDictionary;
import Objects.Base.BaseView.Nekomata;
import DB.StringRes.MonsterAnimationDictionary;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MonsterView extends Nekomata implements PropertyChangeListener {
    private final MonsterModel model;
    private final MonsterController controller;
    private MonsterAnimationDictionary animationDictionary;
    private float angle;

    public MonsterView(MonsterController controller, MonsterModel model, RayHandler rayHandler)
    {
        super(MySettings.ATLAS_DAO.getAtlasDAO().getTexture(MySettings.MONSTER_CHARSET), 8, 12, 3, 0.20f, true);

        this.controller = controller;
        this.model = model;

        animationDictionary = new MonsterAnimationDictionary();
        animationDictionary.Create();

        setWidth(MySettings.TILE_WIDTH);
        setHeight(MySettings.TILE_HEIGHT);

        setPosition(model.getDynamicBody().getBottomLeftCornerX(), model.getDynamicBody().getBottomLeftCornerY());

        setLights(rayHandler);
    }

    private void setLights(RayHandler rayHandler)
    {
        PointLight lights = new PointLight(rayHandler, 300, new Color(0.7f,0.7f,0.7f, 0.5f), 400 * MySettings.PIXEL_METERS, 0, 0);
        lights.setSoft(true);
        lights.attachToBody(model.getDynamicBody().getBody(), 0, 0);
        lights.setSoftnessLength(0.1f);
    }

    @Override
    public void updateAnimation() {
        angle = model.getDynamicBody().getVectorDireccion().angle();

        if (goesEast())
        {
            animate("GoingEast");
        }
        else if (goesNorth())
        {
            animate("GoingNorth");
        }
        else if (goesWest())
        {
            animate("GoingWest");
        }
        else if (goesSouth())
        {
            animate("GoingSouth");
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

    private boolean goesNorth()
    {
        return angle > 67.5f && angle <= 112.5f;
    }

    private boolean goesWest()
    {
        return angle > 157.5f && angle <= 202.5f;
    }

    private boolean goesSouth()
    {
        return angle > 247.5f && angle <= 292.5f;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String notification = evt.getPropertyName();

        switch (notification)
        {
            case NotificationsDictionary.POSITION_SET:
                Vector2 dto = (Vector2) evt.getNewValue();
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
        setPosition(model.getDynamicBody().getInterpolatedX(), model.getDynamicBody().getInterpolatedY());
    }
}
