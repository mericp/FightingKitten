package Behaviors.Catalog.PathFinding.Algorithm;

import Behaviors.Catalog.PathFinding.Node.Node;
import DB.StringRes.MySettings;
import Objects.Catalog.World.Map.MVC.Map;
import Objects.Catalog.World.Map.MVC.MapModel;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.math.Vector2;

public class PathFinder {
    private int endNodeX;
    private int endNodeY;

    private DefaultGraphPath<Node> path;
    private ManhattanDistance<Node> heuristic;

    public PathFinder()
    {
        endNodeX = -1;
        endNodeY = -1;

        path = new DefaultGraphPath<>();
        heuristic = new ManhattanDistance<>();
    }

    public void updatePath (Vector2 start, Vector2 goal) {
        if (notLastNode(goal))
        {
            Node startNode = startNode(start);
            Node goalNode = endNode(goal);

            searchNodePath(startNode, goalNode);
        }
    }

    private Node startNode(Vector2 start)
    {
        Node startNode = graph().getNode(parseToNodes(start.x), parseToNodes(start.y));

        graph().startNode = startNode;

        return startNode;
    }

    private Node endNode(Vector2 goal)
    {
        int goalX = parseToNodes(goal.x);
        int goalY = parseToNodes(goal.y);

        Node goalNode = graph().getNode(goalX, goalY);

        if (!goalNode.isCollidable())
        {
            endNodeX = goalX;
            endNodeY = goalY;
        }
        else
        {
            goalNode = graph().getNode(goalX, goalY);
        }

        return goalNode;
    }

    private void searchNodePath(Node start, Node goal)
    {
        path.clear();

        IndexedAStarPathFinder pathFinder = new IndexedAStarPathFinder(Map.get().graph);
        pathFinder.searchNodePath(start, goal, heuristic, path);
    }

    private boolean notLastNode(Vector2 goal)
    {
        int goalX = parseToNodes(goal.x);
        int goalY = parseToNodes(goal.y);

        return goalX != endNodeX || goalY != endNodeY;
    }

    public DefaultGraphPath getPath()
    {
        return path;
    }

    private int parseToNodes(float pixel)
    {
        return (int)pixel / MySettings.TILE_SIZE;
    }

    private MapModel graph()
    {
        return Map.get().graph;
    }
}
