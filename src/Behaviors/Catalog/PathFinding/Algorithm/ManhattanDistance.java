package Behaviors.Catalog.PathFinding.Algorithm;

import Behaviors.Catalog.PathFinding.Node.Node;

public class ManhattanDistance<N extends Node> implements com.badlogic.gdx.ai.pfa.Heuristic<N> {
    @Override
    public float estimate (N node, N endNode) {
        return Math.abs(endNode.x() - node.x()) + Math.abs(endNode.y() - node.y());
    }
}
