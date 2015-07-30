import Objects.World.MundoController;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen
{
    private final MundoController mundoController;

    public GameScreen()
    {
        mundoController = new MundoController();
    }

    @Override
    public void render(float delta)
    {
        mundoController.render(delta);
    }

    @Override
    public void resize(int width, int height)
    {
        mundoController.resize(width, height);
    }

    @Override
    public void dispose()
    {
        mundoController.dispose();
    }

    @Override public void hide(){}
    @Override public void pause(){}
    @Override public void resume(){}
    @Override public void show(){}
}
