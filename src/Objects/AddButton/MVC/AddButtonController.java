package Objects.AddButton.MVC;

import DB.StringRes.MySettings;
import Objects.AddButton.DTO.ButtonDTOs;
import Objects.World.MVC.WorldController;
import PhysicalObjects.DynamicObject;
import PhysicalObjects.PhysicalObjectsFactory;
import com.badlogic.gdx.math.Vector2;

public class AddButtonController
{
    private WorldController worldController;

    public AddButtonController(WorldController worldController)
    {
        this.worldController = worldController;
    }

    public void createButton(int x, int y)
    {
        AddButtonModel model = createModel(new Vector2(x, y));
        AddButtonView view = createView(model);
        AddToWorld(model, view);
    }

    private AddButtonModel createModel(Vector2 position)
    {
        AddButtonModel model = new AddButtonModel((DynamicObject) PhysicalObjectsFactory.create(DynamicObject.class, worldController.getModel().getWorld(), MySettings.TILE_WIDTH, MySettings.TILE_HEIGHT));
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
        worldController.getModel().addMob(k);
    }


    public void buttonClicked()
    {
        worldController.addButtonClicked();
    }
}
