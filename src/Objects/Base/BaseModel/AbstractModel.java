package Objects.Base.BaseModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class AbstractModel
{
    protected final PropertyChangeSupport observer;

    public AbstractModel()
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
