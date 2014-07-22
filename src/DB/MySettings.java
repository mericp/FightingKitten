package DB;

import DB.Atlas.AtlasDAOFactory;

public class MySettings
{
    // Screen config
    public static boolean V_SYNC_ENABLED = false;
    public static boolean FULL_SCREEN = false;
    public static int SCREEN_WIDTH = 1200;
    public static int SCREEN_HEIGHT = 900;

    // Run mode
    public static boolean DEV_MODE = true;


    // Physics
    public static float PIXEL_METERS = 0.01f;
    public static float METERS_PIXEL = 100f;
    public static float FIXED_TIMESTEP = 30/1000f;   //33 times per second.

    public static int KITTEN_HITBOX_WIDTH = 32;
    public static int KITTEN_HITBOX_HEIGHT = 32;

    // Resources
    public static String IMAGES_FOLDER = "Images/";

    public static String ATLAS_FOLDER = "Atlas/";
    public static String ATLAS_NAME = "atlas";
    public static String ATLAS_EXTENSION = ".atlas";
    public static AtlasDAOFactory ATLAS_DAO = AtlasDAOFactory.FILE;
}
