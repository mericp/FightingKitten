package test.logica;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Room {
    public final int width;
    public final int height;
    public final Vector2 position;

    public Room(Vector2 position, int maxWidth, int maxHeight)
    {
        this.position = position;
        width = randomWidth(maxWidth);
        height = randomHeight(maxHeight);
    }

    private int randomWidth(int bound)
    {
        return nextInt(bound - (int)position.x);
    }

    private int randomHeight(int bound)
    {
        return nextInt(bound - (int)position.y);
    }

    private int nextInt(int bounds)
    {
        Random rnd = new Random();
        return rnd.nextInt(bounds);
    }
}
