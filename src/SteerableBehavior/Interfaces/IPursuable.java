package SteerableBehavior.Interfaces;

import SteerableBehavior.Base.Footprint;

public interface IPursuable {
    void set(boolean isPursuable);
    boolean get();
    void setFootprint(Footprint footprint);
    Footprint getFootprint();
}
