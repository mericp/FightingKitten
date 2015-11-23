package Objects.World.MVC;

import Objects.Base.BaseView.Nekomata;
import Objects.Environment.Grass;
import DB.StringRes.MySettings;
import DB.StringRes.NotificationsDictionary;
import Objects.Base.BaseDTO.MobDTO;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class WorldView extends Stage implements PropertyChangeListener
{
    private final WorldModel worldModel;

    private final OrthographicCamera camera;
    private final OrthographicCamera boxCamera;
    private final Box2DDebugRenderer worldRenderer;

    private final List<Nekomata> mobViewArray = new ArrayList<>();
    private final Grass battlefield;

    public WorldView(WorldController mundoController)
    {
        worldModel = mundoController.getModel();

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        boxCamera = new OrthographicCamera(Gdx.graphics.getWidth() * MySettings.PIXEL_METERS, Gdx.graphics.getHeight() * MySettings.PIXEL_METERS);
        worldRenderer = new Box2DDebugRenderer();

        getViewport().setCamera(camera);

        battlefield = new Grass();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        String notification = evt.getPropertyName();

        switch (notification)
        {
            case NotificationsDictionary.MOB_ADDED:
                MobDTO newMob = (MobDTO)evt.getNewValue();
                addMobView(newMob.view);
                break;

        }
    }

    @Override
    public void draw()
    {
        updateMobsView();

        camera.position.x = 600;
        camera.position.y = 450;
        camera.update();

        boxCamera.position.x = 600 * MySettings.PIXEL_METERS;
        boxCamera.position.y = 450 * MySettings.PIXEL_METERS;
        boxCamera.update();


        battlefield.setView(camera);
        battlefield.render();

        super.draw();

        worldRenderer.render(worldModel.getWorld(), boxCamera.combined);
    }

    private void updateMobsView()
    {
        for(Nekomata mobView : mobViewArray)
        {
            mobView.updateAnimation();
        }
    }

    @Override
    public void dispose()
    {
        worldModel.getWorld().dispose();
        worldRenderer.dispose();
        battlefield.dispose();
    }

    public void addMobView(Nekomata newMobView)
    {
        mobViewArray.add(newMobView);
        addActor(newMobView);
    }

    public void resize()
    {
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
}
