package Behaviors.Catalog.PathFinding.Connection;

import Behaviors.Catalog.PathFinding.Node.Node;
import Objects.Catalog.World.Map.MVC.Map;
import com.badlogic.gdx.ai.pfa.DefaultConnection;

public class Connection extends DefaultConnection<Node> {
    private final float COST = (float)Math.sqrt(2);

    public Connection(Node startNode, Node endNode) {
        super(startNode, endNode);
    }

    @Override
    public float getCost () {
        if(getToNode().x() != Map.get().graph.startNode.x() && getToNode().y() != Map.get().graph.startNode.y())
        {
            return COST;
        }
        else
        {
            return 1;
        }
    }
}
