package cn.edu.bdu.book.Bean;

public class Book {
    private String bName;
    private String ISBN;
    private String author;
    private float price;
    private String category;
    private int number;
    private String description;
    private String imgUrl;

    public Book() {
    }

    public Book(String bName, String ISBN, String author, float price, String category, int number, String description, String imgUrl) {
        this.bName = bName;
        this.ISBN = ISBN;
        this.author = author;
        this.price = price;
        this.category = category;
        this.number = number;
        this.description = description;
        this.imgUrl = imgUrl;
    }

    public String getbName() {
        return bName;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public float getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bName='" + bName + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", number=" + number +
                ", description='" + description + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
