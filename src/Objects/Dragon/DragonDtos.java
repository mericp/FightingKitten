package Objects.Dragon;

import Objects.Base.BaseModel.IMobModel;
import Objects.Base.BaseView.Nekomata;
import Objects.Base.MobDTO;

public class DragonDtos
{
    public static class DragonDto extends MobDTO{
        public DragonDto(IMobModel model, Nekomata view) {
            super(model, view);
        }
    }
}


