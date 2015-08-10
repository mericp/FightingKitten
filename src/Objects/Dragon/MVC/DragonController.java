package Objects.Dragon.MVC;

import Objects.Dragon.DragonDtos;
import Objects.World.MundoController;
import com.badlogic.gdx.math.Vector2;

public class DragonController {
    private MundoController mc;

    public DragonController(MundoController mcpar)
    {
        mc = mcpar;
    }

    public void createDragon(int x, int y)
    {
        DragonModel model = createModel(new Vector2(x, y));
        DragonView view = createView(model);
        AddToWorld(model, view);
    }

    private DragonModel createModel(Vector2 position)
    {
        DragonModel model = new DragonModel(mc.getModel().getMundo());
        model.setPosition(position.x, position.y);

        return model;
    }

    private DragonView createView(DragonModel model)
    {
        DragonView view = new DragonView(this, model, mc.getView().getRayHandler());
        model.addObserver(view);

        return view;
    }

    private void AddToWorld(DragonModel model, DragonView view)
    {
        DragonDtos.DragonDto k = new DragonDtos.DragonDto(model, view);
        mc.getModel().addMob(k);
    }


    public void buttonClicked()
    {
        mc.addButtonClicked();
    }
}
