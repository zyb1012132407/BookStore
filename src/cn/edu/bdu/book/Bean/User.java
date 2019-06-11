package cn.edu.bdu.book.Bean;

public class User {
    private String username;
    private String password;
    private String sex;
    private int age;
    private String email;
    private int Level;

    public User() {

    }

    public User(String username, String password, String sex, int age, String email, int level) {
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.age = age;
        this.email = email;
        this.Level = level;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", birthday='" + email + '\'' +
                ", Level=" + Level +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public int getLevel() {
        return Level;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
