package Objects.Catalog.Kitten.MVC;

import Behaviors.Base.SuperClasses.Automaton;
import Behaviors.Catalog.BehaviorsFactory;
import DB.StringRes.MySettings;
import Objects.Catalog.World.Map.MVC.Map;
import Objects.Catalog.World.Map.Tiles.TileModel;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.steer.utils.paths.LinePath;
import com.badlogic.gdx.math.Vector2;
import Behaviors.Catalog.PathFinding.Algorithm.PathFinder;
import com.badlogic.gdx.utils.Array;

public class KittenModel extends Automaton
{
    public KittenModel(Vector2 position)
    {
        super(position, true, 50, 100);
    }

    public void dragged(Vector2 clickPosition)
    {
        DefaultGraphPath path = calculatePath(clickPosition);
        LinePath<Vector2> linePath = parsePath(path);
        setSteeringBehavior(BehaviorsFactory.COLLECTION.followPath(this, linePath));
    }

    public void preCalculate(Vector2 potentialGoal)
    {
        DefaultGraphPath path = calculatePath(potentialGoal);

        Map.get().reset();
        for(int x = 0; x < path.nodes.size; x++)
        {
            TileModel node = (TileModel)path.nodes.get(x);
            //Map.get().view.updateTextureAt(node.x(), node.y(), MySettings.ATLAS_DAO.getAtlasDAO().getTexture(MySettings.FOOTPRINT));
        }
    }

    private DefaultGraphPath calculatePath(Vector2 goal)
    {
        PathFinder pathFinder = new PathFinder();

        pathFinder.updatePath(position, goal);

        return pathFinder.getPath();
    }

    private LinePath<Vector2> parsePath (DefaultGraphPath path) {
        Array<Vector2> wayPoints = new Array<>();

        for(int x = 0; x < path.nodes.size; x++)
        {
            TileModel node = (TileModel)path.nodes.get(x);

            float posx = node.x() * MySettings.TILE_SIZE;
            float posy = node.y() * MySettings.TILE_SIZE;

            wayPoints.add(new Vector2(posx, posy));
        }

        changePosition(new Vector2(wayPoints.first().x, wayPoints.first().y));

        LinePath<Vector2> linePath = new LinePath<>(wayPoints, true);

        return linePath;
    }
}
