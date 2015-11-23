package Objects.Monster.MVC;

import Objects.Base.BaseMob.AbstractMob;
import Objects.Monster.DTO.MonsterDtos;
import Objects.World.MVC.WorldController;
import com.badlogic.gdx.math.Vector2;

public class MonsterController {
    private WorldController mc;
    private MonsterModel model;

    public MonsterController(WorldController mcpar)
    {
        mc = mcpar;
    }

    public void create(Vector2 position)
    {
        createModel(position);
        MonsterView view = createView(model);
        addToWorld(model, view);
    }

    private void createModel(Vector2 position)
    {
        model = new MonsterModel(position);
        model.setPosition(position);
    }

    private MonsterView createView(MonsterModel model)
    {
        return new MonsterView(model);
    }

    public void setTarget(AbstractMob target)
    {
        model.setTarget(target);
    }

    private void addToWorld(MonsterModel model, MonsterView view)
    {
        MonsterDtos.MonsterDto m = new MonsterDtos.MonsterDto(model, view);
        mc.getModel().addMob(m);
    }
}
