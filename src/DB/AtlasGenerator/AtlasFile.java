package DB.AtlasGenerator;

import DB.StringRes.MySettings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AtlasFile
{
    public TextureAtlas atlas;

    private static class Singleton
    {
        private static final AtlasFile get = new AtlasFile();
    }

    private AtlasFile()
    {
        this.getAltasFile();
    }

    public static AtlasFile getUniqueInstance()
    {
        return Singleton.get;
    }

    private void getAltasFile()
    {
        atlas = new TextureAtlas(Gdx.files.internal(MySettings.ATLAS_FOLDER + MySettings.ATLAS_NAME + MySettings.ATLAS_EXTENSION));
    }
}
