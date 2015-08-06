package DB.Atlas;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

public interface IAtlasDAO extends Disposable
{
    TextureAtlas getAtlas();
    TextureRegion getTexture(String texturePath);
}
