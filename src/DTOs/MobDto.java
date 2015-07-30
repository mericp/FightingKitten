package DTOs;

import ViewBase.Nekomata;
import Models.IMobModel;

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
