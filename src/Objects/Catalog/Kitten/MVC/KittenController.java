package Objects.Catalog.Kitten.MVC;

import Objects.Catalog.Kitten.DTO.KittenDTOs;
import Objects.Catalog.World.MVC.WorldController;
import com.badlogic.gdx.math.Vector2;

public class KittenController
{
    private final WorldController mc;
    public final KittenModel model;

    public KittenController(WorldController mcpar, Vector2 position)
    {
        mc = mcpar;
        model = new KittenModel(position);
        config();
    }

    public void config()
    {
        KittenView view = createView(model);
        addToWorld(model, view);
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
