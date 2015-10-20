package SteerableBehavior.Base;

import SteerableBehavior.Interfaces.IPursuable;

public class Pursuable implements IPursuable{
    protected FootprintPath footprint;
    protected boolean pursuable = false;

    @Override
    public void set(boolean isPursuable) {
        pursuable = isPursuable;
    }

    @Override
    public boolean get()
    {
        return pursuable;
    }

    @Override
    public void setFootprint(FootprintPath footprint) {
        this.footprint = footprint;
    }

    @Override
    public FootprintPath getFootprint()
    {
        return footprint;
    }
}
