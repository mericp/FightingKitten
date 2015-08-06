package Listeners;

import Objects.Kitten.MVC.KittenView;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class KittenClickedListener extends ClickListener
{
    private final KittenView kittenView;

    public KittenClickedListener(KittenView kittenView)
    {
        this.kittenView = kittenView;
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
    {
        kittenView.mousehover();
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
    {
        kittenView.mouseleft();
    }
}
