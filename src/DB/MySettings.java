package DB;

import DB.Atlas.AtlasDAOFactory;

public class MySettings
{
    // MAIN
    public static final String TITLE = "Fighting Kitten";
    public static final boolean FULL_SCREEN = false;
    public static final boolean V_SYNC_ENABLED = false;
    public static final int FOREGROUND_FPS = 5000;
    public static final int SCREEN_WIDTH = 1200;
    public static final int SCREEN_HEIGHT = 900;

    // Run mode
    public static final boolean DEV_MODE = true;


    // Physics
    public static final float PIXEL_METERS = 0.01f;
    public static final float METERS_PIXEL = 100f;
    public static final float FIXED_TIMESTEP = 30/1000f;   //33 times per second.

    public static final int TILE_WIDTH = 32;
    public static final int TILE_HEIGHT = 32;

    // Resources
    public static final String IMAGES_FOLDER = "assets/Images/";

    public static final String ATLAS_FOLDER = "assets/Atlas/";
    public static final String ATLAS_NAME = "atlas";
    public static final String ATLAS_EXTENSION = ".atlas";
    public static final AtlasDAOFactory ATLAS_DAO = AtlasDAOFactory.FILE;
}
