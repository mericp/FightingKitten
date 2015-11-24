package Objects.Environment.AddButton.MVC;

import DB.StringRes.MySettings;
import Objects.Environment.AddButton.Listeners.AddButtonClickedListener;
import Objects.Base.BaseView.Nekomata;

public class AddButtonView extends Nekomata {
    private final AddButtonController controller;

    public AddButtonView(AddButtonController c, AddButtonModel m)
    {
        super(MySettings.ATLAS_DAO.getAtlasDAO().getTexture(MySettings.BUTTON), 1, 2, 2, 0.80f, false);

        controller = c;

        addListener(new AddButtonClickedListener(this));

        setWidth(MySettings.TILE_WIDTH);
        setHeight(MySettings.TILE_HEIGHT);

        setPosition(m.getPosition().x, m.getPosition().y);
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
