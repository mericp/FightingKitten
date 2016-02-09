package Objects.Catalog.World.MVC;

import Objects.Base.MobDTO;
import Objects.Base.Nekomata;
import Objects.Catalog.World.Map.MVC.Map;
import DB.StringRes.MySettings;
import DB.StringRes.NotificationsDictionary;
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

    public WorldView(WorldController mundoController)
    {
        worldModel = mundoController.getModel();

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        boxCamera = new OrthographicCamera(Gdx.graphics.getWidth() * MySettings.PIXEL_METERS, Gdx.graphics.getHeight() * MySettings.PIXEL_METERS);
        worldRenderer = new Box2DDebugRenderer();

        getViewport().setCamera(camera);
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

        camera.position.x = MySettings.SCREEN_WIDTH - 640;
        camera.position.y = MySettings.SCREEN_HEIGHT - 320;
        camera.update();

        boxCamera.position.x = 600 * MySettings.PIXEL_METERS;
        boxCamera.position.y = 450 * MySettings.PIXEL_METERS;
        boxCamera.update();

        Map.get().view.setCamera(camera);
        Map.get().view.render();

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
        Map.get().dispose();
    }

    private void addMobView(Nekomata newMobView)
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
