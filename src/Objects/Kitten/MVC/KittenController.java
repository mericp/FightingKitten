package Objects.Kitten.MVC;

import Objects.Kitten.DTO.KittenDTOs;
import Objects.World.MVC.WorldController;
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
        return new KittenModel(position);
    }

    private KittenView createView(KittenModel model)
    {
        KittenView view = new KittenView(this, model);
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
