package Entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class KittenDragListener extends DragListener
{
    private Kitten kitten;

    public KittenDragListener(Kitten kitten)
    {
        this.kitten = kitten;
    }

    @Override public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
    {
        return  true;
    }

    @Override public void touchDragged (InputEvent event, float x, float y, int pointer)
    {

    }

    @Override public void touchUp (InputEvent event, float x, float y, int pointer, int button)
    {
        Vector2 clickPosition = new Vector2(x, y);
        event.getListenerActor().localToStageCoordinates(clickPosition);
        kitten.setDestino(clickPosition);
    }
}
