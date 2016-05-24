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

        Assert.assertEquals(generator.map[0][0], wall);
    }
}