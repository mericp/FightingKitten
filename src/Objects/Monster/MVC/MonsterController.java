package Objects.Monster.MVC;

import Objects.Monster.DTO.MonsterDtos;
import Objects.World.MVC.WorldController;
import SteerableBehavior.Base.SteerableAgent;
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
    }

    private MonsterView createView(MonsterModel model)
    {
        return new MonsterView(model);
    }

    public void setTarget(SteerableAgent target)
    {
        model.setTarget(target);
    }

    private void addToWorld(MonsterModel model, MonsterView view)
    {
        MonsterDtos.MonsterDto m = new MonsterDtos.MonsterDto(model, view);
        mc.getModel().addMob(m);
    }
}
