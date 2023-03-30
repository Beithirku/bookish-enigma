package Stock;

public class Video extends Media {

    //Variable setting, decided to use Ints for the runTime because all move runtimes I've seen so far have been in full numbers, no decimals.
    private int runTime; //store video runtime in minutes.
    private String videoFormat; //Stores the format of the video, DVD, BluRay, VHS (If this is even still used)
    private String genre; //Stores the genre(s) of the video.
    private String storageContainer; //Stores the container in which the video is sold/loaned in.

    Video(int stockID, String title, double price, String publisher, String loaner, String loanDate, String loaneeID, String loaneeDate, int runTime, String videoFormat, String genre, String storageContainer) {

        setMediaFormat("Video");
        setStockID(stockID);
        setTitle(title);
        setPrice(price);
        setPublisher(publisher);
        setLoaner(loaner);
        setLoanDate(loanDate);
        setLoaneeID(loaneeID);
        setLoaneeDate(loaneeDate);

        this.runTime = runTime;
        this.videoFormat = videoFormat;
        this.genre = genre;
        this.storageContainer = storageContainer;

    }

    //Getters and setters.
    public int getRunTime(){
        return runTime;
    }

    public void setRunTime(int runTime){
        this.runTime = runTime;
    }

    public String getVideoFormat(){
        return videoFormat;
    }

    public void setVideoFormat(String videoFormat){
        this.videoFormat = videoFormat;
    }

    public String getGenre(){
        return genre;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }

    public String getStorageContainer(){
        return storageContainer;
    }

    public void setStorageContainer(String storageContainer){
        this.storageContainer = storageContainer;
    }


}
