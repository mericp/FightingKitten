package Objects.Catalog.Monster.MVC;

import Objects.Catalog.Monster.DTO.MonsterDtos;
import Objects.Catalog.World.MVC.WorldController;
import Behaviors.Base.SuperClasses.SteerableAgent;
import com.badlogic.gdx.math.Vector2;

public class MonsterController
{
    private final WorldController mc;
    private final MonsterModel model;
    private final MonsterView view;

    public MonsterController(WorldController mcpar, Vector2 position)
    {
        mc = mcpar;
        model = new MonsterModel(position);
        view = new MonsterView(model);

        addToWorld();
    }

    public void changeTarget(SteerableAgent target)
    {
        model.changeTarget(target);
    }

    private void addToWorld()
    {
        MonsterDtos.MonsterDto m = new MonsterDtos.MonsterDto(model, view);
        mc.getModel().addMob(m);
    }
}
