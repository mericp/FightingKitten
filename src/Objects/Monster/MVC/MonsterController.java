package Objects.Monster.MVC;

import DB.StringRes.MySettings;
import Objects.Monster.DTO.MonsterDtos;
import Objects.World.MVC.WorldController;
import PhysicalObjects.DynamicObject;
import PhysicalObjects.PhysicalObjectsFactory;
import com.badlogic.gdx.math.Vector2;

public class MonsterController {
    private WorldController mc;

    public MonsterController(WorldController mcpar)
    {
        mc = mcpar;
    }

    public void create(Vector2 position)
    {
        MonsterModel model = createModel(position);
        MonsterView view = createView(model);
        addToWorld(model, view);
    }

    private MonsterModel createModel(Vector2 position)
    {
        MonsterModel model = new MonsterModel((DynamicObject) PhysicalObjectsFactory.create(DynamicObject.class, mc.getModel().getWorld(), MySettings.TILE_WIDTH, MySettings.TILE_HEIGHT));
        model.setPosition(position);

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
