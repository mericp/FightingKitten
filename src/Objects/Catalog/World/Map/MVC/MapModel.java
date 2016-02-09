package Objects.Catalog.World.Map.MVC;

import Behaviors.Catalog.PathFinding.Connection.Connection;
import Behaviors.Catalog.PathFinding.Graph.IGraph;
import Behaviors.Catalog.PathFinding.Node.Node;
import Objects.Catalog.World.Map.Tiles.TileController;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class MapModel implements IGraph<Node> {
    public final int width;
    public final int height;
    protected final Array<Node> nodes;
    public Node startNode;

    public MapModel(TileController[][] grid)
    {
        width = grid.length;
        height = grid[0].length;
        startNode = null;
        nodes = copiedNodes(grid);

        initGraph();
    }

    private Array<Node> copiedNodes(TileController[][] grid)
    {
        Array<Node> nodes = new Array<>(width * height);

        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                nodes.add(grid[x][y].getModel());
            }
        }

        return nodes;
    }

    private void initGraph()
    {
        int roomCount = MathUtils.random(height, width);
        int roomMinSize = 1;
        int roomMaxSize = 4;
        int squashIterations = 100;

        init(roomCount, roomMinSize, roomMaxSize, squashIterations);
    }

    @Override
    public void init(int roomCount, int roomMinSize, int roomMaxSize, int squashIterations) {
        // Each node has up to 4 neighbors, therefore no diagonal movement is possible
        for (int x = 0; x < width; x++)
        {
            int idx = x * height;

            for (int y = 0; y < height; y++)
            {
                Node n = nodes.get(idx + y);

                if (x > 0) addConnection(n, -1, 0);
                if (y > 0) addConnection(n, 0, -1);
                if (x < width - 1) addConnection(n, 1, 0);
                if (y < height - 1) addConnection(n, 0, 1);
            }
        }
    }

    @Override
    public Node getNode (int x, int y) {
        return nodes.get(x * height + y);
    }

    @Override
    public Array<Connection> getConnections(Node fromNode) {
        return fromNode.getConnections();
    }

    @Override
    public int getIndex(Node node) {
        return node.getIndex();
    }

    @Override
    public int getNodeCount () {
        return nodes.size;
    }

    private void addConnection (Node n, int xOffset, int yOffset) {
        Node target = getNode(n.x() + xOffset, n.y() + yOffset);

        if (!target.isCollidable())
        {
            n.getConnections().add(new Connection(n, target));
        }
    }
}
