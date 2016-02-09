package Behaviors.Catalog.PathFinding.Graph;

import Behaviors.Catalog.PathFinding.Connection.Connection;
import com.badlogic.gdx.utils.Array;

public interface IGraph<N> {
    Array<Connection> getConnections (N fromNode);

    int getIndex (N node);
    int getNodeCount ();

    void init (int roomCount, int roomMinSize, int roomMaxSize, int squashIterations);
    N getNode (int x, int y);
}
