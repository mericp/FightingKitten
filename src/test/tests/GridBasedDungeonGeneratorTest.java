package test.tests;

import org.junit.Assert;
import org.junit.Test;
import test.logica.Cell;
import test.logica.DungeonGenerator;
import test.logica.Room;

public class GridBasedDungeonGeneratorTest {
    private DungeonGenerator generator()
    {
        return new DungeonGenerator(10, 10);
    }

    @Test
    public void generateRoom()
    {
        DungeonGenerator generator = generator();

        generator.generateRoomAtCell(0, 0);

        Assert.assertTrue(generator.map[0][0].rooms.size() == 1);
    }

    @Test
    public void roomFitsInCell()
    {
        DungeonGenerator generator = generator();
        Cell cell = generator.map[0][0];

        generator.generateRoomAtCell(0, 0);

        Room generatedRoom = cell.rooms.get(0);

        Assert.assertTrue(generatedRoom.position.x + generatedRoom.width <= cell.content.length);
        Assert.assertTrue(generatedRoom.position.y + generatedRoom.height <= cell.content[0].length);
    }
}