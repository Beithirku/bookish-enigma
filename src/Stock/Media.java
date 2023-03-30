package Stock;

abstract class Media {

    private int stockID; //Stores the StockID on the local system.
    private String mediaFormat; //Stores the format of the content i.e Book, Video, CD.
    private String title; //Stores the title of the content.
    private double price; //Stores the price of the item.
    private String publisher; //Stores the publishers name.
    private String loaner; //Stores the information on who loaned the content out.
    private String loanDate; //Stores the last date of when it was loaned out.
    private String loaneeID; //Stores the information of the person it was loaned to.
    private String loaneeDate; //Stores the date it was loaned to said person.

    public String getMediaFormat(){
        return mediaFormat;
    }

    public void setMediaFormat(String mediaFormat) {
        this.mediaFormat = mediaFormat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public int getStockID(){
        return stockID;
    }

    public void setStockID(int stockID){
        this.stockID = stockID;
    }

    public String getPublisher(){
        return publisher;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    public String getLoaner(){
        return loaner;
    }

    public void setLoaner(String loaner){
        this.loaner = loaner;
    }

    public String getLoanDate(){
        return loanDate;
    }

    public void setLoanDate(String loanDate){
        this.loanDate = loanDate;
    }

    public String getLoaneeID(){
        return loaneeID;
    }

    public void setLoaneeID(String loaneeID){
        this.loaneeID = loaneeID;
    }

    public String getLoaneeDate(){
        return loaneeDate;
    }

    public void setLoaneeDate(String loaneeDate){
        this.loaneeDate = loaneeDate;
    }





}
