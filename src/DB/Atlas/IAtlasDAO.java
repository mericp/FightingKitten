package DB.Atlas;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

public interface IAtlasDAO extends Disposable
{
    public TextureAtlas getAtlas();
    public TextureRegion getTexture(String texturePath);
}
