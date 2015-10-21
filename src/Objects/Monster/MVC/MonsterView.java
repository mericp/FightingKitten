package Objects.Monster.MVC;

import DB.StringRes.MySettings;
import DB.StringRes.NotificationsDictionary;
import Objects.Base.BaseView.Nekomata;
import DB.StringRes.MonsterAnimationDictionary;
import com.badlogic.gdx.math.Vector2;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MonsterView extends Nekomata implements PropertyChangeListener {
    private MonsterAnimationDictionary animationDictionary;

    public MonsterView(MonsterModel model)
    {
        super(MySettings.ATLAS_DAO.getAtlasDAO().getTexture(MySettings.MONSTER_CHARSET), 8, 12, 3, 0.20f, true);

        animationDictionary = new MonsterAnimationDictionary();
        animationDictionary.Create();

        setWidth(MySettings.TILE_WIDTH);
        setHeight(MySettings.TILE_HEIGHT);

        setPosition(model.getPosition().x, model.getPosition().y);
    }

    @Override
    public void updateAnimation() {
        animate("GoingSouth");
    }

    private void animate(String name)
    {
        setAnimation(animationDictionary.getAnimationNumber(name), false);
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
        }
    }
}
