package Objects.AddButton.MVC;

import DB.StringRes.MySettings;
import DB.StringRes.NotificationsDictionary;
import Objects.AddButton.Listeners.AddButtonClickedListener;
import Objects.Base.BaseView.Nekomata;
import com.badlogic.gdx.math.Vector2;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AddButtonView extends Nekomata implements PropertyChangeListener {
    private final AddButtonController controller;

    public AddButtonView(AddButtonController c, AddButtonModel m)
    {
        super(MySettings.ATLAS_DAO.getAtlasDAO().getTexture(MySettings.BUTTONS_CHARSET), 1, 2, 2, 0.80f, false);

        controller = c;

        addListener(new AddButtonClickedListener(this));

        setWidth(MySettings.TILE_WIDTH);
        setHeight(MySettings.TILE_HEIGHT);

        setPosition(m.getPosition().x, m.getPosition().y);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        String notification = evt.getPropertyName();

        switch (notification)
        {
            case NotificationsDictionary.POSITION_SET:
                Vector2 dto = (Vector2) evt.getNewValue();
                setPosition(dto.x, dto.y);
                break;

        }
    }

    @Override
    public void updateAnimation() {
        setAnimation(0, false);
    }

    public void clicked()
    {
        controller.buttonClicked();
    }
}
