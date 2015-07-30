package Objects.Kitten;

import Objects.Base.BaseDto.MobDto;

public class KittenDTOs
{
    public static class KittenDTO extends MobDto
    {
        public KittenDTO(KittenModel kittenModel, KittenView kittenView)
        {
            super(kittenModel, kittenView);
        }
    }
}
