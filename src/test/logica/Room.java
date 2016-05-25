package test.logica;

import com.badlogic.gdx.math.Vector2;

public class Room {
    public final int width;
    public final int height;
    public final Vector2 position;

    public Room(int width, int height, Vector2 position)
    {
        this.width = width;
        this.height = height;
        this.position = position;
    }
}
