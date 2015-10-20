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

    public void create(Vector2 position)
    {
        KittenModel model = createModel(position);
        KittenView view = createView(model);
        addToWorld(model, view);
    }

    private KittenModel createModel(Vector2 position)
    {
        KittenModel model = new KittenModel(mundo);
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
