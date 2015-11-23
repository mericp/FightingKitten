package Objects.Kitten.MVC;

import Objects.Kitten.DTO.KittenDTOs;
import Objects.World.MVC.WorldController;
import com.badlogic.gdx.math.Vector2;

public class KittenController
{
    private WorldController mc;
    public KittenModel model;

    public KittenController(WorldController mcpar)
    {
        mc = mcpar;
    }

    public void create(Vector2 position)
    {
        createModel(position);
        KittenView view = createView(model);
        addToWorld(model, view);
    }

    private void createModel(Vector2 position)
    {
        model = new KittenModel(position);
    }

    private KittenView createView(KittenModel model)
    {
        return new KittenView(this, model);
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
