package Objects.Environment.AddButton.Listeners;

import Objects.Environment.AddButton.MVC.AddButtonView;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class AddButtonClickedListener extends ClickListener {
    private final AddButtonView view;

    public AddButtonClickedListener(AddButtonView v)
    {
        view = v;
    }

    @Override public void clicked(InputEvent event, float x, float y)
    {
        view.clicked();
    }
}
