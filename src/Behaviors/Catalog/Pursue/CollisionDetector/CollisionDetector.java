package Behaviors.Catalog.Pursue.CollisionDetector;

import DB.StringRes.MySettings;
import DB.TileGenerator.MVC.TileController;
import DB.TileGenerator.Map;
import com.badlogic.gdx.ai.utils.Collision;
import com.badlogic.gdx.ai.utils.Ray;
import com.badlogic.gdx.ai.utils.RaycastCollisionDetector;
import com.badlogic.gdx.math.Vector2;

public class CollisionDetector implements RaycastCollisionDetector<Vector2> {
    @Override
    public boolean collides(Ray<Vector2> ray)
    {
        return findCollision(null, ray);
    }

    @Override
    public boolean findCollision(Collision<Vector2> collision, Ray<Vector2> ray) {
        return RunBresenhamAlgorithm(collision, ray);
    }

    private boolean RunBresenhamAlgorithm(Collision<Vector2> collision, Ray<Vector2> ray)
    {
        int tileX;
        int tileY;

        int lastTileX = 0;
        int lastTileY = 0;

        int x1 = (int)ray.start.x;
        int y1 = (int)ray.start.y;

        int x2 = (int)ray.end.x;
        int y2 = (int)ray.end.y;

        int d = 0;
        int dy = Math.abs(y2 - y1);
        int dx = Math.abs(x2 - x1);
        int dy2 = (dy << 1); // slope scaling factors to avoidWall floating
        int dx2 = (dx << 1); // point

        int ix = x1 < x2 ? 1 : -1; // increment direction
        int iy = y1 < y2 ? 1 : -1;

        if (dy <= dx)
        {
            for (;;)
            {
                tileX = x1 / MySettings.TILE_WIDTH;
                tileY = y1 / MySettings.TILE_WIDTH;

                if (lastTileX != tileX || lastTileY != tileY)
                {
                    lastTileX = tileX;
                    lastTileY = tileY;

                    if (checkCollision(x1, y1, lastTileX, lastTileY, collision))
                    {
                        return true;
                    }
                }

                if (x1 == x2)
                {
                    break;
                }

                x1 += ix;
                d += dy2;

                if (d > dx)
                {
                    y1 += iy;
                    d -= dx2;
                }
            }
        }
        else
        {
            for (;;)
            {
                tileX = x1 / MySettings.TILE_WIDTH;
                tileY = y1 / MySettings.TILE_WIDTH;

                if (lastTileX != tileX || lastTileY != tileY)
                {
                    lastTileX = tileX; lastTileY = tileY;

                    if (checkCollision(x1, y1, lastTileX, lastTileY, collision))
                    {
                        return true;
                    }
                }

                if (y1 == y2)
                {
                    break;
                }

                y1 += iy;
                d += dx2;

                if (d > dy)
                {
                    x1 += ix;
                    d -= dy2;
                }
            }
        }

        return false;
    }

    private boolean checkCollision (int x, int y, int tileX, int tileY, Collision<Vector2> collision)
    {
        TileController tile = Map.getTile(tileX, tileY);

        if (tile != null && tile.isCollidable())
        {
            if (collision != null)
            {
                calculateCollisionData(x, y, tileX, tileY, collision);
            }

            return true;
        }

        return false;
    }

    private void calculateCollisionData (int x, int y, int tileX, int tileY, Collision<Vector2> outputCollision)
    {
        outputCollision.point.set(x, y);

        int directionX = x - tileX * MySettings.TILE_WIDTH;
        int directionY = y - tileY * MySettings.TILE_WIDTH;

        if (directionX == 0)
        {
            outputCollision.normal.x = -10;
        }
        else if (directionX == (MySettings.TILE_WIDTH- 1))
        {
            outputCollision.normal.x = 10;
        }
        else if (directionY == 0)
        {
            outputCollision.normal.y = -10;
        }
        else if (directionY == (MySettings.TILE_WIDTH - 1))
        {
            outputCollision.normal.y = 10;
        }
    }
}
