package SteerableBehavior.Base;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Observable
{
    protected final PropertyChangeSupport observer;

    public Observable()
    {
        observer = new PropertyChangeSupport(this);
    }

    protected void notifyUpdate(String valueName, Object newValue)
    {
        observer.firePropertyChange(valueName, null, newValue);
    }

    public void addObserver(PropertyChangeListener observer)
    {
        this.observer.addPropertyChangeListener(observer);
    }

    public void removeObserver(PropertyChangeListener observer)
    {
        this.observer.removePropertyChangeListener(observer);
    }
}
