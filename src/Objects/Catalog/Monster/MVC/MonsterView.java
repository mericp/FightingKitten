package Objects.Catalog.Monster.MVC;

import DB.StringRes.MySettings;
import Objects.Base.Nekomata;
import DB.StringRes.MonsterAnimationDictionary;

public class MonsterView extends Nekomata{
    private final MonsterAnimationDictionary animationDictionary;
    private final MonsterModel model;

    public MonsterView(MonsterModel model)
    {
        super(MySettings.ATLAS_DAO.getAtlasDAO().getTexture(MySettings.MONSTER), 8, 12, 3, 0.20f, true);

        this.model = model;

        animationDictionary = new MonsterAnimationDictionary();
        animationDictionary.Create();

        setWidth(MySettings.TILE_SIZE);
        setHeight(MySettings.TILE_SIZE);

        setPosition(model.getPosition().x, model.getPosition().y);
    }

    @Override
    public void updateAnimation() {
        setPosition(model.getPosition().x, model.getPosition().y);
        animate("GoingSouth");
    }

    private void animate(String name)
    {
        setAnimation(animationDictionary.getAnimationNumber(name), false);
    }
}
