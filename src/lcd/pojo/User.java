package lcd.pojo;

/**
 * @athor lcdstart
 * @create 2022-07-24 16:41
 */
public class User {
    private String username;
    private Integer id;
    private String password;
    private String email;

    public User() {
    }

    public User(String username, Integer id, String password, String email) {
        this.username = username;
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", id=" + id +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
