package Entities;

import Actores.Nekomata;
import DB.MySettings;
import DynamicBody.BodyFactory;
import DynamicBody.DynamicObject;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sun.org.apache.xalan.internal.xsltc.dom.KeyIndex;

public class Kitten extends Actor
{
    //Model
    private DynamicObject dynamicBody;

    //View
    private Nekomata nekomata;

    private Vector2 destino;

    public Kitten(World world)
    {
        this.dynamicBody = new DynamicObject(world, MySettings.HITBOX_WIDTH, MySettings.HITBOX_HEIGHT);
        BodyFactory.buildBody.KINEMATIK.buildBody(dynamicBody);

        TextureRegion texture = MySettings.ATLAS_DAO.getAtlasDAO().getTexture("gatito");
        nekomata = new Nekomata(texture, 8, 12, 3, 0.20f);

        this.addListener(new KittenDragListener(this));
        this.setWidth(MySettings.HITBOX_WIDTH);
        this.setHeight(MySettings.HITBOX_HEIGHT);
    }

    // Relate model (body) with view (kitten)
    public void getDynamicBodyPosition()
    {
        this.setPosition(dynamicBody.getX(), dynamicBody.getY());
        nekomata.setPosition(dynamicBody.getX(), dynamicBody.getY());
    }

    @Override public void draw (Batch batch, float alpha)
    {
        nekomata.draw(batch, alpha);
    }

    public void setDestino(Vector2 destino)
    {
        this.destino = destino;
    }

    public void update()
    {
        if(destino != null)
        {
            if(Math.abs(dynamicBody.getX() - destino.x) > 1  || Math.abs(dynamicBody.getX() - destino.y) > 1)
            {
                float x = (destino.x - dynamicBody.getX()) / 1f;
                float y = (destino.y - dynamicBody.getY()) / 1f;
                dynamicBody.getBody().setLinearVelocity(x * MySettings.PIXEL_METTERS, y * MySettings.PIXEL_METTERS);
            }
            else
            {
                dynamicBody.getBody().setLinearVelocity(0, 0);
            }

            getDynamicBodyPosition();
        }
    }
}
