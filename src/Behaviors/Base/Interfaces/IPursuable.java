package Behaviors.Base.Interfaces;

import Behaviors.Base.SuperClasses.SmellTrail;
import Behaviors.Base.SuperClasses.SmellTrails;
import com.badlogic.gdx.math.Vector2;

import java.util.Iterator;

public interface IPursuable {
    void change(boolean isPursuable);
    boolean is();
    void setSmellTrails(SmellTrails smellTrails);
    Iterator<SmellTrail> getSmellTrailsIterator();
    void addSmellTrailAt(Vector2 position);
    void updateSmellTrail(float delta);
}
