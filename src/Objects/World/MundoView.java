package Objects.World;

import Objects.Base.BaseView.Nekomata;
import Background.Ground;
import DB.MySettings;
import DB.NotificationsDictionary;
import Objects.Base.BaseDto.MobDto;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class MundoView extends Stage implements PropertyChangeListener
{
    private final MundoController mundoController;
    private final MundoModel mundoModel;

    private final RayHandler rayHandler;
    private final OrthographicCamera camera;
    private final OrthographicCamera boxCamera;
    private final Box2DDebugRenderer worldRenderer;

    private final List<Nekomata> mobViewArray = new ArrayList<>();
    private final Ground battlefield;

    public MundoView(MundoController mundoController)
    {
        this.mundoController = mundoController;
        mundoModel = this.mundoController.getModel();

        RayHandler.useDiffuseLight(true);
        this.rayHandler = new RayHandler(mundoModel.getMundo());
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.boxCamera = new OrthographicCamera(Gdx.graphics.getWidth() * MySettings.PIXEL_METERS, Gdx.graphics.getHeight() * MySettings.PIXEL_METERS);
        this.worldRenderer = new Box2DDebugRenderer();

        this.rayHandler.setAmbientLight(0.2f, 0.2f, 0.2f, 0.5f);
        this.getViewport().setCamera(camera);

        battlefield = new Ground();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        String notification = evt.getPropertyName();

        switch (notification)
        {
            case NotificationsDictionary.MOB_ADDED:
                MobDto newMob = (MobDto)evt.getNewValue();
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

        //Box2dLights
        rayHandler.setCombinedMatrix(boxCamera.combined);
        rayHandler.updateAndRender();

        //Draws Box2d Hitboxes:
        worldRenderer.render(mundoModel.getMundo(), boxCamera.combined);
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
        rayHandler.dispose();
        mundoModel.getMundo().dispose();
        worldRenderer.dispose();
        battlefield.dispose();
    }

    public void addMobView(Nekomata newMobView)
    {
        mobViewArray.add(newMobView);
        addActor(newMobView);
    }

    public void resize(int width, int height)
    {
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public RayHandler getRayHandler()
    {
        return rayHandler;
    }
}
