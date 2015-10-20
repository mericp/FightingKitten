package Objects.Monster.MVC;

import DB.MySettings;
import DB.NotificationsDictionary;
import Objects.Base.BaseMob.AbstractMob;
import com.badlogic.gdx.physics.box2d.World;

public class MonsterModel extends AbstractMob {
    public MonsterModel(World w)
    {
        super(w, MySettings.TILE_WIDTH, MySettings.TILE_HEIGHT);
    }

    @Override
    public void onCollide()
    {
        this.dynamicBody.setLinearVelocity(0f);
        this.notifyUpdate(NotificationsDictionary.ANIMATION_CHANGED, null);
    }

    @Override
    public void setPosition(float x, float y)
    {
        super.setPosition(x, y);
    }

    @Override
    public void interpolatePositions(float alpha)
    {
        dynamicBody.interpolatePositions(alpha);
        this.notifyUpdate(NotificationsDictionary.POSITION_INTERPOLATED, null);
    }
}
