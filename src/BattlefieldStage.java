import Background.Ground;
import DB.MySettings;
import Entities.Kitten;
import Entities.WaypointListener;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class BattlefieldStage extends Stage
{
    //Model
    private World world;
    private Array<Kitten> kittenArray = new Array<>();

    //View
    private RayHandler rayHandler;
    private OrthographicCamera camera;
    private OrthographicCamera boxCamera;
    private Box2DDebugRenderer worldRenderer;
    private Ground battlefield;

    public BattlefieldStage()
    {
        RayHandler.useDiffuseLight(true);

        this.world = new World(new Vector2(0, 0), false);
        this.rayHandler = new RayHandler(world);
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.boxCamera = new OrthographicCamera(Gdx.graphics.getWidth() * MySettings.PIXEL_METERS, Gdx.graphics.getHeight() * MySettings.PIXEL_METERS);
        this.worldRenderer = new Box2DDebugRenderer();

        this.rayHandler.setAmbientLight(0.2f, 0.2f, 0.2f, 0.5f);
        this.getViewport().setCamera(camera);

        Kitten kitten = new Kitten(this.world, this.rayHandler);
        kitten.setModelPosition(50, 50);
        addKitten(kitten);

        kitten = new Kitten(this.world, this.rayHandler);
        kitten.setModelPosition(50, 450);
        addKitten(kitten);

        kitten = new Kitten(this.world, this.rayHandler);
        kitten.setModelPosition(50, 850);
        addKitten(kitten);

        kitten = new Kitten(this.world, this.rayHandler);
        kitten.setModelPosition(600, 450);
        addKitten(kitten);

        kitten = new Kitten(this.world, this.rayHandler);
        kitten.setModelPosition(1150, 50);
        addKitten(kitten);

        kitten = new Kitten(this.world, this.rayHandler);
        kitten.setModelPosition(1150, 450);
        addKitten(kitten);

        kitten = new Kitten(this.world, this.rayHandler);
        kitten.setModelPosition(1150, 850);
        addKitten(kitten);

        battlefield = new Ground();

        world.setContactListener(new WaypointListener());
    }

    public World getWorld()
    {
        return this.world;
    }

    public void addKitten(Kitten kitten)
    {
        kittenArray.add(kitten);
        this.addActor(kitten);
    }

    public void removeKitten(Kitten kitten)
    {
        kittenArray.removeValue(kitten, true);
        this.getRoot().removeActor(kitten);
    }

    public void resize(int width, int height)
    {
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    private void updateKittens()
    {
        for(Kitten kitten : kittenArray)
        {
            kitten.updateView();
        }
    }

    public void saveLastPosition()
    {
        for(Kitten kitten : kittenArray)
        {
            kitten.saveLastPosition();
        }
    }

    public void interpolatePositions(float alpha)
    {
        for(Kitten kitten : kittenArray)
        {
            kitten.interpolatePositions(alpha);
        }
    }

    public void draw()
    {
       this.updateKittens();

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
        worldRenderer.render(world, boxCamera.combined);
    }

    public RayHandler getRayHandler()
    {
        return this.rayHandler;
    }

    @Override public void dispose()
    {
        rayHandler.dispose();
        world.dispose();
        worldRenderer.dispose();
        battlefield.dispose();
    }
}
