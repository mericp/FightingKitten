package Behaviors.Base.SuperClasses;

import Behaviors.Base.Interfaces.IPursuable;
import com.badlogic.gdx.math.Vector2;
import java.util.Iterator;

public class Pursuable implements IPursuable{
    private final SmellTrails smellTrails;
    private boolean isPursuable;

    public Pursuable(boolean pursuable)
    {
        smellTrails = new SmellTrails();
        isPursuable = pursuable;
    }

    @Override public void change(boolean isPursuable) {
        this.isPursuable = isPursuable;
    }
    @Override public boolean is(){ return isPursuable; }
    @Override public Iterator<SmellTrail> getSmellTrailsIterator()
    {
        return smellTrails.getIterator();
    }
    @Override public void addSmellTrailAt(Vector2 position) { smellTrails.add(position); }
    @Override public void updateSmellTrail(float delta) {
        smellTrails.update(delta); 
    }
}
