package SteerableBehavior.Pursuing.Behavior;

import SteerableBehavior.Base.SteerableAgent;
import SteerableBehavior.Pursuing.ConfigRay.IRayTargetConfiguration;
import com.badlogic.gdx.math.Vector2;

public class Target {
    private SteerableAgent steerableAgent;
    private Vector2 targetCoords = new Vector2();
    private IRayTargetConfiguration rayTargetConfig;

    public Target(SteerableAgent steerableAgent, Vector2 targetCoords, IRayTargetConfiguration rayTargetConfig)
    {
        this.steerableAgent = steerableAgent;
        this.targetCoords = targetCoords;
        this.rayTargetConfig = rayTargetConfig;
    }

    public SteerableAgent getSteerableAgent() {
        return steerableAgent;
    }

    public void setSteerableAgent(SteerableAgent target) {
        this.steerableAgent = target;
    }

    public Vector2 getCoords() {
        return targetCoords;
    }

    public void setCoords(Vector2 targetCoords) {
        this.targetCoords = targetCoords;
    }

    public IRayTargetConfiguration getRayConfig() {
        return rayTargetConfig;
    }

    public void setRayConfig(IRayTargetConfiguration rayTargetConfig) {
        this.rayTargetConfig = rayTargetConfig;
    }
}
