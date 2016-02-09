package Objects.Catalog.World.MVC;

import DB.StringRes.MySettings;
import Objects.Catalog.Kitten.MVC.KittenController;
import Objects.Catalog.Monster.MVC.MonsterController;
import Objects.Catalog.ObjectsFactory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WorldController implements PropertyChangeListener
{
    private static WorldController instance;
    private WorldModel worldModel;
    private WorldView worldView;

    public static WorldController get()
    {
        if(instance == null)
        {
            instance = new WorldController();
        }

        return instance;
    }

    private WorldController()
    {
        createStructure();
        setInputSources();

        drawDefaults();
    }

    private void createStructure()
    {
        worldModel = new WorldModel();
        worldView = new WorldView(this);

        worldModel.addObserver(worldView);
    }

    private void setInputSources()
    {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(worldView);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private void drawDefaults()
    {
        ObjectsFactory.COLLECTION.addbutton(this, new Vector2(0, 0));
        MonsterController monster = ObjectsFactory.COLLECTION.monster(this, new Vector2(500, 450));
        KittenController kitten = ObjectsFactory.COLLECTION.kitten(this, new Vector2(50, 150));

        monster.changeTarget(kitten.model);
    }

    public void addButtonClicked()
    {
        ObjectsFactory.COLLECTION.kitten(this, new Vector2(50, 450));
    }

    public void render(float delta)
    {
        Gdx.gl.glClearColor(0 / 2.55f, 0 / 2.55f, 0 / 2.55f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateAI(delta);
        updateView(delta);

        worldView.draw();
    }

    private void updateView(float delta)
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
