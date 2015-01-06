package Controllers;

import DTOs.KittenDTOs;
import Models.KittenModel;
import Views.KittenView;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class KittenController
{
    private MundoController mc;
    private World mundo;

    public KittenController(MundoController mc)
    {
        mundo = mc.getMundoModel().getMundo();
    }

    public KittenDTOs.KittenDTO createKitten(Vector2 position)
    {
        KittenModel kittenModel = new KittenModel(mundo);
        kittenModel.setPosition(position.x, position.y);

        KittenView kittenView = new KittenView(this, kittenModel, mc.getMundoView().getRayHandler());
        kittenModel.addObserver(kittenView);

        return new KittenDTOs.KittenDTO(kittenModel, kittenView);

        //Mundoview
        //kittenViewArray.add(newKittenView);
        //addActor(newKittenView);
    }

    public void KittenDragged(Vector2 clickPosition, KittenModel kittenModel)
    {
        kittenModel.dragged(clickPosition);
    }
}
