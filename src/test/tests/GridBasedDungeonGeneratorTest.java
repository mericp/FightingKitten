package test.tests;

import org.junit.Assert;
import org.junit.Test;
import test.logica.DungeonGenerator;
import test.logica.Room;

public class GridBasedDungeonGeneratorTest {
    private char wall = '*';

    private DungeonGenerator generator()
    {
        return new DungeonGenerator(100, 100);
    }

    @Test
    public void setFirstCorner()
    {
        DungeonGenerator generator = generator();

        generator.setWallAt(0, 0);

        Assert.assertEquals(wall, generator.map[0][0]);
    }

    @Test
    public void randomizeRoomsDimensions()
    {
        DungeonGenerator generator = generator();

        Room room = generator.generateRoom();

        Assert.assertTrue(room.width > -1);
        Assert.assertTrue(room.height > -1);
    }

    @Test
    public void addRoom()
    {
        DungeonGenerator generator = generator();

        generator.addRoom();

        Assert.assertEquals(1, generator.rooms.size());
    }
}