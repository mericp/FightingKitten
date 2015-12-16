import Objects.Catalog.ObjectsFactory;
import Objects.Catalog.World.MVC.WorldController;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen
{
    private final WorldController worldController;

    public GameScreen()
    {
        worldController = ObjectsFactory.COLLECTION.world();
    }

    @Override public void render(float delta)
    {
        worldController.render(delta);
    }
    @Override public void resize(int width, int height)
    {
        worldController.resize();
    }
    @Override public void dispose()
    {
        worldController.dispose();
    }
    @Override public void hide(){}
    @Override public void pause(){}
    @Override public void resume(){}
    @Override public void show(){}
}
