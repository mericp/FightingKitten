package Objects.Catalog.AddButton.DTO;

import Objects.Base.MobDTO;
import Objects.Base.AbstractMob;
import Objects.Base.Nekomata;

public class ButtonDTOs
{
    public static class ButtonDTO extends MobDTO {
        public ButtonDTO(AbstractMob model, Nekomata view) {
            super(model, view);
        }
    }
}


