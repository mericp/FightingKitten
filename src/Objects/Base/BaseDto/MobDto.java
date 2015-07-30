package Objects.Base.BaseDto;

import Objects.Base.BaseView.Nekomata;
import Objects.Base.BaseModel.IMobModel;

public class MobDto
{
    public final IMobModel model;
    public final Nekomata view;

    public MobDto(IMobModel model, Nekomata view)
    {
        this.model = model;
        this.view = view;
    }
}
