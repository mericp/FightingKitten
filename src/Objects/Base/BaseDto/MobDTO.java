package Objects.Base.BaseDto;

import Objects.Base.BaseMob.AbstractMob;
import Objects.Base.BaseView.Nekomata;

public class MobDTO{
    public final AbstractMob model;
    public final Nekomata view;

    public MobDTO(AbstractMob model, Nekomata view)
    {
        this.model = model;
        this.view = view;
    }
}
