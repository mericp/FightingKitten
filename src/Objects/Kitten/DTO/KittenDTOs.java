package Objects.Kitten.DTO;

import Objects.Base.MobDTO;
import Objects.Kitten.MVC.KittenModel;
import Objects.Kitten.MVC.KittenView;

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
