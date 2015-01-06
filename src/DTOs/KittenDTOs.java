package DTOs;

import Models.KittenModel;
import Views.KittenView;
import com.badlogic.gdx.math.Vector2;

public class KittenDTOs
{
    public static class PositionDTO
    {
        public final float x;
        public final float y;

        public PositionDTO(float x, float y)
        {
            this.x = x;
            this.y = y;
        }
    }

    public static class KittenDTO
    {
        public final KittenModel kittenModel;
        public final KittenView kittenView;

        public KittenDTO(KittenModel kittenModel, KittenView kittenView)
        {
            this.kittenModel = kittenModel;
            this.kittenView = kittenView;
        }
    }
}
