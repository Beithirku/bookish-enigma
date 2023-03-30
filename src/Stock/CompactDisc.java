package Stock;

public class CompactDisc extends Media {

    private int runTime; //Overall runTime of the CD.
    private int noOfTracks; //Number of tracks on the CD.
    private String storageContainer; //Type of container the disc comes in.
    private String artist; //The performing/recording artist.
    private String genre; //Genre the music is.

    //default constructor
    CompactDisc(int stockID, String title, double price, String publisher, String loaner, String loanDate, String loaneeID, String loaneeDate, int runTime, int noOfTracks, String storageContainer, String artist, String genre) {

        setMediaFormat("Compact Disc");
        setStockID(stockID);
        setTitle(title);
        setPrice(price);
        setPublisher(publisher);
        setLoaner(loaner);
        setLoanDate(loanDate);
        setLoaneeID(loaneeID);
        setLoaneeDate(loaneeDate);

        this.runTime = runTime;
        this.noOfTracks = noOfTracks;
        this.storageContainer = storageContainer;
        this.artist = artist;
        this.genre = genre;

    }

    //Getters and setters.
    public int getRunTime(){
        return runTime;
    }

    public void setRunTime(int runTime){
        this.runTime = runTime;
    }

    public int getNoOfTracks(){
        return noOfTracks;
    }

    public void setNoOfTracks(int noOfTracks){
        this.noOfTracks = noOfTracks;
    }

    public String getArtist(){
        return artist;
    }

    public void setArtist(String artist){
        this.artist = artist;
    }

    public String getGenre(){
        return genre;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }

}
