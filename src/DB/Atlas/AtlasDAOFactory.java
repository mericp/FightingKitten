package DB.Atlas;

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

    private AtlasDAOFactory() {}
}