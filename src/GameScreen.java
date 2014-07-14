
import DB.MySettings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen implements Screen
{
    protected OrthographicCamera camara;
    protected Stage sMundo;



    public GameScreen()
    {
        camara = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sMundo = new Stage();
        sMundo.getViewport().setCamera(camara);


    }



    @Override public void show()
    {

    }

    @Override public void render(float delta)
    {
        Gdx.gl.glClearColor(0/2.55f, 0/2.55f, 0/2.55f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sMundo.act(delta);
        sMundo.draw();
    }

    @Override public void resize(int width, int height)
    {
        camara.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sMundo.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
