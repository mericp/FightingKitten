package Models;

import PhysicalObjects.DynamicObject;

public interface IMobModel
{
    DynamicObject getDynamicBody();
    void interpolatePositions(float alpha);
    void setPosition(float x, float y);
}
