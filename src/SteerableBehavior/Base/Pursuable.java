package SteerableBehavior.Base;

import SteerableBehavior.Interfaces.IPursuable;

public class Pursuable implements IPursuable{
    protected SmellTrails smellTrails;
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
    public void setSmellTrails(SmellTrails smellTrails) {
        this.smellTrails = smellTrails;
    }

    @Override
    public SmellTrails getSmellTrails() {
        return smellTrails;
    }
}
