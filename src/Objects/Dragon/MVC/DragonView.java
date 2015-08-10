package Objects.Dragon.MVC;

import DB.MySettings;
import DB.NotificationsDictionary;
import Objects.Base.BaseDto.PositionDTO;
import Objects.Base.BaseView.Nekomata;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DragonView extends Nekomata implements PropertyChangeListener {
    private final DragonModel model;
    private final DragonController controller;

    public DragonView(DragonController c, DragonModel m, RayHandler rayHandler)
    {
        super(MySettings.ATLAS_DAO.getAtlasDAO().getTexture("dragon"), 4, 3, 3, 0.80f, true);

        controller = c;
        model = m;

        setWidth(260);
        setHeight(200);

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
    public void propertyChange(PropertyChangeEvent evt)
    {
        String notification = evt.getPropertyName();

        switch (notification)
        {
            case NotificationsDictionary.POSITION_SET:
                PositionDTO dto = (PositionDTO) evt.getNewValue();
                setPosition(dto.x, dto.y);
                break;

        }
    }

    @Override
    public void updateAnimation() {
        setAnimation(1, false);
    }

    public void clicked()
    {
        controller.buttonClicked();
    }
}