package DB.AtlasGenerator;

public enum AtlasDAOFactory
{
    FILE()
    {
        @Override public IAtlasDAO getAtlasDAO()
        {
            return new AtlasFileDAO();
        }
    };

    public abstract IAtlasDAO getAtlasDAO();

    AtlasDAOFactory() {}
}