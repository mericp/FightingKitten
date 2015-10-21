package Objects.World.MVC;

import DB.StringRes.MySettings;
import Objects.AddButton.MVC.AddButtonController;
import Objects.Kitten.MVC.KittenController;
import Objects.Monster.MVC.MonsterController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WorldController implements PropertyChangeListener
{
    private WorldModel worldModel;
    private WorldView worldView;

    public WorldController()
    {
        JOptionPane loading = new JOptionPane("Loading...");
        loading.show();

        createStructure();
        setInputSources();

        drawDefaults();

        loading.hide();
    }

    private void createStructure()
    {
        worldModel = new WorldModel();
        worldView = new WorldView(this);

        worldModel.addObserver(worldView);
        worldModel.addObserver(this);
    }

    private void setInputSources()
    {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(worldView);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private void drawDefaults()
    {
        AddButtonController addButtonController = new AddButtonController(this);
        addButtonController.createButton(10, 135);

        KittenController kittenController = new KittenController(this);
        kittenController.create(new Vector2(50, 450));

        MonsterController monsterController = new MonsterController(this);
        monsterController.create(new Vector2(1100, 450));
    }

    public void addButtonClicked()
    {
        KittenController kittenController = new KittenController(this);
        kittenController.create(new Vector2(50, 450));
    }

    public void render(float delta)
    {
        Gdx.gl.glClearColor(0 / 2.55f, 0 / 2.55f, 0 / 2.55f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateAI(delta);
        updatePhysics(delta);

        worldView.draw();
    }

    private void updatePhysics(float delta)
    {
        worldView.act(delta);
    }

    private void updateAI(float delta)
    {
        worldModel.updateAI(delta);
    }

    public void resize()
    {
        worldView.resize();
    }

    public void dispose()
    {
        worldView.dispose();
        MySettings.ATLAS_DAO.getAtlasDAO().dispose();
    }

    public WorldModel getModel()
    {
        return worldModel;
    }
    public WorldView getView()
    {
        return worldView;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {}
}
