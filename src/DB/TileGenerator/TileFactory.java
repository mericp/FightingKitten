package DB.TileGenerator;

import DB.StringRes.MySettings;
import com.badlogic.gdx.math.Vector2;

public enum TileFactory {
    COLLECTION()
    {
        @Override public Tile grass(Vector2 position)
        {
            return new Tile(position, MySettings.ATLAS_DAO.getAtlasDAO().getTexture(MySettings.GROUND), false);
        }

        @Override public Tile wall(Vector2 position)
        {
            return new Tile(position, MySettings.ATLAS_DAO.getAtlasDAO().getTexture(MySettings.WALL), true);
        }
    };

    public abstract Tile grass(Vector2 position);
    public abstract Tile wall(Vector2 position);

    TileFactory() {}
}
