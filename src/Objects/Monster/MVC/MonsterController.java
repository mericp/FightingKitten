package Objects.Monster.MVC;

import Objects.Monster.DTO.MonsterDtos;
import Objects.World.MVC.WorldController;
import Behaviors.Base.SuperClasses.SteerableAgent;
import com.badlogic.gdx.math.Vector2;

public class MonsterController {
    private final WorldController mc;
    private final MonsterModel model;

    public MonsterController(WorldController mcpar, Vector2 position)
    {
        mc = mcpar;
        model = new MonsterModel(position);
        config();
    }

    private void config()
    {
        MonsterView view = createView(model);
        addToWorld(model, view);
    }

    private MonsterView createView(MonsterModel model)
    {
        return new MonsterView(model);
    }

    public void changeTarget(SteerableAgent target)
    {
        model.changeTarget(target);
    }

    private void addToWorld(MonsterModel model, MonsterView view)
    {
        MonsterDtos.MonsterDto m = new MonsterDtos.MonsterDto(model, view);
        mc.getModel().addMob(m);
    }
}
