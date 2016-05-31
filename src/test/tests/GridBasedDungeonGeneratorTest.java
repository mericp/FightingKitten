package test.tests;

import org.junit.Assert;
import org.junit.Test;
import org.lwjgl.Sys;
import test.logica.Cell;
import test.logica.DungeonGenerator;
import test.logica.Room;

import java.io.Console;

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

    @Test
    public void drawDungeon()
    {
        DungeonGenerator generator = generator();
        generator.generateRoomAtCell(0, 0);

        for(int x = 0; x < generator.map.length; x++)
        {
            for(int y = 0; y < generator.map[0].length; y++)
            {
                System.out.println();

                for(int z = 0; z < generator.map[0][0].content.length; z++)
                {
                    for(int w = 0; w < generator.map[0][0].content[0].length; w++)
                    {
                        if(generator.map[x][y].content[z][w] != '*')
                        {
                            System.out.print('.');
                        }
                        else
                        {
                            System.out.print(generator.map[x][y].content[z][w]);
                        }
                    }
                }
            }
        }
    }
}