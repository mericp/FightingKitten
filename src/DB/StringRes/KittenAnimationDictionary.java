package DB.StringRes;

import java.util.HashMap;

public class KittenAnimationDictionary {
    private HashMap<String, Integer> animations = new HashMap<>();

    public void Create()
    {
        animations.put("Selected", 18);
        animations.put("Sitting", 28);
        animations.put("GoingEast", 8);
        animations.put("GoingNorthEast", 13);
        animations.put("GoingNorth", 12);
        animations.put("GoingNorthWest", 5);
        animations.put("GoingWest", 4);
        animations.put("GoingSouthWest", 1);
        animations.put("GoingSouth", 0);
        animations.put("GoingSouthEast", 9);
    }

    public Integer getAnimationNumber(String name)
    {
        return animations.get(name);
    }
}
