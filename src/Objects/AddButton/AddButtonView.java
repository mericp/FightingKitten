package Objects.AddButton;

import DB.MySettings;
import DB.NotificationsDictionary;
import Listeners.AddButtonClickedListener;
import Objects.Base.BaseDto.PositionDTO;
import Objects.Base.BaseView.Nekomata;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AddButtonView extends Nekomata implements PropertyChangeListener {
    private final AddButtonModel model;
    private final AddButtonController controller;

    public AddButtonView(AddButtonController c, AddButtonModel m)
    {
        super(MySettings.ATLAS_DAO.getAtlasDAO().getTexture("addButton"), 1, 2, 2, 0.80f, false);

        controller = c;
        model = m;

        addListener(new AddButtonClickedListener(this));

        setWidth(MySettings.KITTEN_HITBOX_WIDTH);
        setHeight(MySettings.KITTEN_HITBOX_HEIGHT);

        setPosition(model.getDynamicBody().getBottomLeftCornerX(), model.getDynamicBody().getBottomLeftCornerY());
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
        setAnimation(0, false);
    }

    public void clicked()
    {
        controller.buttonClicked();
    }
}
