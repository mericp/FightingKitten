package Objects.Monster.DTO;

import Objects.Base.BaseDto.MobDTO;
import Objects.Monster.MVC.MonsterModel;
import Objects.Monster.MVC.MonsterView;

public class MonsterDtos {
    public static class MonsterDto extends MobDTO
    {
        public MonsterDto(MonsterModel monsterModel, MonsterView monsterView)
        {
            super(monsterModel, monsterView);
        }
    }
}
