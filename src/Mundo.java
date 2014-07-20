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

public class Mundo extends Stage
{
    //Model
    private World world;
    private Array<Kitten> kittenArray = new Array<>();

    //View
    private RayHandler rayHandler;
    private OrthographicCamera camera;
    private OrthographicCamera boxCamera;
    private Box2DDebugRenderer worldRenderer;

    public Mundo()
    {
        RayHandler.useDiffuseLight(true);
        this.world = new World(new Vector2(0, 0), false);
        this.rayHandler = new RayHandler(world);
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.boxCamera = new OrthographicCamera(Gdx.graphics.getWidth() * MySettings.PIXEL_METTERS, Gdx.graphics.getHeight() * MySettings.PIXEL_METTERS);
        this.worldRenderer = new Box2DDebugRenderer();

        this.rayHandler.setAmbientLight(0.4f, 0.4f, 0.4f, 1.0f);
        this.getViewport().setCamera(camera);

        Kitten kitten = new Kitten(this.world);
        kitten.setModelPosition(0, 0);
        addKitten(kitten);
        

        kitten = new Kitten(this.world);
        kitten.setModelPosition(50, 0);
        addKitten(kitten);

        kitten = new Kitten(this.world);
        kitten.setModelPosition(100, 0);
        addKitten(kitten);

        kitten = new Kitten(this.world);
        kitten.setModelPosition(150, 0);
        addKitten(kitten);

        kitten = new Kitten(this.world);
        kitten.setModelPosition(200, 0);
        addKitten(kitten);

        kitten = new Kitten(this.world);
        kitten.setModelPosition(250, 0);
        addKitten(kitten);

        kitten = new Kitten(this.world);
        kitten.setModelPosition(300, 0);
        addKitten(kitten);


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

    public void draw()
    {
       this.updateKittens();

        camera.position.x = 0;
        camera.position.y = 0;
        camera.update();

        boxCamera.position.x = 0 * MySettings.PIXEL_METTERS;
        boxCamera.position.y = 0 * MySettings.PIXEL_METTERS;
        boxCamera.update();

        super.draw();

        //Box2dLights: (iluminacion)
        rayHandler.setCombinedMatrix(boxCamera.combined);
        rayHandler.updateAndRender();

        //Draws Box2d Hitboxes:
        worldRenderer.render(world, boxCamera.combined);
    }

    @Override public void dispose()
    {
        rayHandler.dispose();
        world.dispose();
        worldRenderer.dispose();
    }
}
