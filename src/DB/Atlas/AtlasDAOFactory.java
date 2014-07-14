package DB.Atlas;

public enum AtlasDAOFactory
{
    FILE()
    {
        public IAtlasDAO getAtlasDAO()
        {
            return new AtlasFileDAO();
        }
    };

    public abstract IAtlasDAO getAtlasDAO();

    private AtlasDAOFactory() {}
}