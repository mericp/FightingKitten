package Objects.Kitten.MVC;

import DB.StringRes.MySettings;
import Objects.Kitten.DTO.KittenDTOs;
import Objects.World.MVC.WorldController;
import PhysicalObjects.DynamicObject;
import PhysicalObjects.PhysicalObjectsFactory;
import PhysicalObjects.StaticObject;
import com.badlogic.gdx.math.Vector2;

public class KittenController
{
    private WorldController mc;

    public KittenController(WorldController mcpar)
    {
        mc = mcpar;
    }

    public void create(Vector2 position)
    {
        KittenModel model = createModel(position);
        KittenView view = createView(model);
        addToWorld(model, view);
    }

    private KittenModel createModel(Vector2 position)
    {
        KittenModel model = new KittenModel((DynamicObject) PhysicalObjectsFactory.create(DynamicObject.class, mc.getModel().getWorld(), MySettings.TILE_WIDTH, MySettings.TILE_HEIGHT), (StaticObject)PhysicalObjectsFactory.create(StaticObject.class, mc.getModel().getWorld(), 1, 1));
        model.setPosition(position.x, position.y);

        return model;
    }

    private KittenView createView(KittenModel model)
    {
        KittenView view = new KittenView(this, model, mc.getView().getRayHandler());
        model.addObserver(view);

        return view;
    }

    private void addToWorld(KittenModel model, KittenView view)
    {
        KittenDTOs.KittenDTO k = new KittenDTOs.KittenDTO(model, view);
        mc.getModel().addMob(k);
    }

    public void dragged(Vector2 clickPosition, KittenModel kittenModel)
    {
        kittenModel.dragged(clickPosition);
    }
}
