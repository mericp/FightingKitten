package Objects.Kitten.MVC;

import Objects.Kitten.DTO.KittenDTOs;
import Objects.World.MundoController;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class KittenController
{
    private MundoController mc;
    private World mundo;

    public KittenController(MundoController mcpar)
    {
        mc = mcpar;
        mundo = mcpar.getModel().getMundo();
    }

    public void createKitten(Vector2 position)
    {
        KittenModel model = createModel(position);
        KittenView view = createView(model);
        AddKittenToWorld(model, view);
    }

    private KittenModel createModel(Vector2 position)
    {
        KittenModel kittenModel = new KittenModel(mundo);
        kittenModel.setPosition(position.x, position.y);

        return kittenModel;
    }

    private KittenView createView(KittenModel model)
    {
        KittenView kittenView = new KittenView(this, model, mc.getView().getRayHandler());
        model.addObserver(kittenView);

        return kittenView;
    }

    private void AddKittenToWorld(KittenModel model, KittenView view)
    {
        KittenDTOs.KittenDTO k = new KittenDTOs.KittenDTO(model, view);
        mc.getModel().addMob(k);
    }

    public void KittenDragged(Vector2 clickPosition, KittenModel kittenModel)
    {
        kittenModel.dragged(clickPosition);
    }
}
