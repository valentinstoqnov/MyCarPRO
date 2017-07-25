package vstoyanov.mycar.mycarpro.data.model;


import com.google.gson.annotations.SerializedName;

import vstoyanov.mycar.mycarpro.data.Constants;

public class User {

    @SerializedName(Constants.ID)
    private String id;
    @SerializedName(Constants.FIRST_NAME)
    private String firstName;
    @SerializedName(Constants.LAST_NAME)
    private String lastName;
    @SerializedName(Constants.EMAIL)
    private String email;
    @SerializedName(Constants.PASSWORD)
    private String password;

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
