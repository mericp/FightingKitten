package Behaviors.Catalog.PathFinding.Algorithm;

import Behaviors.Catalog.PathFinding.Graph.IGraph;
import Behaviors.Catalog.PathFinding.Node.Node;
import Behaviors.Catalog.PathFinding.Connection.Connection;
import Behaviors.Catalog.PathFinding.Node.NodeRecord;
import com.badlogic.gdx.ai.pfa.*;
import com.badlogic.gdx.ai.pfa.PathFinder;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BinaryHeap;

public class IndexedAStarPathFinder implements PathFinder<Node> {
    private final IGraph graph;
    private final NodeRecord[] nodeRecords;
    private final BinaryHeap<NodeRecord> openList;
    private NodeRecord current;

    private int searchId; //The unique ID for each search run. Used to mark nodes.

    public IndexedAStarPathFinder (IGraph graph) {
        this.graph = graph;
        nodeRecords = new NodeRecord[graph.getNodeCount()];
        openList = new BinaryHeap<>();
    }

    @Override
    public boolean searchNodePath(Node start, Node goal, Heuristic<Node> heuristic, GraphPath<Node> outPath) {
        PerformAStar(start, goal, heuristic);

        if (goalIsUnreachable(goal))
        {
            return false;
        }
        else
        {
            generateNodePath(start, outPath);
            return true;
        }
    }

    private void PerformAStar (Node start, Node goal, Heuristic<Node> heuristic) {
        initSearch(start, goal, heuristic);

        do
        {
            retrieveSmallestCostNode();

            if (goalReached(goal)) return;

            visitChildren(goal, heuristic);
        }
        while (openList.size > 0);
    }

    private boolean goalIsUnreachable(Node endNode)
    {
        return current.node != endNode;
    }

    //region init
    protected void initSearch (Node start, Node goal, Heuristic<Node> heuristic) {
        incrementSearchId();
        initializeNodeList();
        initializeStartNode(start, goal, heuristic);
        initCurrentNode();
    }

    private void incrementSearchId()
    {
        if (++searchId < 0) searchId = 1;
    }
    private void initializeNodeList()
    {
        openList.clear();
    }
    private void initializeStartNode(Node start, Node goal, Heuristic<Node> heuristic)
    {
        NodeRecord startRecord = getNodeRecord(start);
        startRecord.node = start;
        startRecord.connection = null;
        startRecord.cost = 0;
        addToNodeList(startRecord, heuristic.estimate(start, goal));
    }
    private void initCurrentNode()
    {
        current = null;
    }
    //endregion

    private void retrieveSmallestCostNode()
    {
        current = openList.pop();
        current.category = NodeRecord.Category.CLOSED;
    }

    private boolean goalReached(Node endNode)
    {
        return current.node == endNode;
    }

    private void visitChildren (Node endNode, Heuristic<Node> heuristic) {
        for(Connection connection : currentNodeOutgoingConnections())
        {
            Node node = connection.getToNode();
            float cost = current.cost + connection.getCost();
            float nodeHeuristic;
            NodeRecord nodeRecord = getNodeRecord(node);

            if (nodeRecord.category == NodeRecord.Category.CLOSED)
            {
                if (noShorterRoute(nodeRecord, cost)) continue;

                nodeHeuristic = nodeRecord.heuristic();
            }
            else if (nodeRecord.category == NodeRecord.Category.OPEN)
            {
                if (noShorterRoute(nodeRecord, cost)) continue;

                // Remove it from the open list (it will be re-added with the new cost)
                openList.remove(nodeRecord);
                nodeHeuristic = nodeRecord.heuristic();
            }
            else // the node is unvisited
            {
                nodeHeuristic = heuristic.estimate(node, endNode);
            }

            // Update node record's cost and connection
            nodeRecord.cost = cost;
            nodeRecord.connection = connection;

            // Add it to the node list with the estimated total cost
            addToNodeList(nodeRecord, cost + nodeHeuristic);
        }
    }

    private boolean noShorterRoute(NodeRecord nodeRecord, float cost)
    {
        return nodeRecord.cost <= cost;
    }

    private Array<Connection> currentNodeOutgoingConnections()
    {
        return graph.getConnections(current.node);
    }

    private void generateNodePath (Node start, GraphPath<Node> outPath) {
        // Work back along the path, accumulating nodes
        while (current.connection != null)
        {
            outPath.add(current.node);
            current = nodeRecords[graph.getIndex(current.connection.getFromNode())];
        }

        outPath.add(start);

        // Reverse the path
        outPath.reverse();
    }

    private void addToNodeList (NodeRecord nodeRecord, float estimatedTotalCost) {
        openList.add(nodeRecord, estimatedTotalCost);
        nodeRecord.category = NodeRecord.Category.OPEN;
    }

    private NodeRecord getNodeRecord (Node node) {
        int index = graph.getIndex(node);
        NodeRecord nr = nodeRecords[index];

        if (nr != null)
        {
            if (nr.searchId != searchId)
            {
                nr.category = NodeRecord.Category.UNVISITED;
                nr.searchId = searchId;
            }

            return nr;
        }

        nr = nodeRecords[index] = new NodeRecord();
        nr.node = node;
        nr.searchId = searchId;

        return nr;
    }

    @Override public boolean searchConnectionPath(Node node, Node n1, Heuristic<Node> heuristic, GraphPath<com.badlogic.gdx.ai.pfa.Connection<Node>> graphPath) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    @Override public boolean search (PathFinderRequest<Node> request, long timeToRun) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
