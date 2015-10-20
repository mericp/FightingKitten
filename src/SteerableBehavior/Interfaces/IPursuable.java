package SteerableBehavior.Interfaces;

import SteerableBehavior.Base.FootprintPath;

public interface IPursuable {
    void set(boolean isPursuable);
    boolean get();
    void setFootprint(FootprintPath footprint);
    FootprintPath getFootprint();
}
