package Objects.AddButton.MVC;

import DB.MySettings;
import Objects.Base.BaseMob.AbstractMob;
import com.badlogic.gdx.physics.box2d.World;

public class AddButtonModel extends AbstractMob
{
    public AddButtonModel(World w)
    {
        super(w, MySettings.TILE_WIDTH, MySettings.TILE_HEIGHT);
    }

    @Override
    public void interpolatePositions(float alpha) {

    }

    @Override
    public void onCollide() {

    }
}
