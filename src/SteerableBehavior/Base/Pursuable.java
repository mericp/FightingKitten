package SteerableBehavior.Base;

import SteerableBehavior.Interfaces.IPursuable;

public class Pursuable implements IPursuable{
    protected Footprint footprint;
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
    public void setFootprint(Footprint footprint) {
        this.footprint = footprint;
    }

    @Override
    public Footprint getFootprint()
    {
        return footprint;
    }
}
