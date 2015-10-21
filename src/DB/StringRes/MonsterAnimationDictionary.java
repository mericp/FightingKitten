package DB.StringRes;

import java.util.HashMap;

public class MonsterAnimationDictionary {
    private HashMap<String, Integer> animations = new HashMap<>();

    public void Create()
    {
        animations.put("GoingSouth", 1);
        animations.put("GoingWest", 5);
        animations.put("GoingEast", 9);
        animations.put("GoingNorth", 13);
    }

    public Integer getAnimationNumber(String name)
    {
        return animations.get(name);
    }
}
