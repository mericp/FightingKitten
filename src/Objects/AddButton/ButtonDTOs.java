package Objects.AddButton;

import Objects.Base.BaseModel.IMobModel;
import Objects.Base.BaseView.Nekomata;
import Objects.Base.MobDTO;

public class ButtonDTOs
{
    public static class ButtonDTO extends MobDTO{
        public ButtonDTO(IMobModel model, Nekomata view) {
            super(model, view);
        }
    }
}


