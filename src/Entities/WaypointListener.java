package Entities;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class WaypointListener implements ContactListener
{
    @Override public void beginContact(Contact contact)
    {
        Object fixtureAuserData = contact.getFixtureA().getBody().getUserData();
        Object fixtureBuserData = contact.getFixtureB().getBody().getUserData();

        if (fixtureAuserData == fixtureBuserData)
        {
            if (fixtureAuserData instanceof ICollisionable)
            {
                ((ICollisionable) fixtureAuserData).onCollide();
            }
        }
    }

    @Override public void endContact(Contact contact) {}
    @Override public void preSolve(Contact contact, Manifold oldManifold) {}
    @Override public void postSolve(Contact contact, ContactImpulse impulse) {}
}