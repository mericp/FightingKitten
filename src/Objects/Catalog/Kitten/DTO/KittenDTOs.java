package Objects.Catalog.Kitten.DTO;

import Objects.Base.MobDTO;
import Objects.Catalog.Kitten.MVC.KittenModel;
import Objects.Catalog.Kitten.MVC.KittenView;

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
