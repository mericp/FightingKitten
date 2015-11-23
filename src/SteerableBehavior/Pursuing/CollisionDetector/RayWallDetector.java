package SteerableBehavior.Pursuing.CollisionDetector;

import com.badlogic.gdx.ai.utils.Collision;
import com.badlogic.gdx.ai.utils.Ray;
import com.badlogic.gdx.ai.utils.RaycastCollisionDetector;
import com.badlogic.gdx.math.Vector2;

public class RayWallDetector implements RaycastCollisionDetector<Vector2> {
    @Override
    public boolean collides(Ray<Vector2> ray)
    {
        return false;
    }

    @Override
    public boolean findCollision(Collision<Vector2> collision, Ray<Vector2> ray) {
        return false;
    }
}
