package DB.StringRes;

import DB.AtlasGenerator.AtlasDAOFactory;

public class MySettings
{
    // MAIN
    public static final String TITLE = "Fighting Kitten";
    public static final boolean FULL_SCREEN = false;
    public static final boolean V_SYNC_ENABLED = false;
    public static final int FOREGROUND_FPS = 5000;
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 640;

    // Physics
    public static final float PIXEL_METERS = 0.01f;

    public static final int TILE_SIZE = 32;

    public static final int TILES_COUNT_WIDTH = SCREEN_WIDTH / TILE_SIZE;
    public static final int TILES_COUNT_HEIGHT = SCREEN_HEIGHT / TILE_SIZE;

    // Resources
    public static final String IMAGES_FOLDER = "assets/Images/";

    public static final String ATLAS_FOLDER = "assets/Atlas/";
    public static final String ATLAS_NAME = "atlas";
    public static final String ATLAS_EXTENSION = ".atlas";
    public static final AtlasDAOFactory ATLAS_DAO = AtlasDAOFactory.FILE;

    ///Charsets
    public static final String KITTEN = "gatito";
    public static final String BUTTON = "addButton";
    public static final String GROUND = "Grass";
    public static final String WALL = "muro";
    public static final String MONSTER = "monsters";
    public static final String FOOTPRINT = "patitas";
}
