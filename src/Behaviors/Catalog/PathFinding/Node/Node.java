package Behaviors.Catalog.PathFinding.Node;

import Behaviors.Catalog.PathFinding.Connection.Connection;
import com.badlogic.gdx.utils.Array;

public abstract class Node {
    private final boolean collidable;
    private final int x;
    private final int y;
    private final Array<Connection> connections;

    public Node(int x, int y, boolean collidable, Array<Connection> connections) {
        this.collidable = collidable;
        this.x = x;
        this.y = y;
        this.connections = connections;
    }

    public abstract int getIndex ();

    public Array<Connection> getConnections () {
        return connections;
    }

    public int x(){ return x; }
    public int y(){ return y; }

    public boolean isCollidable()
    {
        return collidable;
    }
}