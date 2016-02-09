package Objects.Catalog.World.Map.Tiles;

import Behaviors.Catalog.PathFinding.Connection.Connection;
import Objects.Catalog.World.Map.MVC.Map;
import Behaviors.Catalog.PathFinding.Node.Node;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class TileModel extends Node {
    public TileModel(int x, int y, boolean collidable, int connectionCapacity)
    {
        super(x, y, collidable, new Array<Connection>(connectionCapacity));
    }

    public TileModel(Vector2 position, boolean collidable, int connectionCapacity)
    {
        this((int) position.x, (int) position.y, collidable, connectionCapacity);
    }

    @Override
    public int getIndex () {
        return x() * Map.get().graph.height + y();
    }
}
