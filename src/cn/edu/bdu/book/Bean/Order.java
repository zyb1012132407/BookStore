package cn.edu.bdu.book.Bean;

public class Order {
    private String ID;
    private String username;
    private String DI_name;
    private String DI_position;
    private String DI_phonenumber;
    private float price;
    private int isPaid;
    private String books_ISBN;
    private int num;
    private String date;

    public Order() {
    }

    public Order(String ID, String username, String DI_name, String DI_position, String DI_phonenumber, float price, int isPaid, String books_ISBN, int num, String date) {
        this.ID = ID;
        this.username = username;
        this.DI_name = DI_name;
        this.DI_position = DI_position;
        this.DI_phonenumber = DI_phonenumber;
        this.price = price;
        this.isPaid = isPaid;
        this.books_ISBN = books_ISBN;
        this.num = num;
        this.date = date;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDI_name() {
        return DI_name;
    }

    public void setDI_name(String DI_name) {
        this.DI_name = DI_name;
    }

    public String getDI_position() {
        return DI_position;
    }

    public void setDI_position(String DI_position) {
        this.DI_position = DI_position;
    }

    public String getDI_phonenumber() {
        return DI_phonenumber;
    }

    public void setDI_phonenumber(String DI_phonenumber) {
        this.DI_phonenumber = DI_phonenumber;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(int isPaid) {
        this.isPaid = isPaid;
    }

    public String getBooks_ISBN() {
        return books_ISBN;
    }

    public void setBooks_ISBN(String books_ISBN) {
        this.books_ISBN = books_ISBN;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "ID='" + ID + '\'' +
                ", username='" + username + '\'' +
                ", DI_name='" + DI_name + '\'' +
                ", DI_position='" + DI_position + '\'' +
                ", DI_phonenumber='" + DI_phonenumber + '\'' +
                ", price=" + price +
                ", isPaid=" + isPaid +
                ", books_ISBN='" + books_ISBN + '\'' +
                ", num=" + num +
                ", date='" + date + '\'' +
                '}';
    }
}
