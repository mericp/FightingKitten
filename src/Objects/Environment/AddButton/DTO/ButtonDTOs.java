package Objects.Environment.AddButton.DTO;

import Objects.Base.BaseDto.MobDTO;
import Objects.Base.BaseMob.AbstractMob;
import Objects.Base.BaseView.Nekomata;

public class ButtonDTOs
{
    public static class ButtonDTO extends MobDTO {
        public ButtonDTO(AbstractMob model, Nekomata view) {
            super(model, view);
        }
    }
}


