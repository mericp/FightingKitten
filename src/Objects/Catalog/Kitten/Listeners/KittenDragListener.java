package Objects.Catalog.Kitten.Listeners;

import Objects.Catalog.Kitten.MVC.KittenView;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class KittenDragListener extends DragListener
{
    private final KittenView kittenView;
    private Vector2 lastPosition;

    public KittenDragListener(KittenView kittenView)
    {
        this.kittenView = kittenView;
        lastPosition = new Vector2(this.kittenView.getX(), this.kittenView.getY());
        setTapSquareSize(32);
    }

    @Override
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
    {
        return  true;
    }

    @Override
    public void touchDragged (InputEvent event, float x, float y, int pointer)
    {
        Vector2 goal = new Vector2(event.getStageX(), event.getStageY());

        if (changedPosition(goal) && significantDrag(goal))
        {
            kittenView.showPath(goal);
            lastPosition = goal;
        }
    }

    private boolean significantDrag(Vector2 goal)
    {
        return Math.abs(goal.x - lastPosition.x) > 1 || Math.abs(goal.y - lastPosition.y) > 1;
    }

    private boolean changedPosition(Vector2 goal)
    {
        return lastPosition != goal;
    }

    @Override
    public void touchUp (InputEvent event, float x, float y, int pointer, int button)
    {
        Vector2 clickPosition = new Vector2(x, y);
        event.getListenerActor().localToStageCoordinates(clickPosition);
        kittenView.dragged(clickPosition);
    }
}
