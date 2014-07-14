import DB.MySettings;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class Main extends Game
{
    public static void main (String[] arg)
    {
        if(MySettings.DEV_MODE)
        {
            TexturePacker.process(MySettings.IMAGES_FOLDER, MySettings.ATLAS_FOLDER, MySettings.ATLAS_NAME);
        }

        System.setProperty("user.name", "Mery");

        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Fighting Kitten";
        cfg.fullscreen = MySettings.FULL_SCREEN;
        cfg.vSyncEnabled = MySettings.V_SYNC_ENABLED;
        cfg.foregroundFPS = 5000;
        cfg.width = MySettings.SCREEN_WIDTH;
        cfg.height = MySettings.SCREEN_HEIGHT;

        new LwjglApplication(new Main(), cfg);
    }

    @Override public void create()
    {
        this.setScreen(new GameScreen());
    }
}
