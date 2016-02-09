package Behaviors.Catalog.PathFinding.Node;

import Behaviors.Catalog.PathFinding.Connection.Connection;
import com.badlogic.gdx.utils.BinaryHeap;

public class NodeRecord extends BinaryHeap.Node {
    public int searchId;

    public enum Category
    {
        UNVISITED,
        OPEN,
        CLOSED
    }

    public Category category;

    public Node node;

    public Connection connection;

    public float cost;

    public NodeRecord () {
        super(0);
    }

    public float getEstimatedTotalCost () {
        return getValue();
    }

    public float heuristic()
    {
        return getEstimatedTotalCost() - cost;
    }
}
