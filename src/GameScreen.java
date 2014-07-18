import DB.MySettings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen implements Screen
{
    private Mundo mundo;
    private float timeStep = 0;
    private float alphaTimeStep;

    private InputMultiplexer inputMultiplexer;


    public GameScreen()
    {
        mundo = new Mundo();

        //Set input sources for LibGdx.
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(mundo);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override public void show()
    {

    }

    @Override public void render(float delta)
    {
        Gdx.gl.glClearColor(0/2.55f, 0/2.55f, 0/2.55f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        timeStep += delta;

        while (timeStep >= MySettings.FIXED_TIMESTEP)
        {
            //Physics simulation
            this.mundo.getWorld().step(MySettings.FIXED_TIMESTEP, 8, 6);

            timeStep -= MySettings.FIXED_TIMESTEP;
        }

        alphaTimeStep = timeStep/MySettings.FIXED_TIMESTEP;

        //Render the last physics simulation.
        mundo.act(delta);
        mundo.draw();
    }

    @Override public void resize(int width, int height)
    {
        this.mundo.resize(width, height);
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
        this.mundo.dispose();
        MySettings.ATLAS_DAO.getAtlasDAO().dispose();
    }
}
