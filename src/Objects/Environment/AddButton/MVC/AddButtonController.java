package Objects.Environment.AddButton.MVC;

import Objects.Environment.AddButton.DTO.ButtonDTOs;
import Objects.World.MVC.WorldController;
import com.badlogic.gdx.math.Vector2;

public class AddButtonController
{
    private final WorldController worldController;

    public AddButtonController(WorldController worldController, Vector2 position)
    {
        this.worldController = worldController;
        config(position);
    }

    private void config(Vector2 position)
    {
        AddButtonModel model = newModel(position);
        AddToWorld(model, newView(model));
    }

    private AddButtonModel newModel(Vector2 position)
    {
        return new AddButtonModel(position);
    }
    private AddButtonView newView(AddButtonModel model)
    {
        return new AddButtonView(this, model);
    }

    private void AddToWorld(AddButtonModel model, AddButtonView view)
    {
        ButtonDTOs.ButtonDTO k = new ButtonDTOs.ButtonDTO(model, view);
        worldController.getModel().addMob(k);
    }

    public void buttonClicked()
    {
        worldController.addButtonClicked();
    }
}
