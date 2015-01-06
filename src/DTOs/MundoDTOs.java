package DTOs;

import Models.KittenModel;

public class MundoDTOs
{
    public static class AddKittenDTO
    {
        public final KittenModel kittenModel;

        public AddKittenDTO(KittenModel kittenModel)
        {
            this.kittenModel = kittenModel;
        }
    }
}
