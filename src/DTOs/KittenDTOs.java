package DTOs;

import Objects.Kitten.KittenModel;
import Views.KittenView;

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

    public static class KittenDTO extends MobDto
    {
        public KittenDTO(KittenModel kittenModel, KittenView kittenView)
        {
            super(kittenModel, kittenView);
        }
    }
}
