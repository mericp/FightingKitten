package Objects.Kitten;

import Objects.Base.MobDTO;

public class KittenDTOs
{
    public static class KittenDTO extends MobDTO
    {
        public KittenDTO(KittenModel kittenModel, KittenView kittenView)
        {
            super(kittenModel, kittenView);
        }
    }
}
