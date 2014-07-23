package PhysicalObjects;

import com.badlogic.gdx.physics.box2d.*;

public class PhysicalObjectsFactory
{
    public enum newPhysicalObject
    {
        NEW_DYNAMIC_OBJECT()
        {
            @Override public DynamicObject create(World world, int width, int height)
            {
                DynamicObject dynamicObject = new DynamicObject(world, width, height);
                physicalObjectsBuilder.DYNAMIC_OBJECT.build(dynamicObject);
                return dynamicObject;
            }
        },
        NEW_STATIC_OBJECT()
        {
            @Override public StaticObject create(World world, int width, int height)
            {
                StaticObject staticObject = new StaticObject(world, width, height);
                physicalObjectsBuilder.STATIC_OBJECT.build(staticObject);
                return staticObject;
            }
        };

        private newPhysicalObject() {}

        public abstract IPhysicalObject create(World world, int width, int height);
    }

    private enum physicalObjectsBuilder
    {
        DYNAMIC_OBJECT()
        {
            @Override public void build(IPhysicalObject obj)
            {
                float objWidth = obj.getWidth() / 2;
                float objHeight = obj.getHeight() / 2;

                BodyDef bodyDef = bodyDefBuilder.DYNAMIC_BODY.build(objWidth, objHeight);
                PolygonShape shape = createPolygon(objWidth, objHeight);
                FixtureDef fixDef = createFixture(shape);

                Body body = obj.getWorld().createBody(bodyDef);
                body.createFixture(fixDef);
                obj.setBody(body);

                shape.dispose();
            }
        },
        STATIC_OBJECT()
        {
            @Override public void build(IPhysicalObject obj)
            {
                float objWidth = obj.getWidth() / 2;
                float objHeight = obj.getHeight() / 2;

                BodyDef bodyDef = bodyDefBuilder.STATIC_BODY.build(objWidth, objHeight);
                PolygonShape shape = createPolygon(objWidth, objHeight);
                FixtureDef fixDef = createFixture(shape);

                Body body = obj.getWorld().createBody(bodyDef);
                body.createFixture(fixDef);
                obj.setBody(body);

                shape.dispose();
            }
        };

        private physicalObjectsBuilder() {}

        public abstract void build (IPhysicalObject physicalObject);

        public PolygonShape createPolygon(float width, float height)
        {
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(width, height);
            return shape;
        }

        public FixtureDef createFixture(PolygonShape shape)
        {
            FixtureDef fixDef = new FixtureDef();
            fixDef.shape = shape;
            fixDef.isSensor = true;
            return fixDef;
        }
    }

    private enum bodyDefBuilder
    {
        DYNAMIC_BODY()
        {
            @Override public BodyDef build(float width, float height)
            {
                BodyDef bodyDef = new BodyDef();
                bodyDef.position.set(width, height);
                bodyDef.type = BodyDef.BodyType.DynamicBody;

                return bodyDef;
            }
        },
        STATIC_BODY()
        {
            @Override public BodyDef build(float width, float height)
            {
                BodyDef bodyDef = new BodyDef();
                bodyDef.position.set(width, height);
                bodyDef.type = BodyDef.BodyType.StaticBody;

                return bodyDef;
            }
        };

        private bodyDefBuilder(){}

        public abstract BodyDef build(float width, float height);
    }
}
