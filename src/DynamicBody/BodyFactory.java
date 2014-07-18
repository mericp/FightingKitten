package DynamicBody;

import DB.MySettings;
import com.badlogic.gdx.physics.box2d.*;

public class BodyFactory
{
    public enum newBody {

        KINEMATIK()
        {
            public Body newBody(World world, int width, int height)
            {
                BodyDef bodyDef = new BodyDef();
                bodyDef.position.set(width / 2 * MySettings.PIXEL_METTERS, height / 2 * MySettings.PIXEL_METTERS);
                bodyDef.type = BodyDef.BodyType.KinematicBody;

                PolygonShape shape = new PolygonShape();
                shape.setAsBox(width / 2 * MySettings.PIXEL_METTERS, height / 2 * MySettings.PIXEL_METTERS);

                FixtureDef fixDef = new FixtureDef();
                fixDef.shape = shape;

                Body body = world.createBody(bodyDef);
                body.createFixture(fixDef);

                shape.dispose();

                return body;
            }
        };

        public abstract Body newBody(World world, int width, int height);

        private newBody() {}
    }

    public enum buildBody
    {
        KINEMATIK()
        {
            public void buildBody(DynamicObject obj)
            {
                BodyDef bodyDef = new BodyDef();
                bodyDef.position.set(obj.getWidth() / 2, obj.getHeight() / 2);
                bodyDef.type = BodyDef.BodyType.KinematicBody;

                PolygonShape shape = new PolygonShape();
                shape.setAsBox(obj.getWidth() / 2, obj.getHeight() / 2);

                FixtureDef fixDef = new FixtureDef();
                fixDef.shape = shape;

                Body body = obj.getWorld().createBody(bodyDef);
                body.createFixture(fixDef);

                shape.dispose();

                obj.setBody(body);
            }
        };

        public abstract void buildBody(DynamicObject dynamicObject);

        private buildBody() {}
    }
}
