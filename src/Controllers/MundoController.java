package Controllers;

import DB.MySettings;
import DB.NotificationsDictionary;
import DTOs.KittenDTOs;
import DTOs.MundoDTOs;
import Models.KittenModel;
import Models.MundoModel;
import Views.MundoView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MundoController implements PropertyChangeListener
{
    private final MundoModel mundoModel;
    private final MundoView mundoView;

    public KittenController kittenController;

    private float timeStep = 0;

    public MundoController()
    {
        mundoModel = new MundoModel();
        mundoView = new MundoView(this);

        mundoModel.addObserver(mundoView);
        mundoModel.addObserver(this);

        //Put some kitten in the world:
        kittenController = new KittenController(this);
        kittenController.createKitten(new Vector2(50, 50));
        kittenController.createKitten(new Vector2(50, 450));

        //Set input sources for LibGdx.
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(mundoView);
        Gdx.input.setInputProcessor(inputMultiplexer);
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

    public MundoModel getMundoModel()
    {
        return this.mundoModel;
    }
    public MundoView getMundoView()
    {
        return this.mundoView;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {}
}
