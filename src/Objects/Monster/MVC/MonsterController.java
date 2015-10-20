package Objects.Monster.MVC;

import Objects.Monster.MonsterDtos;
import Objects.World.MundoController;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class MonsterController {
    private MundoController mc;
    private World mundo;

    public MonsterController(MundoController mcpar)
    {
        mc = mcpar;
        mundo = mcpar.getModel().getMundo();
    }

    public void create(Vector2 position)
    {
        MonsterModel model = createModel(position);
        MonsterView view = createView(model);
        addToWorld(model, view);
    }

    private MonsterModel createModel(Vector2 position)
    {
        MonsterModel model = new MonsterModel(mundo);
        model.setPosition(position.x, position.y);

        return model;
    }

    private MonsterView createView(MonsterModel model)
    {
        MonsterView view = new MonsterView(this, model, mc.getView().getRayHandler());
        model.addObserver(view);

        return view;
    }

    private void addToWorld(MonsterModel model, MonsterView view)
    {
        MonsterDtos.MonsterDto m = new MonsterDtos.MonsterDto(model, view);
        mc.getModel().addMob(m);
    }
}
