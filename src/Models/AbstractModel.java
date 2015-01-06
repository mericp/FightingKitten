package Models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class AbstractModel
{
    protected final PropertyChangeSupport observed;

    public AbstractModel()
    {
        observed = new PropertyChangeSupport(this);
    }

    protected void notifyUpdate(String valueName, Object newValue)
    {
        observed.firePropertyChange(valueName, null, newValue);
    }

    public void addObserver(PropertyChangeListener observer)
    {
        observed.addPropertyChangeListener(observer);
    }

    public void removeObserver(PropertyChangeListener observer)
    {
        observed.removePropertyChangeListener(observer);
    }
}
