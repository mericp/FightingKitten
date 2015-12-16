package DB.AtlasGenerator;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AtlasFileDAO implements IAtlasDAO
{
    private final TextureAtlas atlas;

    public AtlasFileDAO()
    {
        this.atlas = AtlasFile.getUniqueInstance().atlas;
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
    }
}
