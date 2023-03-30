package Stock;
// Kyle Taylor -


//This class is for the stock item books and extends media to inherit some values.

public class Book extends Media {

    private String author; //Stores the author of the book.
    private String genre; //Stores the genre of the book covered.
    private int pageCount; //Stores the page count of the book.
    private String ISBN; //Stores the ISBN for easy look-up.

    Book(int stockID, String title, double price, String publisher, String loaner, String loanDate, String loaneeID, String loaneeDate, String author, String genre, int pageCount, String ISBN)
    {
        setMediaFormat("Book");
        setStockID(stockID);
        setTitle(title);
        setPrice(price);
        setPublisher(publisher);
        setLoaner(loaner);
        setLoanDate(loanDate);
        setLoaneeID(loaneeID);
        setLoaneeDate(loaneeDate);


        this.author = author;
        this.genre = genre;
        this.pageCount = pageCount;
        this.ISBN = ISBN;
    }

    //Getters and Setters for the item information.

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

}
