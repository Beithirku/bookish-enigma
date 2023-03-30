package Stock;

import java.time.LocalDate;

public class Journals extends Media {

    private String ISSN;
    private String issueNumber;
    private LocalDate issueDate;
    private int pageCount;
    private String subjectArea;


    Journals(int stockID, String title, double price, String publisher, String loaner, String loanDate, String loaneeID, String loaneeDate, String subjectArea, int pageCount, String ISSN, String issueNumber, LocalDate issueDate) {

        setMediaFormat("Journal");
        setStockID(stockID);
        setTitle(title);
        setPrice(price);
        setPublisher(publisher);
        setLoaner(loaner);
        setLoanDate(loanDate);
        setLoaneeID(loaneeID);
        setLoaneeDate(loaneeDate);

        this.ISSN = ISSN;
        this.issueNumber = issueNumber;
        this.issueDate = issueDate;
        this.pageCount = pageCount;
        this.subjectArea = subjectArea;
    }

    public String getISSN(){
        return ISSN;
    }
    public void setISSN(String ISSN) {
        this.ISSN = ISSN;
    }
    public String getIssuNumber(){
        return issueNumber;
    }
    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }
    public LocalDate getIssueDate(){
        return issueDate;
    }
    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }
    public int getPageCount(){
        return pageCount;
    }
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
