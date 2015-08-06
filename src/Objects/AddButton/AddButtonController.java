package Objects.AddButton;

import Objects.World.MundoController;
import com.badlogic.gdx.math.Vector2;

public class AddButtonController
{
    private MundoController mc;

    public AddButtonController(MundoController mcpar)
    {
        mc = mcpar;
    }

    public void createButton(int x, int y)
    {
        AddButtonModel model = createModel(new Vector2(x, y));
        AddButtonView view = createView(model);
        AddToWorld(model, view);
    }

    private AddButtonModel createModel(Vector2 position)
    {
        AddButtonModel model = new AddButtonModel(mc.getModel().getMundo());
        model.setPosition(position.x, position.y);

        return model;
    }

    private AddButtonView createView(AddButtonModel model)
    {
        AddButtonView view = new AddButtonView(this, model);
        model.addObserver(view);

        return view;
    }

    private void AddToWorld(AddButtonModel model, AddButtonView view)
    {
        ButtonDTOs.ButtonDTO k = new ButtonDTOs.ButtonDTO(model, view);
        mc.getModel().addMob(k);
    }


    public void buttonClicked()
    {
        mc.addButtonClicked();
    }
}
