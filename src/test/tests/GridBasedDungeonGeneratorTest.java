package test.tests;

import org.junit.Assert;
import org.junit.Test;
import test.logica.DungeonGenerator;

public class GridBasedDungeonGeneratorTest {
    private char wall = '*';

    @Test
    public void setFirstCorner()
    {
        DungeonGenerator generator = new DungeonGenerator(100, 100);

        generator.setWallAt(0, 0);

        Assert.assertEquals(wall, generator.map[0][0]);
    }

    @Test
    public void calculateRoom()
    {
        DungeonGenerator generator = new DungeonGenerator(100, 100);

        generator.addRoom();

        Assert.assertEquals(1, generator.rooms.size());
    }
}