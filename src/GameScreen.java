import Objects.Catalog.World.MVC.WorldController;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen
{
    @Override public void render(float delta)
    {
        WorldController.get().render(delta);
    }
    @Override public void resize(int width, int height)
    {
        WorldController.get().resize();
    }
    @Override public void dispose()
    {
        WorldController.get().dispose();
    }
    @Override public void hide(){}
    @Override public void pause(){}
    @Override public void resume(){}
    @Override public void show(){}
}
