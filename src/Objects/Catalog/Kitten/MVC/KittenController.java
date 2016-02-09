package Objects.Catalog.Kitten.MVC;

import Objects.Catalog.Kitten.DTO.KittenDTOs;
import Objects.Catalog.World.MVC.WorldController;
import com.badlogic.gdx.math.Vector2;

public class KittenController
{
    private final WorldController mc;
    public final KittenModel model;
    private KittenView view;

    public KittenController(WorldController mcpar, Vector2 position)
    {
        mc = mcpar;
        model = new KittenModel(position);
        config();
    }

    public void config()
    {
        view = createView();
        addToWorld();
    }

    private KittenView createView()
    {
        return new KittenView(this, model);
    }

    private void addToWorld()
    {
        KittenDTOs.KittenDTO k = new KittenDTOs.KittenDTO(model, view);
        mc.getModel().addMob(k);
    }

    public void dragged(Vector2 clickPosition)
    {
        model.dragged(clickPosition);
    }

    public void pathCalculationRequest(Vector2 potentialGoal)
    {
        model.preCalculate(potentialGoal);
    }
}
