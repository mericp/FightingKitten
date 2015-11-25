package SteerableBehavior.Pursuing.Behavior;

import SteerableBehavior.Base.SteerableAgent;
import SteerableBehavior.Pursuing.ConfigRay.IRayTargetConfiguration;
import com.badlogic.gdx.math.Vector2;

public class Target {
    private Vector2 alternativeTarget;
    private IRayTargetConfiguration rayTargetConfig;
    private SteerableAgent target;

    public Target(SteerableAgent target, IRayTargetConfiguration rayTargetConfig)
    {
        this.target = target;
        this.alternativeTarget = target.getPosition();
        this.rayTargetConfig = rayTargetConfig;
    }

    public SteerableAgent get()
    {
        return target;
    }

    public Vector2 getAlternativeTargetCoords() {
        return alternativeTarget;
    }

    public void setAlternativeTargetCoords(Vector2 alternativeTarget) {
        this.alternativeTarget = alternativeTarget;
    }

    public IRayTargetConfiguration getRayConfig() {
        return rayTargetConfig;
    }

    public void setRayConfig(IRayTargetConfiguration rayTargetConfig) {
        this.rayTargetConfig = rayTargetConfig;
    }
}
