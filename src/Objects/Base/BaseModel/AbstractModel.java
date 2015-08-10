package Objects.Base.BaseModel;

import com.badlogic.gdx.physics.box2d.World;

public abstract class AbstractModel extends Observable
{
    protected World world;

    public AbstractModel(World w)
    {
        super();
        world = w;
    }
}
