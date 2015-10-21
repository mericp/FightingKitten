package Objects.AddButton.DTO;

import Objects.Base.BaseMob.AbstractMob;
import Objects.Base.BaseView.Nekomata;
import Objects.Base.BaseDTO.MobDTO;

public class ButtonDTOs
{
    public static class ButtonDTO extends MobDTO{
        public ButtonDTO(AbstractMob model, Nekomata view) {
            super(model, view);
        }
    }
}


