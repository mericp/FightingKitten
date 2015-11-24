package SteerableBehavior.Interfaces;

import SteerableBehavior.Base.SmellTrails;

public interface IPursuable {
    void set(boolean isPursuable);
    boolean get();
    void setSmellTrails(SmellTrails smellTrails);
    SmellTrails getSmellTrails();
}
