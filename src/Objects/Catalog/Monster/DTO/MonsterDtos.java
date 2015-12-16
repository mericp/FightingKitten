package Objects.Catalog.Monster.DTO;

import Objects.Base.MobDTO;
import Objects.Catalog.Monster.MVC.MonsterModel;
import Objects.Catalog.Monster.MVC.MonsterView;

public class MonsterDtos {
    public static class MonsterDto extends MobDTO
    {
        public MonsterDto(MonsterModel monsterModel, MonsterView monsterView)
        {
            super(monsterModel, monsterView);
        }
    }
}
