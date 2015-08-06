package Objects.Base;

import Objects.Base.BaseModel.IMobModel;
import Objects.Base.BaseView.Nekomata;

public class MobDTO{
    public final IMobModel model;
    public final Nekomata view;

    public MobDTO(IMobModel model, Nekomata view)
    {
        this.model = model;
        this.view = view;
    }
}
