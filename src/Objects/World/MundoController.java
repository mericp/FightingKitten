package Objects.World;

import DB.MySettings;
import Objects.AddButton.AddButtonController;
import Objects.Kitten.MVC.KittenController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MundoController implements PropertyChangeListener
{
    private MundoModel mundoModel;
    private MundoView mundoView;

    private float timeStep = 0;

    public MundoController()
    {
        createStructure();
        setInputSources();

        drawDefaults();
    }

    private void createStructure()
    {
        mundoModel = new MundoModel();
        mundoView = new MundoView(this);

        mundoModel.addObserver(mundoView);
        mundoModel.addObserver(this);
    }

    private void setInputSources()
    {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(mundoView);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private void drawDefaults()
    {
        KittenController kittenController = new KittenController(this);
        kittenController.createKitten(new Vector2(50, 450));

        AddButtonController addButtonController = new AddButtonController(this);
        addButtonController.createButton(50, 50);
    }

    public void addButtonClicked()
    {
        KittenController kittenController = new KittenController(this);
        kittenController.createKitten(new Vector2(50, 450));
    }

    public void render(float delta)
    {
        Gdx.gl.glClearColor(0/2.55f, 0/2.55f, 0/2.55f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        timeStep += delta;

        while (timeStep >= MySettings.FIXED_TIMESTEP)
        {
            //Physics simulation
            mundoModel.saveLastPosition(); //Save the last position of kittens' bodies.
            mundoModel.getMundo().step(MySettings.FIXED_TIMESTEP, 8, 6); //Simulate one step

            timeStep -= MySettings.FIXED_TIMESTEP;
        }

        float alphaTimeStep = timeStep/MySettings.FIXED_TIMESTEP; //Accumulate remainder.

        mundoModel.interpolatePositions(alphaTimeStep);

        //Render the last physics simulation.
        mundoView.act(delta);
        mundoView.draw();
    }

    public void resize(int width, int height)
    {
        mundoView.resize(width, height);
    }

    public void dispose()
    {
        mundoView.dispose();
        MySettings.ATLAS_DAO.getAtlasDAO().dispose();
    }

    public MundoModel getModel()
    {
        return mundoModel;
    }
    public MundoView getView()
    {
        return mundoView;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {}
}
