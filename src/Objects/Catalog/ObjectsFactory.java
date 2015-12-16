package Objects.Catalog;

import Objects.Catalog.AddButton.MVC.AddButtonController;
import Objects.Catalog.Kitten.MVC.KittenController;
import Objects.Catalog.Monster.MVC.MonsterController;
import Objects.Catalog.World.MVC.WorldController;
import com.badlogic.gdx.math.Vector2;

public enum ObjectsFactory {
    COLLECTION()
    {
        @Override public KittenController kitten(WorldController world, Vector2 position)
        {
            return new KittenController(world, position);
        }

        @Override public MonsterController monster(WorldController world, Vector2 position)
        {
            return new MonsterController(world, position);
        }

        @Override public AddButtonController addbutton(WorldController world, Vector2 position)
        {
            return new AddButtonController(world, position);
        }

        @Override public WorldController world()
        {
            return new WorldController();
        }
    };

    public abstract KittenController kitten(WorldController mcpar, Vector2 position);
    public abstract MonsterController monster(WorldController mcpar, Vector2 position);
    public abstract AddButtonController addbutton(WorldController world, Vector2 position);
    public abstract WorldController world();

    ObjectsFactory() {}
}
