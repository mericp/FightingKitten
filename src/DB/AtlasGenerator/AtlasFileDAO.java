package DB.AtlasGenerator;

import ch.qos.logback.classic.Logger;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.slf4j.LoggerFactory;

public class AtlasFileDAO implements IAtlasDAO
{
    private final TextureAtlas atlas;
    private final Logger logger;

    public AtlasFileDAO()
    {
        this.atlas = AtlasFile.getUniqueInstance().atlas;
        this.logger = (Logger) LoggerFactory.getLogger(this.getClass());
    }

    @Override
    public TextureAtlas getAtlas()
    {
        return this.atlas;
    }

    @Override
    public TextureRegion getTexture(String texturePath)
    {
        return (new TextureRegion(this.atlas.findRegion(texturePath)));
    }

    @Override
    public void dispose()
    {
        this.atlas.dispose();
        this.logger.trace("DISPOSE: Dispose atlas.");
    }
}
