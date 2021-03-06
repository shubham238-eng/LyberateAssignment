package daily.worker.lyberateassignment.model;

public class Title implements BasicEntity {
    String Artist_Name ;
    int Artist_Id;

    public String getArtist_Name() {
        return Artist_Name;
    }

    public void setArtist_Name(String artist_Name) {
        Artist_Name = artist_Name;
    }

    public int getArtist_Id() {
        return Artist_Id;
    }

    public void setArtist_Id(int artist_Id) {
        Artist_Id = artist_Id;
    }

    public Title(String artist_Name, int artist_Id) {
        Artist_Name = artist_Name;
        Artist_Id = artist_Id;
    }

    @Override
    public int getType() {
        return HEADER;
    }
}
