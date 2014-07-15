
import Actores.Nekomata;
import DB.MySettings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen implements Screen
{
    protected OrthographicCamera camera;
    protected Stage battlefield;

    public GameScreen()
    {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        battlefield = new Stage();
        battlefield.getViewport().setCamera(camera);

        TextureRegion texture = MySettings.ATLAS_DAO.getAtlasDAO().getTexture("gatito");
        Nekomata gatito = new Nekomata(texture, 8, 12, 3, 0.20f);

        gatito.setPosition(400,400);
        battlefield.addActor(gatito);
    }

    @Override public void show()
    {

    }

    @Override public void render(float delta)
    {
        Gdx.gl.glClearColor(0/2.55f, 0/2.55f, 0/2.55f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        battlefield.act(delta);
        battlefield.draw();
    }

    @Override public void resize(int width, int height)
    {
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        battlefield.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override public void hide()
    {

    }

    @Override public void pause()
    {

    }

    @Override public void resume()
    {

    }

    @Override public void dispose()
    {
        MySettings.ATLAS_DAO.getAtlasDAO().dispose();
    }
}
