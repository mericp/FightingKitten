package Objects.World.Map;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class MapController{
    private MapView view;

    public MapController()
    {
        view = new MapView();
    }

    public void dispose()
    {
        view.dispose();
    }

    public void setCamera(OrthographicCamera camera)
    {
        view.setCamera(camera);
    }

    public void render()
    {
        view.render();
    }
}
