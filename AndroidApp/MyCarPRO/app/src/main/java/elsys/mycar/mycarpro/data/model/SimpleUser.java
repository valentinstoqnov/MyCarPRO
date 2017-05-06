package elsys.mycar.mycarpro.data.model;

/**
 * Created by valio_stoyanov on 02.05.17.
 */

public class SimpleUser {

    private String username;
    private String password;

    public SimpleUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
